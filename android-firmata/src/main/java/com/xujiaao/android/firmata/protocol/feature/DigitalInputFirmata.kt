package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

// -------------------------------------------------------------------------------------------------
// Report Digital
// -------------------------------------------------------------------------------------------------

/**
 * Toggle digital port reporting by port (second nibble of byte 0).
 *
 * ````
 * 0  toggle digital port reporting (0xD0-0xDF) (MIDI Aftertouch)
 * 1  disable(0) / enable(non-zero)
 * ````
 */
fun Firmata.sendReportDigitalRequest(port: Int, enabled: Boolean) {
    sendRequest(
        byteArrayOf(
            (MIDI_REPORT_DIGITAL.toInt() or port).toByte(),
            if (enabled) 1.toByte() else 0.toByte()
        )
    )
}

fun Firmata.registerDigitalMessageReceiver(receiver: DigitalMessageReceiver) {
    registerReceiver(receiver)
}

fun Firmata.unregisterDigitalMessageReceiver(receiver: DigitalMessageReceiver) {
    unregisterReceiver(receiver)
}

typealias DigitalMessageReceiver = (DigitalMessage) -> Unit

/**
 * Two byte digital data format, second nibble of byte 0 gives the port number.
 *
 * ````
 * 0  digital data, 0x90-0x9F, (MIDI NoteOn, bud different data format)
 * 1  digital pins 0-6 bitmask
 * 2  digital pin 7 bitmask
 * ````
 */
class DigitalMessage(data: ByteArray) : FirmataMessage {

    val port = data[0].toInt() and 0x7F
    val values = (data[1].toInt() ushr 7) or data[2].toInt()

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createMidiMessageParser(MIDI_DIGITAL_MESSAGE, 3, ::DigitalMessage)
    }
}