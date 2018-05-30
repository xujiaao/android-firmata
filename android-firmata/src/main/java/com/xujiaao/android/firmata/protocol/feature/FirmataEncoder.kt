package com.xujiaao.android.firmata.protocol.feature

import android.util.SparseIntArray
import com.xujiaao.android.firmata.protocol.*

private const val ENCODER_ATTACH = 0x00.toByte()
private const val ENCODER_REPORT_POSITION = 0x01.toByte()
private const val ENCODER_REPORT_POSITIONS = 0x02.toByte()
private const val ENCODER_RESET_POSITION = 0x03.toByte()
private const val ENCODER_REPORT_AUTO = 0x04.toByte()
private const val ENCODER_DETACH = 0x05.toByte()

// -------------------------------------------------------------------------------------------------
// Attach Encoder
// -------------------------------------------------------------------------------------------------

/**
 * Attach encoder query.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_ATTACH             (0x00)
 * 3 encoder #                  ([0 - MAX_ENCODERS-1])
 * 4 pin A #                    (first pin)
 * 5 pin B #                    (second pin)
 * 6 END_SYSEX                  (0xF7)
 * ````
 */
fun Firmata.sendAttachEncoderRequest(encoder: Int, pinA: Int, pinB: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_ATTACH,
            (encoder and 0x7F).toByte(),
            (pinA and 0x7F).toByte(),
            (pinB and 0x7F).toByte(),
            MIDI_END_SYSEX
        )
    )
}

// -------------------------------------------------------------------------------------------------
// Detach Encoder
// -------------------------------------------------------------------------------------------------

/**
 * Detach encoder query.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_DETACH             (0x05)
 * 3 encoder #                  ([0 - MAX_ENCODERS-1])
 * 4 END_SYSEX                  (0xF7)
 * ````
 */
fun Firmata.sendDetachEncoderRequest(encoder: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_DETACH,
            (encoder and 0x7F).toByte(),
            MIDI_END_SYSEX
        )
    )
}

// -------------------------------------------------------------------------------------------------
// Reset
// -------------------------------------------------------------------------------------------------

/**
 * Reset encoder position to zero.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_RESET_POSITION     (0x03)
 * 3 encoder #                  ([0 - MAX_ENCODERS-1])
 * 4 END_SYSEX                  (0xF7)
 * ````
 */
fun Firmata.sendResetEncoderPositionRequest(encoder: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_RESET_POSITION,
            (encoder and 0x7F).toByte(),
            MIDI_END_SYSEX
        )
    )
}

// -------------------------------------------------------------------------------------------------
// Reporting
// -------------------------------------------------------------------------------------------------

/**
 * Enable/disable reporting. Note: when reports are enabled, EncoderFirmata feature send the
 * message at every SAMPLING interval (19ms by default).
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_REPORT_AUTO        (0x04)
 * 3 enable                     (0x00 => false, true otherwise)
 * 4 END_SYSEX                  (0xF7)
 * ````
 */
fun Firmata.sendAutoReportEncoderRequest(enabled: Boolean) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_REPORT_AUTO,
            if (enabled) 0x01 else 0x00,
            MIDI_END_SYSEX
        )
    )
}

/**
 * Report encoder's position.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_REPORT_POSITION    (0x01)
 * 3 Encoder #                  ([0 - MAX_ENCODERS-1])
 * 4 END_SYSEX                  (0xF7)
 * ````
 */
@Suppress("unused")
fun Firmata.sendReportEncoderRequest(encoder: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_REPORT_POSITION,
            (encoder and 0x7F).toByte(),
            MIDI_END_SYSEX
        )
    )
}

/**
 * Report all encoders positions.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 ENCODER_REPORT_POSITIONS   (0x02)
 * 3 END_SYSEX                  (0xF7)
 * ````
 */
@Suppress("unused")
fun Firmata.sendReportAllEncodersRequest() {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_ENCODER_DATA,
            ENCODER_REPORT_POSITIONS,
            MIDI_END_SYSEX
        )
    )
}

fun Firmata.registerEncoderResponseReceiver(receiver: EncoderResponseReceiver) {
    registerReceiver(receiver)
}

fun Firmata.unregisterEncoderResponseReceiver(receiver: EncoderResponseReceiver) {
    unregisterReceiver(receiver)
}

typealias EncoderResponseReceiver = (EncoderResponse) -> Unit

/**
 * Encoder response.
 *
 * ````
 * 0 START_SYSEX                (0xF0)
 * 1 ENCODER_DATA               (0x61)
 * 2 first enc. #  & first enc. dir.   [= (direction << 6) | (#)]
 * 4 first enc. position, bits 0-6
 * 5 first enc. position, bits 7-13
 * 6 first enc. position, bits 14-20
 * 7 first enc. position, bits 21-27
 * 8 second enc. #  & second enc. dir. [= (direction << 6) | (#)]
 * ...
 * N END_SYSEX                  (0xF7)
 * ````
 */
class EncoderResponse(data: ByteArray) : FirmataMessage {

    private val mPositions = SparseIntArray((data.size - 3) / 5).apply {
        for (i in 2 until data.size - 1 step 5) {
            val encoder = data[i].toInt() and 0x3F
            val direction = if ((data[i].toInt() and 0x40) == 0) 1 else -1
            val position = data[i + 1].toInt() or
                    (data[i + 2].toInt() shl 7) or
                    (data[i + 2].toInt() shl 14) or
                    (data[i + 2].toInt() shl 21)

            put(encoder, direction * position)
        }
    }

    fun getPosition(encoder: Int) = mPositions.get(encoder, 0)

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_ENCODER_DATA, ::EncoderResponse)
    }
}