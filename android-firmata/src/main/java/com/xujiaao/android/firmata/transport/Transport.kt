package com.xujiaao.android.firmata.transport

import java.io.Closeable
import java.io.IOException
import java.io.InputStream
import java.io.OutputStream

interface Transport {

    fun openConnection(): Connection

    interface Connection : Closeable {

        @Throws(IOException::class)
        fun connect()

        @Throws(IOException::class)
        fun getInputStream(): InputStream

        @Throws(IOException::class)
        fun getOutputStream(): OutputStream

        @Throws(IOException::class)
        override fun close()
    }
}