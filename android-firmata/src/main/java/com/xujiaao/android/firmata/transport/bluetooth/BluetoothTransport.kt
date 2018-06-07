package com.xujiaao.android.firmata.transport.bluetooth

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import com.xujiaao.android.firmata.transport.Transport
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

val UUID_SPP: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

abstract class BluetoothTransport : Transport {

    override fun openConnection(): Transport.Connection = Connection()

    @Throws(IOException::class)
    protected abstract fun getBluetoothSocket(isClosed: () -> Boolean): BluetoothSocket

    private inner class Connection : Transport.Connection {

        private var mClosed = false
        private var mSocket: BluetoothSocket? = null
        private var mInputStream: InputStream? = null
        private var mOutputStream: OutputStream? = null

        @Throws(IOException::class)
        override fun connect() {
            val socket = getBluetoothSocket { mClosed }

            var closed = false
            synchronized(this) {
                closed = mClosed

                if (!closed) {
                    mSocket = socket
                    mInputStream = BufferedInputStream(socket.inputStream, 32)
                    mOutputStream = socket.outputStream
                }
            }

            if (closed) {
                socket.close()

                throw IOException("Transport connection is closed")
            }
        }

        @Throws(IOException::class)
        override fun read(): Int =
            mInputStream?.read() ?: throw IOException("Transport connection is closed")

        @Throws(IOException::class)
        override fun write(data: ByteArray) =
            mOutputStream?.run {
                write(data)
                flush()
            } ?: throw IOException("Transport connection is closed")

        @Throws(IOException::class)
        override fun close() {
            var socket: BluetoothSocket? = null
            synchronized(this) {
                socket = mSocket

                mClosed = true
                mSocket = null
                mInputStream = null
                mOutputStream = null
            }

            socket?.close()
        }
    }
}

// -------------------------------------------------------------------------------------------------
// Client
// -------------------------------------------------------------------------------------------------

abstract class BluetoothClientTransport : BluetoothTransport() {

    @Throws(IOException::class)
    override fun getBluetoothSocket(isClosed: () -> Boolean): BluetoothSocket {
        val device = getBluetoothDevice()
        if (isClosed()) {
            throw IOException("Transport connection is closed")
        }

        val socket = device.createRfcommSocketToServiceRecord(UUID_SPP)
                ?: throw IOException("Failed to create Rfcomm socket")

        if (!isClosed()) {
            try {
                socket.connect()
            } catch (e: IOException) {
                try {
                    socket.close()
                } catch (ignored: IOException) {
                }

                throw e
            }
        }

        return socket
    }

    @Throws(IOException::class)
    protected abstract fun getBluetoothDevice(): BluetoothDevice
}

class AddressedBluetoothClientTransport(
    private val address: String
) : BluetoothClientTransport() {

    @Throws(IOException::class)
    override fun getBluetoothDevice(): BluetoothDevice {
        val adapter = BluetoothAdapter.getDefaultAdapter()
                ?: throw IOException("Bluetooth is unavailable")

        if (!adapter.isEnabled) {
            throw IOException("Bluetooth is disabled")
        }

        return adapter.getRemoteDevice(address)
    }
}

class NamedBluetoothClientTransport(
    private val name: String
) : BluetoothClientTransport() {

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