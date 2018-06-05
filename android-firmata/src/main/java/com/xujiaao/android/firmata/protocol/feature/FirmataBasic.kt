package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

private const val NULL_CHARACTER = Character.MIN_VALUE.toString()

// -------------------------------------------------------------------------------------------------
// Report Version
// -------------------------------------------------------------------------------------------------

/**
 * Request version report.
 *
 * ````
 * 0  request version report (0xF9) (MIDI Undefined)
 * ````
 */
fun Firmata.sendReportVersionRequest() = sendRequest(REPORT_VERSION_REQUEST)

private val REPORT_VERSION_REQUEST = byteArrayOf(
    MIDI_REPORT_VERSION
)

fun Firmata.registerVersionReportReceiver(receiver: VersionReportReceiver) =
    registerReceiver(receiver)

fun Firmata.unregisterVersionReportReceiver(receiver: VersionReportReceiver) =
    unregisterReceiver(receiver)

typealias VersionReportReceiver = (VersionReport) -> Unit

/**
 * Version report.
 *
 * ````
 * 0  version report header (0xF9) (MIDI Undefined)
 * 1  major version (0-127)
 * 2  minor version (0-127)
 * ````
 */
class VersionReport(data: ByteArray) : FirmataMessage {

    val majorVersion = data[1].toInt()
    val minorVersion = data[2].toInt()

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createMidiMessageParser(MIDI_REPORT_VERSION, 3, ::VersionReport)
    }
}

// -------------------------------------------------------------------------------------------------
// System Reset
// -------------------------------------------------------------------------------------------------

/**
 * System Reset.
 *
 * ````
 * 0  system reset (0xFF)
 * ````
 */
fun Firmata.sendSystemResetRequest() = sendRequest(SYSTEM_RESET_REQUEST)

private val SYSTEM_RESET_REQUEST = byteArrayOf(
    MIDI_SYSTEM_RESET
)

// -------------------------------------------------------------------------------------------------
// Report Firmware
// -------------------------------------------------------------------------------------------------

/**
 * Query firmware Name and Version.
 *
 * ````
 * 0  START_SYSEX       (0xF0)
 * 1  queryFirmware     (0x79)
 * 2  END_SYSEX         (0xF7)
 * ````
 */
fun Firmata.sendReportFirmwareRequest() = sendRequest(REPORT_FIRMWARE_REQUEST)

private val REPORT_FIRMWARE_REQUEST = byteArrayOf(
    MIDI_START_SYSEX,
    SYSEX_REPORT_FIRMWARE,
    MIDI_END_SYSEX
)

fun Firmata.registerFirmwareReportReceiver(receiver: FirmwareReportReceiver) =
    registerReceiver(receiver)

fun Firmata.unregisterFirmwareReportReceiver(receiver: FirmwareReportReceiver) =
    unregisterReceiver(receiver)

typealias FirmwareReportReceiver = (FirmwareReport) -> Unit

/**
 * Receive Firmware Name and Version (after query).
 *
 * ````
 * 0  START_SYSEX       (0xF0)
 * 1  queryFirmware     (0x79)
 * 2  major version     (0-127)
 * 3  minor version     (0-127)
 * 4  first char of firmware name (LSB)
 * 5  first char of firmware name (MSB)
 * 6  second char of firmware name (LSB)
 * 7  second char of firmware name (MSB)
 * ... for as many bytes as it needs
 * N  END_SYSEX         (0xF7)
 * ````
 */
class FirmwareReport(data: ByteArray) : FirmataMessage {

    val majorVersion = data[2].toInt()
    val minorVersion = data[3].toInt()

    val name = String(data, 4, data.size - 5).replace(NULL_CHARACTER, "")

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_REPORT_FIRMWARE, ::FirmwareReport)
    }
}

// -------------------------------------------------------------------------------------------------
// String
// -------------------------------------------------------------------------------------------------

/**
 * Send a string.
 *
 * ````
 * 0  START_SYSEX        (0xF0)
 * 1  STRING_DATA        (0x71)
 * 2  first char LSB
 * 3  first char MSB
 * 4  second char LSB
 * 5  second char MSB
 * ... additional bytes up to half the buffer size - 3 (START_SYSEX, STRING_DATA, END_SYSEX)
 * N  END_SYSEX          (0xF7)
 * ````
 */
@Suppress("unused")
fun Firmata.sendStringMessage(string: String) {
    val bytes = (string + NULL_CHARACTER).toByteArray()
    val data = ByteArray(bytes.size * 2 + 3)

    data[0] = MIDI_START_SYSEX
    data[1] = SYSEX_STRING_DATA
    data[data.size - 1] = MIDI_END_SYSEX

    for (i in 0 until bytes.size) {
        data[i * 2 + 2] = (bytes[i].toInt() and 0x7F).toByte()
        data[i * 2 + 3] = ((bytes[i].toInt() ushr 7) and 0x7F).toByte()
    }

    sendRequest(data)
}

@Suppress("unused")
fun Firmata.registerStringMessageReceiver(receiver: StringMessageReceiver) =
    registerReceiver(receiver)

@Suppress("unused")
fun Firmata.unregisterStringMessageReceiver(receiver: StringMessageReceiver) =
    unregisterReceiver(receiver)

typealias StringMessageReceiver = (StringMessage) -> Unit

/**
 * Receive string.
 *
 * ````
 * 0  START_SYSEX        (0xF0)
 * 1  STRING_DATA        (0x71)
 * 2  first char LSB
 * 3  first char MSB
 * 4  second char LSB
 * 5  second char MSB
 * ... additional bytes up to half the buffer size - 3 (START_SYSEX, STRING_DATA, END_SYSEX)
 * N  END_SYSEX          (0xF7)
 * ````
 */
class StringMessage(data: ByteArray) : FirmataMessage {

    val string = String(data, 2, data.size - 3).replace(NULL_CHARACTER, "")

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_STRING_DATA, ::FirmwareReport)
    }
}

// -------------------------------------------------------------------------------------------------
// Sampling Interval
// -------------------------------------------------------------------------------------------------

/**
 * The sampling interval sets how often analog data and i2c data is reported to the client.
 * The default for the arduino implementation is 19ms. This means that every 19ms analog data
 * will be reported and any i2c devices with startUpdating continuous mode will be startUpdating.
 *
 * ````
 * 0  START_SYSEX        (0xF0)
 * 1  SAMPLING_INTERVAL  (0x7A)
 * 2  sampling interval on the millisecond time scale (LSB)
 * 3  sampling interval on the millisecond time scale (MSB)
 * 4  END_SYSEX          (0xF7)
 * ````
 */
fun Firmata.sendSamplingIntervalRequest(samplingInterval: Int) {
    val safeSamplingInterval = Math.min(Math.max(samplingInterval, 65535), 1)

    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_SAMPLING_INTERVAL,
            (safeSamplingInterval and 0x7F).toByte(),
            (safeSamplingInterval ushr 7).toByte(),
            MIDI_END_SYSEX
        )
    )
}