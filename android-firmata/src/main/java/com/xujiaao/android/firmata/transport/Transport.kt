package com.xujiaao.android.firmata.transport

import android.bluetooth.BluetoothAdapter
import android.net.Uri
import java.io.IOException

interface Transport {

    @Throws(IOException::class)
    fun connect()

    @Throws(IOException::class)
    fun disconnect()

    @Throws(IOException::class)
    fun read(): Int

    @Throws(IOException::class)
    fun write(data: ByteArray)
}

// -------------------------------------------------------------------------------------------------
// TransportWrapper
// -------------------------------------------------------------------------------------------------

@Suppress("unused")
abstract class TransportWrapper : Transport {

    @Volatile
    private var mBase: Transport? = null

    @Throws(IOException::class)
    override fun connect() {
        val base = getBase()
        try {
            base.connect()
        } catch (e: IOException) {
            try {
                base.disconnect()
            } catch (ignored: IOException) {
            }

            throw e
        }

        mBase = base
    }

    @Throws(IOException::class)
    override fun disconnect() {
        val base = mBase
        mBase = null

        base?.disconnect()
    }

    @Throws(IOException::class)
    override fun read(): Int =
        mBase?.read() ?: throw IOException("Transport is not connected")

    @Throws(IOException::class)
    override fun write(data: ByteArray) =
        mBase?.write(data) ?: throw IOException("Transport is not connected")

    @Throws(IOException::class)
    protected abstract fun getBase(): Transport
}

// -------------------------------------------------------------------------------------------------
// Factory
// -------------------------------------------------------------------------------------------------

@Throws(IllegalArgumentException::class)
fun String.toTransport(transform: ((String) -> Transport) = ::getTransport) = transform(this)

@Throws(IllegalArgumentException::class)
private fun getTransport(string: String): Transport {
    val uri = Uri.parse(string)

    return when (uri.scheme) {
        "bt",
        "bluetooth" -> {
            val host = uri.host ?: throw IllegalArgumentException("Failed to get host '$string'")
            val address = host.toUpperCase().replace("[.-]".toRegex(), ":")
            if (BluetoothAdapter.checkBluetoothAddress(address)) {
                AddressedBluetoothClientTransport(address)
            } else {
                NamedBluetoothClientTransport(host)
            }
        }
        "tcp",
        "wifi",
        "network" -> {
            val host = uri.host ?: throw IllegalArgumentException("Failed to get host '$string'")
            val port = uri.port.apply {
                if (this == -1) {
                    throw IllegalArgumentException("Failed to get port '$string'")
                }
            }

            NetworkTransport(host, port)
        }
        else -> {
            throw IllegalArgumentException("Unsupported scheme: ${uri.scheme}")
        }
    }
}