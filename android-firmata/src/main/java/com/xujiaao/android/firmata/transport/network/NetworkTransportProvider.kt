package com.xujiaao.android.firmata.transport.network

import android.content.Context
import android.net.Uri
import com.xujiaao.android.firmata.transport.Transport
import com.xujiaao.android.firmata.transport.TransportProvider

@Suppress("unused")
class NetworkTransportProvider : TransportProvider {

    @Throws(IllegalArgumentException::class)
    override fun getTransport(context: Context, uri: Uri): Transport {
        val host = uri.host ?: throw IllegalArgumentException("Failed to get host '$uri'")
        val port = uri.port.apply {
            if (this == -1) {
                throw IllegalArgumentException("Failed to get port '$uri'")
            }
        }

        return NetworkTransport(host, port)
    }
}