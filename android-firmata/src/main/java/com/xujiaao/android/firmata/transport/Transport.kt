package com.xujiaao.android.firmata.transport

import java.io.Closeable
import java.io.IOException

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