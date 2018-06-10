package com.xujiaao.android.firmata.transport.usb

import android.content.Context
import android.net.Uri
import com.xujiaao.android.firmata.transport.Transport
import com.xujiaao.android.firmata.transport.TransportProvider

@Suppress("unused")
class UsbTransportProvider : TransportProvider {

    @Throws(IllegalArgumentException::class)
    override fun getTransport(context: Context, uri: Uri): Transport {
        var name: String? = null
        if (uri.scheme != null) {
            val builder = StringBuilder()
            uri.host?.trim('/')?.let { if (!it.isEmpty()) builder.append('/').append(it) }
            uri.path?.trim('/')?.let { if (!it.isEmpty()) builder.append('/').append(it) }

            name = builder.toString()
        }

        return UsbTransport(
            context = context,
            baudRate = uri.getQueryParameter("baudRate")?.toIntOrNull() ?: USB_BAUD_RATE_DEFAULT,
            name = name
        )
    }
}