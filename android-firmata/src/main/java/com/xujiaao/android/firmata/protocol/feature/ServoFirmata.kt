package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.Firmata
import com.xujiaao.android.firmata.protocol.MIDI_END_SYSEX
import com.xujiaao.android.firmata.protocol.MIDI_START_SYSEX
import com.xujiaao.android.firmata.protocol.SYSEX_SERVO_CONFIG

// -------------------------------------------------------------------------------------------------
// Servo config
// -------------------------------------------------------------------------------------------------

/**
 * Send a Servo config message to configure a pin a servo.
 *
 * ````
 * // minPulse and maxPulse are 14-bit unsigned integers
 * 0  START_SYSEX          (0xF0)
 * 1  SERVO_CONFIG         (0x70)
 * 2  pin number           (0-127)
 * 3  minPulse LSB         (0-6)
 * 4  minPulse MSB         (7-13)
 * 5  maxPulse LSB         (0-6)
 * 6  maxPulse MSB         (7-13)
 * 7  END_SYSEX            (0xF7)
 * ````
 */
fun Firmata.sendServoConfig(pin: Int, min: Int, max: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_SERVO_CONFIG,
            (pin and 0x7F).toByte(),
            (min and 0x7F).toByte(),
            ((min ushr 7) and 0x7F).toByte(),
            (max and 0x7F).toByte(),
            ((max ushr 7) and 0x7F).toByte(),
            MIDI_END_SYSEX
        )
    )
}