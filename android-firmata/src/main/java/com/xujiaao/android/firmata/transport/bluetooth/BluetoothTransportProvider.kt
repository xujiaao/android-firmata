package com.xujiaao.android.firmata.transport.bluetooth

import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.net.Uri
import com.xujiaao.android.firmata.transport.Transport
import com.xujiaao.android.firmata.transport.TransportProvider

@Suppress("unused")
class BluetoothTransportProvider : TransportProvider {

    @Throws(IllegalArgumentException::class)
    override fun getTransport(context: Context, uri: Uri): Transport {
        val host = uri.host ?: throw IllegalArgumentException("Failed to get host '$uri'")
        val address = host.toUpperCase().replace("[.-]".toRegex(), ":")

        return if (BluetoothAdapter.checkBluetoothAddress(address)) {
            AddressedBluetoothClientTransport(address, uri.getQueryParameter("pin"))
        } else {
            NamedBluetoothClientTransport(host)
        }
    }
}