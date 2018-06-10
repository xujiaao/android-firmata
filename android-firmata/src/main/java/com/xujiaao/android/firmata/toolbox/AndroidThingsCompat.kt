package com.xujiaao.android.firmata.toolbox

object AndroidThingsCompat {

    val isAndroidThings: Boolean

    init {
        isAndroidThings = try {
            Class.forName("com.google.android.things.AndroidThings")
            true
        } catch (e: Throwable) {
            false
        }
    }
}