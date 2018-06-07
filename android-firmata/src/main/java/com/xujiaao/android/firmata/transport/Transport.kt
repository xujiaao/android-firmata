package com.xujiaao.android.firmata.transport

import android.content.Context
import android.net.Uri
import java.io.Closeable
import java.io.IOException

@Suppress("FunctionName")
@Throws(IllegalArgumentException::class)
fun Transport(context: Context, uri: String) = DefaultTransportProvider.getTransport(context, uri)

@Suppress("FunctionName")
@Throws(IllegalArgumentException::class)
fun Transport(context: Context, uri: Uri) = DefaultTransportProvider.getTransport(context, uri)

@Throws(IllegalArgumentException::class)
fun String.toTransport(context: Context): Transport = Transport(context, this)

interface Transport {

    fun openConnection(): Connection

    interface Connection : Closeable {

        @Throws(IOException::class)
        fun connect()

        @Throws(IOException::class)
        fun read(): Int

        @Throws(IOException::class)
        fun write(data: ByteArray)

        @Throws(IOException::class)
        override fun close()
    }
}