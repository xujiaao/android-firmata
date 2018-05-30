package com.xujiaao.android.firmata.board.driver.pca9685

import com.xujiaao.android.firmata.board.BasePeripheralGroup
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.I2cDevice

const val PCA9685_ADDRESS = 0x40

const val PCA9685_FREQ_MIN = 24.0
const val PCA9685_FREQ_MAX = 1600.0
const val PCA9685_FREQ_DEFAULT = 60.0 // Analog servos run at ~60 Hz updates

private const val REG_MODE1 = 0x00
private const val REG_PRESCALE = 0xFE
private const val REG_BASE = 0x06

@Suppress("FunctionName")
fun Board.Pca9685(
    address: Int = PCA9685_ADDRESS,
    frequency: Double = PCA9685_FREQ_DEFAULT
): Pca9685 = Pca9685(this, address, frequency)

class Pca9685(
    board: Board,
    address: Int = PCA9685_ADDRESS,
    frequency: Double = PCA9685_FREQ_DEFAULT
) : BasePeripheralGroup(board) {

    private val mI2cDevice: I2cDevice = board.I2cDevice(address)
    private val mFrequency: Double = frequency.coerceIn(PCA9685_FREQ_MIN, PCA9685_FREQ_MAX)

    init {
        /**
         * Correct for overshoot in the frequency setting.
         * (See issue: https://github.com/adafruit/Adafruit-PWM-Servo-Driver-Library/issues/11)
         */
        val prescale = Math.round(25000000.0 / (4096.0 * mFrequency * .9) - 1.0).toByte()

        mI2cDevice.run {
            writeReg(REG_MODE1, 0x00.toByte()) // reset
            writeReg(REG_MODE1, 0x10.toByte()) // sleep
            writeReg(REG_PRESCALE, prescale) // set prescale
            writeReg(REG_MODE1, 0x00.toByte()) // wake up

            // wait 5 milliseconds for restart.
            try {
                Thread.sleep(5L)
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }

            writeReg(REG_MODE1, 0xA1.toByte()) // auto increment
        }
    }

    /**
     * Writes a value in microseconds (uS).
     */
    fun writeMicroseconds(channel: Int, value: Int) {
        write(channel, Math.round(value * mFrequency * 4096.0 / 1000_000.0).toInt())
    }

    fun writeDutyCircle(channel: Int, value: Double) {
        write(channel, Math.round(value * 4096.0).toInt())
    }

    /**
     * Sets the PWM output of one of the PCA9685 channels.
     *
     * @param value True, fully on. False, fully off.
     */
    fun write(channel: Int, value: Boolean) {
        write(channel, if (value) 4095 else 0)
    }

    /**
     * Sets the PWM output of one of the PCA9685 channels.
     *
     * @param value A value from 0 to 4095 inclusive.
     */
    fun write(channel: Int, value: Int) {
        when {
            value <= 0 -> setPwm(channel, 0, 4096)
            value >= 4095 -> setPwm(channel, 4096, 0)
            else -> setPwm(channel, 0, value)
        }
    }

    private fun setPwm(channel: Int, on: Int, off: Int) {
        mI2cDevice.writeReg(
            REG_BASE + 4 * channel,
            byteArrayOf(
                (on and 0xFF).toByte(),
                (on shr 8).toByte(),
                (off and 0xFF).toByte(),
                (off shr 8).toByte()
            )
        )
    }

    override fun onClose() {
        super.onClose()

        mI2cDevice.close()
    }
}