package com.xujiaao.android.firmata.transport

import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class NetworkTransport(private val host: String, private val port: Int) : Transport {

    private var mSocket: Socket? = null
    private var mInputStream: InputStream? = null
    private var mOutputStream: OutputStream? = null

    @Throws(IOException::class)
    override fun connect() {
        val socket = try {
            Socket(host, port).apply {
                soTimeout = 5_000
                tcpNoDelay = true
            }
        } catch (e: RuntimeException) {
            throw IOException(e)
        }

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
        mOutputStream?.run {
            write(data)
            flush()
        } ?: throw IOException("Transport is not connected")
}