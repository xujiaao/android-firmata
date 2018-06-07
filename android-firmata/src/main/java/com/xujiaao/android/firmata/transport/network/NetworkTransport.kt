package com.xujiaao.android.firmata.transport.network

import com.xujiaao.android.firmata.transport.Transport
import java.io.BufferedInputStream
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream
import java.net.Socket

class NetworkTransport(private val host: String, private val port: Int) : Transport {

    override fun openConnection(): Transport.Connection = Connection()

    private inner class Connection : Transport.Connection {

        private var mClosed = false
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
            var socket: Socket? = null
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