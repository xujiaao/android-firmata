package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

// -------------------------------------------------------------------------------------------------
// Analog Message
// -------------------------------------------------------------------------------------------------

/**
 * Analog 14-bit data format.
 *
 * ````
 * 0  analog pin, 0xE0-0xEF, (MIDI Pitch Wheel)
 * 1  analog least significant 7 bits
 * 2  analog most significant 7 bits
 * ````
 */
fun Firmata.sendAnalogMessage(pin: Int, value: Int) {
    sendRequest(
        byteArrayOf(
            (MIDI_ANALOG_MESSAGE.toInt() or (pin and 0x0F)).toByte(),
            (value and 0x7F).toByte(),
            (value ushr 7).toByte()
        )
    )
}

/**
 * As an alternative to the normal analog message, this extended version allows addressing beyond
 * pin 15 and supports sending analog values with any number of bits.
 * The number of data bits is inferred by the length of the message.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  extended analog message  (0x6F)
 * 2  pin                      (0-127)
 * 3  bits 0-6                 (least significant byte)
 * 4  bits 7-13                (most significant byte)
 * ... additional bytes may be sent if more bits are needed
 * N  END_SYSEX                (0xF7)
 * ````
 */
fun Firmata.sendExtendedAnalogRequest(pin: Int, value: Int) {
    sendRequest(when {
        value > 0x00004000 -> ByteArray(7)
        value > 0x00200000 -> ByteArray(8)
        value > 0x10000000 -> ByteArray(9)
        else -> ByteArray(6)
    }.apply {
        this[0] = MIDI_START_SYSEX
        this[1] = SYSEX_EXTENDED_ANALOG
        this[2] = (pin and 0x7F).toByte()
        this[3] = (value and 0x7F).toByte()
        this[4] = (value shr 7 and 0x7F).toByte()

        val end = size - 1
        if (end >= 6) this[5] = (value shr 14 and 0x7F).toByte()
        if (end >= 7) this[6] = (value shr 21 and 0x7F).toByte()
        if (end >= 8) this[7] = (value shr 28 and 0x7F).toByte()

        this[end] = MIDI_END_SYSEX
    })
}