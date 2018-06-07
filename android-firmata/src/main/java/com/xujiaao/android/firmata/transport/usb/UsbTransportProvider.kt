package com.xujiaao.android.firmata.transport.usb

import android.content.Context
import android.net.Uri
import com.xujiaao.android.firmata.transport.Transport
import com.xujiaao.android.firmata.transport.TransportProvider

@Suppress("unused")
class UsbTransportProvider : TransportProvider {

    @Throws(IllegalArgumentException::class)
    override fun getTransport(context: Context, uri: Uri): Transport {
        return UsbTransport(
            context = context,
            baudRate = uri.getQueryParameter("baudRate")?.toIntOrNull() ?: USB_BAUD_RATE_DEFAULT
        )
    }
}