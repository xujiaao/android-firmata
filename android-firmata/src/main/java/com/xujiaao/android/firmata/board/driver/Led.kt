package com.xujiaao.android.firmata.board.driver

import android.animation.ObjectAnimator
import com.xujiaao.android.firmata.board.*
import com.xujiaao.android.firmata.protocol.PIN_MODE_OUTPUT
import com.xujiaao.android.firmata.protocol.PIN_MODE_PWM

interface Led : Peripheral {

    fun setValue(value: Boolean)

    fun setValue(value: Float)

    fun blink(interval: Long)

    fun fadeIn(duration: Long)

    fun fadeOut(duration: Long)

    fun pulse(duration: Long)
}

// -------------------------------------------------------------------------------------------------
// Implementation (BaseLed)
// -------------------------------------------------------------------------------------------------

abstract class BaseLed(parent: PeripheralGroup) : BasePeripheral(parent), Led {

    override fun blink(interval: Long) {
        animate(
            frames = arrayOf(false, true),
            interval = interval,
            repeat = true,
            reverse = false,
            update = {
                setValue(it)
            }
        )
    }

    override fun fadeIn(duration: Long) {
        animate(ObjectAnimator.ofFloat(this, "value", 0F, 1F).setDuration(duration))
    }

    override fun fadeOut(duration: Long) {
        animate(ObjectAnimator.ofFloat(this, "value", 1F, 0F).setDuration(duration))
    }

    override fun pulse(duration: Long) {
        animate(ObjectAnimator.ofFloat(this, "value", 0F, 1F).apply {
            this.duration = duration
            this.repeatMode = ObjectAnimator.REVERSE
            this.repeatCount = ObjectAnimator.INFINITE
        })
    }
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultLed)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.Led(pinAddress: Int): DefaultLed = DefaultLed(this, pinAddress)

@Suppress("FunctionName")
fun Board.Led(pinName: String): DefaultLed = DefaultLed(this, pinName)

class DefaultLed private constructor(board: Board, private val pin: DefaultPin) : BaseLed(board) {

    private val mPwm = !pin.spec.isAnalog && pin.spec.pinModes.contains(PIN_MODE_PWM)

    constructor(board: Board, pinAddress: Int) : this(board, board.Pin(pinAddress))
    constructor(board: Board, pinName: String) : this(board, board.Pin(pinName))

    init {
        if (mPwm) {
            pin.pinMode(PIN_MODE_PWM)
        } else {
            pin.pinMode(PIN_MODE_OUTPUT)
        }
    }

    override fun setValue(value: Boolean) {
        if (mPwm) {
            pin.analogWrite(if (value) 255 else 0)
        } else {
            pin.digitalWrite(value)
        }
    }

    override fun setValue(value: Float) {
        if (mPwm) {
            pin.analogWrite((value * 255F).toInt())
        } else {
            pin.digitalWrite(value >= 127F)
        }
    }

    override fun onClose() {
        super.onClose()

        pin.close()
    }
}