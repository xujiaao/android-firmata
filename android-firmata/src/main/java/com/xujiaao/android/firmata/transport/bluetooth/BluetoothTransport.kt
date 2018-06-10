package com.xujiaao.android.firmata.transport.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import com.google.android.things.bluetooth.BluetoothConnectionManager
import com.google.android.things.bluetooth.BluetoothPairingCallback
import com.google.android.things.bluetooth.PairingParams
import com.xujiaao.android.firmata.toolbox.AndroidThingsCompat
import com.xujiaao.android.firmata.transport.Transport
import java.io.Closeable
import java.io.IOException
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


class AddressedBluetoothClientTransport(
    private val address: String,
    private val pin: String? = null
) : Transport {

    override fun openConnection(): Transport.Connection = Connection()

    private inner class Connection : BluetoothClientConnection() {

        private var mATBluetoothPairingHelper: ATBluetoothPairingHelper? = null

        @Throws(IOException::class)
        override fun getBluetoothDevice(): BluetoothDevice {
            val adapter = BluetoothAdapter.getDefaultAdapter()
                    ?: throw IOException("Bluetooth is unavailable")

            if (!adapter.isEnabled) {
                throw IOException("Bluetooth is disabled")
            }

            val device = adapter.getRemoteDevice(address)

            if (AndroidThingsCompat.isAndroidThings) {
                if (adapter.bondedDevices?.contains(device) == false) { // pair device...
                    val atBluetoothPairingHelper = ATBluetoothPairingHelper(device, pin)
                    synchronized(this) {
                        mATBluetoothPairingHelper = atBluetoothPairingHelper
                    }

                    atBluetoothPairingHelper.await()

                    synchronized(this) {
                        mATBluetoothPairingHelper?.close()
                        mATBluetoothPairingHelper = null

                        if (isClosed()) {
                            throw IOException("Transport connection is closed")
                        }
                    }

                    if (adapter.bondedDevices?.contains(device) == false) {
                        IOException("Failed to pair bluetooth device")
                    }
                }
            }

            return device
        }

        override fun onClose() {
            super.onClose()

            mATBluetoothPairingHelper?.close()
            mATBluetoothPairingHelper = null
        }
    }
}

class NamedBluetoothClientTransport(private val name: String) : Transport {

    override fun openConnection(): Transport.Connection = Connection()

    private inner class Connection : BluetoothClientConnection() {

        @Throws(IOException::class)
        override fun getBluetoothDevice(): BluetoothDevice {
            val adapter = BluetoothAdapter.getDefaultAdapter()
                    ?: throw IOException("Bluetooth is unavailable")

            if (!adapter.isEnabled) {
                throw IOException("Bluetooth is disabled")
            }

            return adapter.bondedDevices?.find {
                it.name == name
            } ?: throw IOException("No bonded bluetooth devices with name '$name' found")
        }
    }
}

// -------------------------------------------------------------------------------------------------
// ATBluetoothPairingHelper
// -------------------------------------------------------------------------------------------------

private const val BLUETOOTH_PAIRING_TIMEOUT = 30_000L

private class ATBluetoothPairingHelper(
    private val device: BluetoothDevice,
    private val pin: String?
) : Closeable {

    private var mClosed = false
    private var mError: String? = null
    private val mCountDownLatch: CountDownLatch = CountDownLatch(1)
    private val mBluetoothConnectionManager = BluetoothConnectionManager.getInstance()
    private val mBluetoothPairingCallback = object : BluetoothPairingCallback {

        override fun onPairingInitiated(device: BluetoothDevice?, params: PairingParams) =
            ensureDevice(device) {
                when (params.pairingType) {
                    PairingParams.PAIRING_VARIANT_PIN,
                    PairingParams.PAIRING_VARIANT_PIN_16_DIGITS -> {
                        if (!pin.isNullOrEmpty()) {
                            mBluetoothConnectionManager.finishPairing(device, pin)
                        } else {
                            mError = "Pin is required for pairing"
                            mCountDownLatch.countDown()
                        }
                    }
                    PairingParams.PAIRING_VARIANT_CONSENT,
                    PairingParams.PAIRING_VARIANT_PASSKEY_CONFIRMATION -> {
                        mBluetoothConnectionManager.finishPairing(device)
                    }
                }
            }

        override fun onPaired(device: BluetoothDevice?) = ensureDevice(device) {
            mCountDownLatch.countDown()
        }

        override fun onPairingError(
            device: BluetoothDevice?,
            error: BluetoothPairingCallback.PairingError?
        ) = ensureDevice(device) {
            val errorMessage = when (error?.errorCode ?: -1) {
                BluetoothPairingCallback.PairingError.UNBOND_REASON_AUTH_FAILED -> "AUTH FAILED"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_AUTH_REJECTED -> "AUTH_REJECTED"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_AUTH_CANCELED -> "AUTH_CANCELED"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_REMOTE_DEVICE_DOWN -> "REMOTE_DEVICE_DOWN"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_DISCOVERY_IN_PROGRESS -> "DISCOVERY_IN_PROGRESS"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_AUTH_TIMEOUT -> "AUTH_TIMEOUT"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_REPEATED_ATTEMPTS -> "REPEATED_ATTEMPTS"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_REMOTE_AUTH_CANCELED -> "_AUTH_CANCELED"
                BluetoothPairingCallback.PairingError.UNBOND_REASON_REMOVED -> "REMOVED"
                else -> "UNKNOWN"
            }

            mError = "Pairing error occurs: $errorMessage"
            mCountDownLatch.countDown()
        }
    }

    init {
        mBluetoothConnectionManager.registerPairingCallback(mBluetoothPairingCallback)
        mBluetoothConnectionManager.initiatePairing(device)
    }

    @Throws(IOException::class)
    fun await() {
        try {
            mCountDownLatch.await(BLUETOOTH_PAIRING_TIMEOUT, TimeUnit.MILLISECONDS)
        } catch (e: InterruptedException) {
            Thread.currentThread().interrupt()

            throw IOException(e)
        }

        synchronized(this) {
            if (!mClosed) {
                mClosed = true
                mBluetoothConnectionManager.unregisterPairingCallback(mBluetoothPairingCallback)
            }
        }

        mError?.let { throw IOException(it) }
    }

    override fun close() {
        synchronized(this) {
            if (!mClosed) {
                mClosed = true
                mCountDownLatch.countDown()
                mBluetoothConnectionManager.unregisterPairingCallback(mBluetoothPairingCallback)
            }
        }
    }

    private inline fun ensureDevice(target: BluetoothDevice?, action: () -> Unit) {
        if (device == target) {
            action()
        }
    }
}