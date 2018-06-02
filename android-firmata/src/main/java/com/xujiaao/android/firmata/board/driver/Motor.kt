package com.xujiaao.android.firmata.board.driver

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.Peripheral
import com.xujiaao.android.firmata.board.PeripheralGroup
import com.xujiaao.android.firmata.protocol.PIN_MODE_OUTPUT
import com.xujiaao.android.firmata.protocol.PIN_MODE_PWM
import kotlin.math.absoluteValue

interface Motor : Peripheral {

    var speed: Float

    fun forward(speed: Float)

    fun reverse(speed: Float)

    fun stop()
}

// -------------------------------------------------------------------------------------------------
// Implementation (BaseMotor)
// -------------------------------------------------------------------------------------------------

abstract class BaseMotor<T>(
    parent: PeripheralGroup,
    private val pwm: T,
    private val dir: T?,
    private val cdir: T?,
    invertPwm: Boolean?,
    threshold: Float?
) : BasePeripheral(parent), Motor {

    private val mInvertPwm = invertPwm ?: true
    private val mThreshold = threshold ?: .1F

    override var speed: Float = 0F
        set(value) {
            val speed = (if (value.absoluteValue > mThreshold) value else 0F).coerceIn(-1F, 1F)
            field = speed

            if (dir != null && cdir != null) { // 3 pins motor.
                updatePin(dir, speed > 0F)
                updatePin(cdir, speed < 0F)

                updatePin(pwm, speed.absoluteValue)
            } else {
                val dir = dir ?: cdir
                if (dir != null) { // 2 pins motor.
                    updatePin(dir, speed > 0F)

                    if (mInvertPwm && speed > 0F) {
                        updatePin(pwm, 1F - speed)
                    } else {
                        updatePin(pwm, speed.absoluteValue)
                    }
                } else { // non-directional motor.
                    updatePin(pwm, if (speed > 0F) speed.absoluteValue else 0F)
                }
            }
        }

    override fun forward(speed: Float) {
        this.speed = speed
    }

    override fun reverse(speed: Float) {
        this.speed = -speed
    }

    override fun stop() {
        this.speed = 0F
    }

    override fun onClose() {
        super.onClose()

        stop()

        closePin(pwm)

        if (dir != null) {
            closePin(dir)
        }

        if (cdir != null) {
            closePin(cdir)
        }
    }

    private fun updateSpeed(speed: Float) {
        val safeSpeed = (if (speed.absoluteValue > mThreshold) speed else 0F).coerceIn(-1F, 1F)

        if (dir != null && cdir != null) { // 3 pins motor.
            updatePin(dir, safeSpeed > 0F)
            updatePin(cdir, safeSpeed < 0F)

            updatePin(pwm, safeSpeed.absoluteValue)
        } else {
            val dir = dir ?: cdir
            if (dir != null) { // 2 pins motor.
                updatePin(dir, safeSpeed > 0F)

                if (mInvertPwm && safeSpeed > 0F) {
                    updatePin(pwm, 1F - safeSpeed)
                } else {
                    updatePin(pwm, safeSpeed.absoluteValue)
                }
            } else { // non-directional motor.
                updatePin(pwm, if (safeSpeed > 0F) safeSpeed.absoluteValue else 0F)
            }
        }
    }

    protected abstract fun updatePin(pin: T, value: Float)

    protected abstract fun updatePin(pin: T, value: Boolean)

    protected abstract fun closePin(pin: T)
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultMotor)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.Motor(
    pwm: Int,
    dir: Int = -1,
    cdir: Int = -1,
    invertPwm: Boolean? = null,
    threshold: Float? = null
): DefaultMotor = DefaultMotor(this, pwm, dir, cdir, invertPwm, threshold)

@Suppress("FunctionName")
fun Board.Motor(
    pwm: String,
    dir: String? = null,
    cdir: String? = null,
    invertPwm: Boolean? = null,
    threshold: Float? = null
): DefaultMotor = DefaultMotor(this, pwm, dir, cdir, invertPwm, threshold)

class DefaultMotor private constructor(
    board: Board,
    pwm: Pin,
    dir: Pin?,
    cdir: Pin?,
    invertPwm: Boolean?,
    threshold: Float?
) : BaseMotor<Pin>(board, pwm, dir, cdir, invertPwm, threshold) {

    constructor(
        board: Board,
        pwm: Int,
        dir: Int = -1,
        cdir: Int = -1,
        invertPwm: Boolean? = null,
        threshold: Float? = null
    ) : this(
        board,
        board.Pin(pwm),
        if (dir >= 0) board.Pin(dir) else null,
        if (cdir >= 0) board.Pin(cdir) else null,
        invertPwm,
        threshold
    )

    constructor(
        board: Board,
        pwm: String,
        dir: String? = null,
        cdir: String? = null,
        invertPwm: Boolean? = null,
        threshold: Float? = null
    ) : this(
        board,
        board.Pin(pwm),
        if (dir != null) board.Pin(dir) else null,
        if (cdir != null) board.Pin(cdir) else null,
        invertPwm,
        threshold
    )

    init {
        pwm.pinMode(PIN_MODE_PWM)
        dir?.pinMode(PIN_MODE_OUTPUT)
        cdir?.pinMode(PIN_MODE_OUTPUT)
    }

    override fun updatePin(pin: Pin, value: Float) {
        pin.analogWrite((value * 255F).toInt())
    }

    override fun updatePin(pin: Pin, value: Boolean) {
        pin.digitalWrite(value)
    }

    override fun closePin(pin: Pin) {
        pin.close()
    }
}