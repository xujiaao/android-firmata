package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*
import java.util.*

// -------------------------------------------------------------------------------------------------
// Set Pin Mode
// -------------------------------------------------------------------------------------------------

/**
 * Set pin mode.
 *
 * ````
 * 0  set digital pin mode (0xF4) (MIDI Undefined)
 * 1  set pin number (0-127)
 * 2  mode (INPUT/OUTPUT/ANALOG/PWM/SERVO/I2C/ONEWIRE/STEPPER/ENCODER/SERIAL/PULLUP, 0/1/2/3/4/6/7/8/9/10/11)
 * ````
 */
fun Firmata.sendSetPinModeRequest(pin: Int, mode: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_SET_PIN_MODE,
            pin.toByte(),
            mode.toByte()
        )
    )
}

// -------------------------------------------------------------------------------------------------
// Pin State Query
// -------------------------------------------------------------------------------------------------

/**
 * Pin state query.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  pin state query          (0x6D)
 * 2  pin                      (0-127)
 * 3  END_SYSEX                (0xF7)
 * ````
 */
fun Firmata.sendPinStateQueryRequest(pin: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_PIN_STATE_QUERY,
            pin.toByte(),
            MIDI_END_SYSEX
        )
    )
}

fun Firmata.registerPinStateResponseReceiver(receiver: PinStateResponseReceiver) =
    registerReceiver(receiver)

fun Firmata.unregisterPinStateResponseReceiver(receiver: PinStateResponseReceiver) =
    unregisterReceiver(receiver)

typealias PinStateResponseReceiver = (PinStateResponse) -> Unit

/**
 * Pin state response.
 *
 * Note about pin state: For output modes, the state is any digitalValue that has been previously written
 * to the pin. For input modes, the state is the status of the pull-up resistor.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  pin state response       (0x6E)
 * 2  pin                      (0-127)
 * 3  pin mode (the currently configured mode)
 * 4  pin state, bits 0-6
 * 5  (optional) pin state, bits 7-13
 * 6  (optional) pin state, bits 14-20
 * ... additional optional bytes, as many as needed
 * N  END_SYSEX                (0xF7)
 * ````
 */
class PinStateResponse(data: ByteArray) : FirmataMessage {

    val pin = data[2].toInt()
    val mode = data[3].toInt()
    val state = run {
        var state = data[4].toInt()

        if (data.size > 6) {
            state = state or (data[5].toInt() shl 7)
        }

        if (data.size > 7) {
            state = state or (data[6].toInt() shl 14)
        }

        state
    }

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_PIN_STATE_RESPONSE, ::FirmwareReport)
    }
}

// -------------------------------------------------------------------------------------------------
// Capability Query
// -------------------------------------------------------------------------------------------------

/**
 * Capabilities query.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  CAPABILITY_QUERY         (0x6B)
 * 2  END_SYSEX                (0xF7)
 * ````
 */
fun Firmata.sendCapabilityQueryRequest() = sendRequest(CAPABILITY_QUERY_REQUEST)

private val CAPABILITY_QUERY_REQUEST = byteArrayOf(
    MIDI_START_SYSEX,
    SYSEX_CAPABILITY_QUERY,
    MIDI_END_SYSEX
)

fun Firmata.registerCapabilityResponseReceiver(receiver: CapabilityResponseReceiver) =
    registerReceiver(receiver)

fun Firmata.unregisterCapabilityResponseReceiver(receiver: CapabilityResponseReceiver) =
    unregisterReceiver(receiver)

typealias CapabilityResponseReceiver = (CapabilityResponse) -> Unit

/**
 * Capabilities response.
 *
 * ````
 * 0  START_SYSEX              (0xF0)
 * 1  CAPABILITY_RESPONSE      (0x6C)
 * 2  1st supported mode of pin 0
 * 3  1st mode's resolution of pin 0
 * 4  2nd supported mode of pin 0
 * 5  2nd mode's resolution of pin 0
 * ... additional modes/resolutions, followed by `0x7F`,
 * to mark the end of the pin's modes. Subsequently, each pin
 * follows with its modes/resolutions and `0x7F`,
 * until all mPins are defined.
 * N  END_SYSEX                (0xF7)
 * ````
 */
class CapabilityResponse(data: ByteArray) : FirmataMessage {

    private val mPins = parseCapabilityResponse(data)

    fun getPinsCount(): Int {
        return mPins.size
    }

    fun getPinModes(pin: Int): Map<Int, Int> {
        return mPins.getOrElse(pin) { emptyMap() }
    }

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_CAPABILITY_RESPONSE, ::CapabilityResponse)
    }
}

private fun parseCapabilityResponse(data: ByteArray): Array<Map<Int, Int>> {
    val pins = ArrayList<Map<Int, Int>>()

    var mode = -1
    var pinModes: TreeMap<Int, Int>? = null
    for (i in 2 until data.size - 1) {
        val curr = data[i].toInt()
        if (curr == 0x7F) {
            pins.add(pinModes ?: mapOf())

            mode = -1
            pinModes = null
        } else {
            if (mode == -1) {
                mode = curr
            } else {
                if (pinModes == null) {
                    pinModes = TreeMap()
                }

                pinModes[mode] = curr
                mode = -1
            }
        }
    }

    if (pinModes != null) {
        pins.add(pinModes)
    }

    return pins.toTypedArray()
}