package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

// -------------------------------------------------------------------------------------------------
// Analog Mapping
// -------------------------------------------------------------------------------------------------

/**
 * Analog mapping query.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  analog mapping query     (0x69)
 * 2  END_SYSEX                (0xF7)
 * ````
 */
fun Firmata.sendAnalogMappingQueryRequest() = sendRequest(ANALOG_MAPPING_QUERY_REQUEST)

private val ANALOG_MAPPING_QUERY_REQUEST = byteArrayOf(
    MIDI_START_SYSEX,
    SYSEX_ANALOG_MAPPING_QUERY,
    MIDI_END_SYSEX
)

fun Firmata.registerAnalogMappingResponseReceiver(receiver: AnalogMappingResponseReceiver) {
    registerReceiver(receiver)
}

fun Firmata.unregisterAnalogMappingResponseReceiver(receiver: AnalogMappingResponseReceiver) {
    unregisterReceiver(receiver)
}

typealias AnalogMappingResponseReceiver = (AnalogMappingResponse) -> Unit

/**
 * Analog mMapping response.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  analog mMapping response  (0x6A)
 * 2  analog channel corresponding to pin 0, or 127 if pin 0 does not support analog
 * 3  analog channel corresponding to pin 1, or 127 if pin 1 does not support analog
 * 4  analog channel corresponding to pin 2, or 127 if pin 2 does not support analog
 * ... etc, one byte for each pin
 * N  END_SYSEX                (0xF7)
 * ````
 */
class AnalogMappingResponse(private val data: ByteArray) : FirmataMessage {

    private val mMapping = IntArray(data.size - 3).apply {
        for (i in 0 until size) {
            this[i] = data[i + 2].toInt()
        }
    }

    fun getAnalogPin(analogChannel: Int): Int =
        mMapping.indexOf(analogChannel)

    fun getAnalogChannel(pin: Int): Int {
        if (pin >= 0 && pin < mMapping.size) {
            val analogChannel = mMapping[pin]
            if (analogChannel != 127) {
                return analogChannel
            }
        }

        return -1
    }

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(
            SYSEX_ANALOG_MAPPING_RESPONSE, ::AnalogMappingResponse
        )
    }
}