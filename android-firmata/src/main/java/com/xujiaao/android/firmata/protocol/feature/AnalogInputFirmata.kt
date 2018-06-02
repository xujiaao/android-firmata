package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

// -------------------------------------------------------------------------------------------------
// Report Analog Pin
// -------------------------------------------------------------------------------------------------

/**
 * Toggle analogIn reporting by pin.
 *
 * ````
 * 0  toggle analogIn reporting (0xC0-0xCF) (MIDI Program Change)
 * 1  disable(0) / enable(non-zero)
 * ````
 */
fun Firmata.sendReportAnalogRequest(pin: Int, enabled: Boolean) {
    sendRequest(
        byteArrayOf(
            (MIDI_REPORT_ANALOG.toInt() or (pin and 0x0F)).toByte(),
            if (enabled) 1.toByte() else 0.toByte()
        )
    )
}

fun Firmata.registerAnalogMessageReceiver(receiver: AnalogMessageReceiver) {
    registerReceiver(receiver)
}

fun Firmata.unregisterAnalogMessageReceiver(receiver: AnalogMessageReceiver) {
    unregisterReceiver(receiver)
}

typealias AnalogMessageReceiver = (AnalogMessage) -> Unit

/**
 * Analog 14-bit data format.
 *
 * ````
 * 0  analog pin, 0xE0-0xEF, (MIDI Pitch Wheel)
 * 1  analog least significant 7 bits
 * 2  analog most significant 7 bits
 * ````
 */
class AnalogMessage(data: ByteArray) : FirmataMessage {

    val pin = data[0].toInt() and 0x0F
    val value = data[1].toInt() or (data[2].toInt() shl 7)

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createMidiMessageParser(MIDI_ANALOG_MESSAGE, 3, ::AnalogMessage)
    }
}