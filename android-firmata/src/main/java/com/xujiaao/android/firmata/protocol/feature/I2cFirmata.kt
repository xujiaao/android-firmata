package com.xujiaao.android.firmata.protocol.feature

import com.xujiaao.android.firmata.protocol.*

// -------------------------------------------------------------------------------------------------
// I2c Config
// -------------------------------------------------------------------------------------------------

/**
 * I2c config. The optional Delay is for I2c devices that require a delay between when the register
 * is written to and the data in that register can be startUpdating.
 *
 * ````
 * 0  START_SYSEX (0xF0)
 * 1  I2C_CONFIG (0x78)
 * 2  Delay in microseconds (LSB) [optional]
 * 3  Delay in microseconds (MSB) [optional]
 * ... user defined for special cases, etc
 * n  END_SYSEX (0xF7)
 * ````
 */
fun Firmata.sendI2cConfig(delay: Int) {
    sendRequest(
        byteArrayOf(
            MIDI_START_SYSEX,
            SYSEX_I2C_CONFIG,
            (delay and 0xFF).toByte(),
            (delay ushr 8 and 0xFF).toByte(),
            MIDI_END_SYSEX
        )
    )
}

// -------------------------------------------------------------------------------------------------
// I2c Read/Write Request
// -------------------------------------------------------------------------------------------------

private const val I2C_MASK_WRITE = 0x00                   // B00000000
private const val I2C_MASK_READ = 0x08                    // B00001000
private const val I2C_MASK_READ_CONTINUOUSLY = 0x10       // B00010000
private const val I2C_MASK_STOP_READING = 0x18            // B00011000
private const val I2C_MASK_10BIT_ADDRESS_MODE = 0x20      // B00100000
private const val I2C_MASK_RESTART_TRANSMISSION = 0x40    // B01000000

/**
 * I2c write request.
 *
 * ````
 * 0  START_SYSEX (0xF0)
 * 1  I2C_REQUEST (0x76)
 * 2  slave address (LSB)
 * 3  slave address (MSB) + startUpdating/write and address mode bits
 * {bit 7: always 0}
 * {bit 6: auto restart transmission, 0 = stop (default), 1 = restart}
 * {bit 5: address mode, 1 = 10-bit mode}
 * {bits 4-3: startUpdating/write, 00 = write, 01 = startUpdating once, 10 = startUpdating continuously, 11 = stop reading}
 * {bits 2-0: slave address MSB in 10-bit mode, not used in 7-bit mode}
 * 4  data 0 (LSB)
 * 5  data 0 (MSB)
 * 6  data 1 (LSB)
 * 7  data 1 (MSB)
 * ...
 * n  END_SYSEX (0xF7)
 * ````
 */
fun Firmata.sendI2cWriteRequest(address: Int, data: ByteArray) =
    sendI2cRequest(address, I2C_MASK_WRITE, data)

/**
 * I2c startUpdating request.
 *
 * ````
 * 0  START_SYSEX (0xF0)
 * 1  I2C_REQUEST (0x76)
 * 2  slave address (LSB)
 * 3  slave address (MSB) + startUpdating/write and address mode bits
 * {bit 7: always 0}
 * {bit 6: auto restart transmission, 0 = stop (default), 1 = restart}
 * {bit 5: address mode, 1 = 10-bit mode}
 * {bits 4-3: startUpdating/write, 00 = write, 01 = startUpdating once, 10 = startUpdating continuously, 11 = stop reading}
 * {bits 2-0: slave address MSB in 10-bit mode, not used in 7-bit mode}
 * 4  data 0 (LSB)
 * 5  data 0 (MSB)
 * 6  data 1 (LSB)
 * 7  data 1 (MSB)
 * ...
 * n  END_SYSEX (0xF7)
 * ````
 */
fun Firmata.sendI2cReadRequest(
    address: Int,
    data: ByteArray,
    readContinuously: Boolean = false,
    autoRestartTransmission: Boolean = false
) {
    var mode = if (readContinuously) {
        I2C_MASK_READ
    } else {
        I2C_MASK_READ_CONTINUOUSLY
    }

    if (autoRestartTransmission) {
        mode = mode or I2C_MASK_RESTART_TRANSMISSION
    }

    sendI2cRequest(address, mode, data)
}

/**
 * Stop I2c reading.
 *
 * ````
 * 0  START_SYSEX (0xF0)
 * 1  I2C_REQUEST (0x76)
 * 2  slave address (LSB)
 * 3  slave address (MSB) + startUpdating/write and address mode bits
 * {bit 7: always 0}
 * {bit 6: auto restart transmission, 0 = stop (default), 1 = restart}
 * {bit 5: address mode, 1 = 10-bit mode}
 * {bits 4-3: startUpdating/write, 00 = write, 01 = startUpdating once, 10 = startUpdating continuously, 11 = stop reading}
 * {bits 2-0: slave address MSB in 10-bit mode, not used in 7-bit mode}
 * 4  data 0 (LSB)
 * 5  data 0 (MSB)
 * 6  data 1 (LSB)
 * 7  data 1 (MSB)
 * ...
 * n  END_SYSEX (0xF7)
 * ````
 */
fun Firmata.sendI2cStopReadingRequest(address: Int) =
    sendI2cRequest(address, I2C_MASK_STOP_READING, null)

private fun Firmata.sendI2cRequest(address: Int, mode: Int, data: ByteArray?) {
    val addressLsb = address and 0x7F
    val addressMsb = if (address > 0x7F) {
        (address ushr 7) and 0x07 or I2C_MASK_10BIT_ADDRESS_MODE
    } else {
        0x00
    }

    sendRequest(ByteArray((data?.size ?: 0) * 2 + 5).apply {
        this[0] = MIDI_START_SYSEX
        this[1] = SYSEX_I2C_REQUEST
        this[2] = addressLsb.toByte()
        this[3] = (addressMsb or mode).toByte()

        data?.let {
            for (i in 0 until it.size) {
                this[i * 2 + 4] = (it[i].toInt() and 0x7F).toByte()
                this[i * 2 + 5] = ((it[i].toInt() ushr 7) and 0x7F).toByte()
            }
        }

        this[size - 1] = MIDI_END_SYSEX
    })
}

fun Firmata.registerI2cReplyReceiver(receiver: I2cReplyReceiver) {
    registerReceiver(receiver)
}

fun Firmata.unregisterI2cReplyReceiver(receiver: I2cReplyReceiver) {
    unregisterReceiver(receiver)
}

typealias I2cReplyReceiver = (I2cReply) -> Unit

/**
 * I2c reply.
 *
 * ````
 * 0  START_SYSEX (0xF0)
 * 1  I2C_REPLY (0x77)
 * 2  slave address (LSB)
 * 3  slave address (MSB)
 * 4  register (LSB)
 * 5  register (MSB)
 * 6  data 0 (LSB)
 * 7  data 0 (MSB)
 * ...
 * n  END_SYSEX (0XF7)
 * ````
 */
class I2cReply(data: ByteArray) : FirmataMessage {

    val address = (data[2].toInt() and 0x7F) or (data[3].toInt() and 0x7F shl 7)
    val register = (data[4].toInt() and 0x7F) or (data[5].toInt() and 0x7F shl 7)
    val data = ByteArray((data.size - 7) / 2).apply {
        for (i in 0 until size) {
            val offset = i * 2 + 6
            this[i] = (data[offset].toInt() or (data[offset + 1].toInt() ushr 7)).toByte()
        }
    }

    companion object {

        @Suppress("unused")
        @JvmField
        val PARSER = createSysexMessageParser(SYSEX_I2C_REPLY, ::I2cReply)
    }
}