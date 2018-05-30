package com.xujiaao.android.firmata.sample

import android.content.Context

const val DEFAULT_TRANSPORT = "tcp://192.168.4.1:3030"

private const val SP_NAME = "sample"
private const val SP_KEY_TRANSPORT = "transport"
private const val SP_KEY_AUTO_CONNECT = "auto_connect"

fun Context.getPreferredTransport(): String =
    getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getString(
        SP_KEY_TRANSPORT,
        DEFAULT_TRANSPORT
    )

fun Context.setPreferredTransport(transport: String) =
    getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().run {
        if (transport.isEmpty() || transport == DEFAULT_TRANSPORT) {
            remove(SP_KEY_TRANSPORT)
        } else {
            putString(SP_KEY_TRANSPORT, transport)
        }
    }.apply()

fun Context.isAutoConnectEnabled(): Boolean =
    getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).getBoolean(
        SP_KEY_AUTO_CONNECT,
        true
    )

fun Context.setAutoConnectEnabled(enabled: Boolean) =
    getSharedPreferences(SP_NAME, Context.MODE_PRIVATE).edit().run {
        putBoolean(SP_KEY_AUTO_CONNECT, enabled)
    }.apply()