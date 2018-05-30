package com.xujiaao.android.firmata.sample

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.xujiaao.android.firmata.board.connectBoardWithLifecycle
import com.xujiaao.android.firmata.board.driver.Led
import com.xujiaao.android.firmata.transport.toTransport
import org.jetbrains.anko.toast

class BlinkLedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /**
         * Connect board through Bluetooth transport.
         *
         * NOTICE: make sure the name of Bluetooth device is "HC-06",
         * and the device has already been bonded with your Android phone!!!
         */
        connectBoardWithLifecycle("bt://HC-06".toTransport(), lifecycle, {
            onConnecting { toast("Connecting...") }

            onConnected { board ->
                toast("Connected")

                val led = board.Led(13) // Create an Led on pin 13
                led.blink(500) // Blink every half second
            }

            onDisconnected { error ->
                if (error != null) {
                    toast("Disconnected: ${error.message}")
                }
            }
        })
    }
}