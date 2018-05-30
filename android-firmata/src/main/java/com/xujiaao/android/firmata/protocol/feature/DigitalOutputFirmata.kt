package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.Firmata
import com.xujiaao.android.firmata.protocol.MIDI_DIGITAL_MESSAGE
import com.xujiaao.android.firmata.protocol.MIDI_SET_DIGITAL_PIN_VALUE

// -------------------------------------------------------------------------------------------------
// Digital Message
// -------------------------------------------------------------------------------------------------


/**
 * Two byte digital data format, second nibble of byte 0 gives the port number.
 *
 * ````
 * 0  digital data, 0x90-0x9F, (MIDI NoteOn, bud different data format)
 * 1  digital pins 0-6 bitmask
 * 2  digital pin 7 bitmask
 * ````
 */
@Suppress("unused")
fun Firmata.sendDigitalMessage(port: Int, values: Int) {
    sendRequest(
        byteArrayOf(
            (MIDI_DIGITAL_MESSAGE.toInt() or port).toByte(),
            (values and 0x7F).toByte(),
            (values ushr 7).toByte()
        )
    )
}

// -------------------------------------------------------------------------------------------------
// Set Digital Pin Value
// -------------------------------------------------------------------------------------------------

/**
 * Set digital pin value (added in v2.5)
 *
 * ````
 * 0  set digital pin value (0xF5) (MIDI Undefined)
 * 1  set pin number (0-127)
 * 2  value (LOW/HIGH, 0/1)
 * ````
 */
fun Firmata.sendSetDigitalPinValueRequest(pin: Int, value: Boolean) {
    sendRequest(
        byteArrayOf(
            MIDI_SET_DIGITAL_PIN_VALUE,
            (pin and 0x7F).toByte(),
            if (value) 1.toByte() else 0.toByte()
        )
    )
}