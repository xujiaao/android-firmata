package com.xujiaao.android.firmata.transport.usb

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.hardware.usb.UsbDevice
import android.hardware.usb.UsbDeviceConnection
import android.hardware.usb.UsbManager
import android.os.SystemClock
import com.felhr.usbserial.FTDISerialDevice
import com.felhr.usbserial.UsbSerialDevice
import com.felhr.usbserial.UsbSerialInterface
import com.xujiaao.android.firmata.toolbox.AndroidThingsCompat
import com.xujiaao.android.firmata.toolbox.toUnsignedInt
import com.xujiaao.android.firmata.transport.Transport
import java.io.Closeable
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock
import kotlin.concurrent.withLock

const val USB_BAUD_RATE_DEFAULT = 57600

private const val USB_TRANSFER_TIMEOUT = 500 // 0 for infinite mode
private const val USB_TRANSFER_START_DELAY = 1500L // delay after connecting
private const val USB_TRANSFER_START_DELAY_AT = 4500L // delay after connecting (Android Things)
private const val USB_PERMISSION_TIMEOUT = 30_000L
private const val USB_PERMISSION_ACTION =
    "com.xujiaao.android.firmata.transport.usb.action.USB_PERMISSION"

class UsbTransport(
    context: Context,
    private val baudRate: Int = USB_BAUD_RATE_DEFAULT,
    private val name: String? = null
) : Transport {

    private val mContext = context.applicationContext // use application context.

    override fun openConnection(): Transport.Connection = Connection()

    private inner class Connection : Transport.Connection {

        private var mClosed = false
        private var mUsbSerialDevice: UsbSerialDevice? = null
        private var mUsbDeviceConnection: UsbDeviceConnection? = null
        private var mUsbPermissionGranter: UsbPermissionGranter? = null
        private var mUsbDetachedReceiver: BroadcastReceiver? = null

        private val mReadBuffer = ByteArray(64)
        private var mReadBufferSize = 0
        private var mReadBufferPosition = 0

        @Throws(IOException::class)
        override fun connect() {
            val manager = mContext.getSystemService(Context.USB_SERVICE) as UsbManager
            val device = getSerialPortDevice(mContext, manager)

            if (!manager.hasPermission(device)) {
                val usbPermissionGranter = UsbPermissionGranter(mContext, manager, device)
                synchronized(this) {
                    mUsbPermissionGranter = usbPermissionGranter
                }

                usbPermissionGranter.await()

                synchronized(this) {
                    mUsbPermissionGranter?.close()
                    mUsbPermissionGranter = null

                    if (mClosed) {
                        throw IOException("Transport connection is closed")
                    }
                }

                if (!manager.hasPermission(device)) {
                    throw IOException("Failed to grant permission for '${device.deviceName}'")
                }
            }

            UsbConnectionFinalizer.await() // wait for all connections closed...

            if (mClosed) {
                throw IOException("Transport connection is closed")
            }

            val usbDeviceConnection = manager.openDevice(device)
                    ?: throw IOException("Failed to open connection for '${device.deviceName}'")

            val usbSerialDevice = UsbSerialDevice.createUsbSerialDevice(device, usbDeviceConnection)
            if (!usbSerialDevice.syncOpen()) {
                throw IOException("Failed to open serial port device: ${device.deviceName}")
            }

            usbSerialDevice.run {
                setBaudRate(baudRate)
                setDataBits(UsbSerialInterface.DATA_BITS_8)
                setStopBits(UsbSerialInterface.STOP_BITS_1)
                setParity(UsbSerialInterface.PARITY_NONE)
                setFlowControl(UsbSerialInterface.FLOW_CONTROL_OFF)
            }

            val delay = if (AndroidThingsCompat.isAndroidThings) {
                USB_TRANSFER_START_DELAY_AT
            } else {
                USB_TRANSFER_START_DELAY
            }

            try {
                Thread.sleep(delay) // wait a moment before reading/writing...
            } catch (ignored: InterruptedException) {
                Thread.currentThread().interrupt()
            }

            var closed = false
            synchronized(this) {
                closed = mClosed

                if (!closed) {
                    mUsbSerialDevice = usbSerialDevice
                    mUsbDeviceConnection = usbDeviceConnection
                    mUsbDetachedReceiver = object : BroadcastReceiver() {

                        override fun onReceive(context: Context, intent: Intent) {
                            if (intent.action == UsbManager.ACTION_USB_DEVICE_DETACHED) {
                                close()
                            }
                        }
                    }

                    mContext.registerReceiver(
                        mUsbDetachedReceiver,
                        IntentFilter(UsbManager.ACTION_USB_DEVICE_DETACHED)
                    )
                }
            }

            if (closed) {
                UsbConnectionFinalizer.closeSync(usbSerialDevice, usbDeviceConnection)
            }
        }

        @Throws(IOException::class)
        override fun read(): Int {
            if (mClosed) {
                throw IOException("Transport connection is closed")
            }

            synchronized(mReadBuffer) {
                if (mReadBufferPosition >= mReadBufferSize) {
                    val result = read(mReadBuffer)
                    if (result <= 0) {
                        return result
                    }

                    mReadBufferSize = result
                    mReadBufferPosition = 0
                }

                return mReadBuffer[mReadBufferPosition++].toUnsignedInt()
            }
        }

        @Throws(IOException::class)
        private fun read(buffer: ByteArray): Int {
            while (true) {
                mUsbSerialDevice?.run {
                    val result = syncRead(buffer, USB_TRANSFER_TIMEOUT)
                    if (result > 0) {
                        return result
                    } else {
                        Thread.yield()
                    }
                } ?: throw IOException("Transport connection is closed")
            }
        }

        @Throws(IOException::class)
        override fun write(data: ByteArray) {
            while (true) {
                mUsbSerialDevice?.run {
                    val result = syncWrite(data, USB_TRANSFER_TIMEOUT)
                    if (result > 0) {
                        return
                    } else {
                        Thread.yield()
                    }
                } ?: throw IOException("Transport connection is closed")
            }
        }

        @Throws(IOException::class)
        override fun close() {
            var usbSerialDevice: UsbSerialDevice? = null
            var usbDeviceConnection: UsbDeviceConnection? = null
            synchronized(this) {
                mClosed = true

                mUsbPermissionGranter?.close()
                mUsbPermissionGranter = null

                usbSerialDevice = mUsbSerialDevice
                mUsbSerialDevice = null

                usbDeviceConnection = mUsbDeviceConnection
                mUsbDeviceConnection = null

                mUsbDetachedReceiver?.let {
                    mContext.unregisterReceiver(it)
                    mUsbDetachedReceiver = null
                }
            }

            UsbConnectionFinalizer.close(usbSerialDevice, usbDeviceConnection)
        }

        @Throws(IOException::class)
        private fun getSerialPortDevice(context: Context, manager: UsbManager): UsbDevice {
            if (!context.packageManager.hasSystemFeature(PackageManager.FEATURE_USB_HOST)) {
                throw IOException(
                    "System feature '${PackageManager.FEATURE_USB_HOST}' is not supported"
                )
            }

            val usbDevices = try {
                manager.deviceList
            } catch (e: Throwable) {
                throw IOException("Failed to list USB devices", e)
            }

            if (usbDevices == null || usbDevices.isEmpty()) {
                throw IOException("Failed to list USB devices")
            }

            if (name.isNullOrEmpty()) {
                usbDevices.forEach {
                    val device = it.value
                    if (UsbSerialDevice.isSupported(device)) {
                        return device
                    }
                }

                throw IOException("No supported USB devices found")
            } else {
                val device = usbDevices[name] ?: throw IOException("No such device '$name'")
                if (!UsbSerialDevice.isSupported(device)) {
                    throw IOException("Device '$name' is not supported")
                }

                return device
            }
        }
    }
}

private class UsbPermissionGranter(
    private val context: Context,
    manager: UsbManager,
    private val device: UsbDevice
) : Closeable {

    private var mClosed = false
    private val mCountDownLatch: CountDownLatch = CountDownLatch(1)
    private val mBroadcastReceiver = object : BroadcastReceiver() {

        override fun onReceive(context: Context, intent: Intent) {
            if (intent.action == USB_PERMISSION_ACTION) {
                val device = intent.getParcelableExtra<UsbDevice>(UsbManager.EXTRA_DEVICE)
                if (device != null && device == this@UsbPermissionGranter.device) {
                    mCountDownLatch.countDown()
                }
            }
        }
    }

    init {
        context.registerReceiver(mBroadcastReceiver, IntentFilter().apply {
            addAction(USB_PERMISSION_ACTION)
        })

        manager.requestPermission(
            device,
            PendingIntent.getBroadcast(context, 0, Intent(USB_PERMISSION_ACTION), 0)
        )
    }

    fun await() {
        try {
            mCountDownLatch.await(USB_PERMISSION_TIMEOUT, TimeUnit.MILLISECONDS)
        } catch (ignored: InterruptedException) {
            Thread.currentThread().interrupt()
        }

        synchronized(this) {
            if (!mClosed) {
                mClosed = true
                context.unregisterReceiver(mBroadcastReceiver)
            }
        }
    }

    override fun close() {
        synchronized(this) {
            if (!mClosed) {
                mClosed = true
                mCountDownLatch.countDown()
                context.unregisterReceiver(mBroadcastReceiver)
            }
        }
    }
}

private object UsbConnectionFinalizer {

    private val mLock = ReentrantLock()
    private val mConditions = ArrayList<Condition>()

    fun await() {
        while (true) {
            mLock.withLock {
                if (mConditions.isEmpty()) {
                    return
                }

                try {
                    mConditions[0].await()
                } catch (ignored: InterruptedException) {
                    Thread.currentThread().interrupt()

                    return
                }
            }
        }
    }

    fun close(device: UsbSerialDevice?, connection: UsbDeviceConnection?) {
        if (device == null && connection == null) {
            return
        }

        val thread = mLock.withLock {
            val condition = mLock.newCondition()
            mConditions.add(condition)

            Thread {
                closeSync(device, connection)

                mLock.withLock {
                    mConditions.remove(condition)
                    condition.signalAll()
                }
            }
        }

        thread.name = "usb-connection-finalizer"
        thread.start()
    }

    fun closeSync(device: UsbSerialDevice?, connection: UsbDeviceConnection?) {
        if (device != null) {
            device.syncClose()

            /**
             * Wait a moment to avoid error:
             *
             *     E/UsbDeviceConnectionJNI: device is closed in native_control_request
             */
            if (device is FTDISerialDevice) {
                SystemClock.sleep(USB_TRANSFER_TIMEOUT + 100L)
            }
        }

        connection?.close()
    }
}