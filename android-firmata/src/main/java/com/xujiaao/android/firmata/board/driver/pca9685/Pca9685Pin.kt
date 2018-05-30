package com.xujiaao.android.firmata.board.driver.pca9685

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.driver.Pin

@Suppress("FunctionName")
fun Pca9685.Pin(channel: Int): Pca9685Pin = Pca9685Pin(this, channel)

class Pca9685Pin(
    private val pca9685: Pca9685,
    private val channel: Int
) : BasePeripheral(pca9685), Pin {

    override fun pinMode(mode: Int): Pin = this

    override fun digitalWrite(value: Boolean): Pin = apply {
        pca9685.write(channel, value)
    }

    override fun digitalRead(callback: Pin.(value: Boolean) -> Unit): Pin {
        throw UnsupportedOperationException()
    }

    override fun stopDigitalReading(): Pin {
        throw UnsupportedOperationException()
    }

    override fun analogWrite(value: Int): Pin = apply {
        pca9685.writeDutyCircle(channel, value.toDouble() / 255.0)
    }

    override fun analogRead(callback: Pin.(value: Int) -> Unit): Pin {
        throw UnsupportedOperationException()
    }

    override fun stopAnalogReading(): Pin {
        throw UnsupportedOperationException()
    }

    override fun query(callback: Pin.(mode: Int, state: Int) -> Unit): Pin {
        throw UnsupportedOperationException()
    }

    override fun stopQuerying(): Pin {
        throw UnsupportedOperationException()
    }
}