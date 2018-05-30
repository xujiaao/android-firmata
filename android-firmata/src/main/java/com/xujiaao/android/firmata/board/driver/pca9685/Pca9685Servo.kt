package com.xujiaao.android.firmata.board.driver.pca9685

import com.xujiaao.android.firmata.board.driver.BaseServo
import com.xujiaao.android.firmata.toolbox.map

@Suppress("FunctionName")
fun Pca9685.Servo(
    channel: Int,
    angleRange: IntRange? = null,
    pwmRange: IntRange? = null
): Pca9685Servo = Pca9685Servo(this, channel, angleRange, pwmRange)

class Pca9685Servo(
    private val pca9685: Pca9685,
    private val channel: Int,
    angleRange: IntRange? = null,
    pwmRange: IntRange? = null
) : BaseServo(pca9685, angleRange) {

    @Suppress("MemberVisibilityCanBePrivate")
    val pwmRange: IntRange = pwmRange ?: 544..2400

    override fun updateAngle(angle: Int) {
        pca9685.writeMicroseconds(channel, angle.map(angleRange, pwmRange))
    }
}