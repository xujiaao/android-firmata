package com.xujiaao.android.firmata.transport

import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothSocket
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.util.*

val UUID_SPP: UUID = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB")

abstract class BluetoothTransport : Transport {

    private var mSocket: BluetoothSocket? = null
    private var mInputStream: InputStream? = null
    private var mOutputStream: OutputStream? = null

    @Throws(IOException::class)
    override fun connect() {
        val socket = getBluetoothSocket()
        synchronized(this) {
            mSocket = socket
            mInputStream = BufferedInputStream(socket.inputStream, 32)
            mOutputStream = socket.outputStream
        }
    }

    @Throws(IOException::class)
    override fun disconnect() {
        val socket = mSocket
        synchronized(this) {
            mSocket = null
            mInputStream = null
            mOutputStream = null
        }

        socket?.close()
    }

    @Throws(IOException::class)
    override fun read(): Int =
        mInputStream?.read() ?: throw IOException("Transport is not connected")

    @Throws(IOException::class)
    override fun write(data: ByteArray) =
        mOutputStream?.write(data) ?: throw IOException("Transport is not connected")

    @Throws(IOException::class)
    protected abstract fun getBluetoothSocket(): BluetoothSocket
}

// -------------------------------------------------------------------------------------------------
// Client
// -------------------------------------------------------------------------------------------------

abstract class BluetoothClientTransport : BluetoothTransport() {

    @Throws(IOException::class)
    override fun getBluetoothSocket(): BluetoothSocket {
        val device = getBluetoothDevice()
        val socket = device.createRfcommSocketToServiceRecord(UUID_SPP)
                ?: throw IOException("Failed to create Rfcomm socket")

        try {
            socket.connect()
        } catch (e: IOException) {
            try {
                socket.close()
            } catch (ignored: IOException) {
            }

            throw e
        }

        return socket
    }

    @Throws(IOException::class)
    protected abstract fun getBluetoothDevice(): BluetoothDevice
}

class AddressedBluetoothClientTransport(private val address: String) : BluetoothClientTransport() {

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

class NamedBluetoothClientTransport(private val name: String) : BluetoothClientTransport() {

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