package com.xujiaao.android.firmata.board.driver.pca9685

import com.xujiaao.android.firmata.board.driver.BaseMotor

@Suppress("FunctionName")
fun Pca9685.Motor(
    pwm: Int,
    dir: Int? = null,
    cdir: Int? = null,
    invertPwm: Boolean? = null,
    threshold: Float? = null
): Pca9685Motor = Pca9685Motor(this, pwm, dir, cdir, invertPwm, threshold)

class Pca9685Motor(
    private val pca9685: Pca9685,
    pwm: Int,
    dir: Int? = null,
    cdir: Int? = null,
    invertPwm: Boolean? = null,
    threshold: Float? = null
) : BaseMotor<Int>(pca9685, pwm, dir, cdir, invertPwm, threshold) {

    override fun updatePin(pin: Int, value: Float) {
        pca9685.writeDutyCircle(pin, value.toDouble())
    }

    override fun updatePin(pin: Int, value: Boolean) {
        pca9685.write(pin, value)
    }

    override fun closePin(pin: Int) = Unit
}