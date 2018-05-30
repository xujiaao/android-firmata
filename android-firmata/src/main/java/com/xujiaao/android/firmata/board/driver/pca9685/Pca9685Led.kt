package com.xujiaao.android.firmata.board.driver.pca9685

import com.xujiaao.android.firmata.board.driver.BaseLed

@Suppress("FunctionName")
fun Pca9685.Led(channel: Int): Pca9685Led = Pca9685Led(this, channel)

class Pca9685Led(private val pca9685: Pca9685, private val channel: Int) : BaseLed(pca9685) {

    override fun setValue(value: Boolean) {
        pca9685.write(channel, value)
    }

    override fun setValue(value: Float) {
        pca9685.writeDutyCircle(channel, value.toDouble())
    }
}