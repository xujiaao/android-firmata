package com.xujiaao.android.firmata.board.driver

import android.animation.ObjectAnimator
import com.xujiaao.android.firmata.board.*
import com.xujiaao.android.firmata.protocol.PIN_MODE_SERVO
import com.xujiaao.android.firmata.protocol.feature.sendServoConfig
import com.xujiaao.android.firmata.toolbox.map

interface Servo : Peripheral {

    val angleRange: IntRange

    var angle: Int

    fun goto(angle: Int, duration: Long = 0L)

    fun gotoMin(duration: Long = 0L)

    fun gotoMax(duration: Long = 0L)

    fun gotoCenter(duration: Long = 0L)

    fun sweep(range: IntRange = angleRange, duration: Long = 1000L)
}

// -------------------------------------------------------------------------------------------------
// Implementation (BaseServo)
// -------------------------------------------------------------------------------------------------

abstract class BaseServo(
    parent: PeripheralGroup,
    angleRange: IntRange?
) : BasePeripheral(parent), Servo {

    override val angleRange: IntRange = angleRange ?: 0..180

    override var angle: Int = -1
        set(value) = value.coerceIn(angleRange).let {
            field = it
            updateAngle(it)
        }

    override fun goto(angle: Int, duration: Long) {
        if (duration <= 0) {
            clearAnimation()

            this.angle = angle
        } else {
            val dst = angle.coerceIn(angleRange)
            if (this.angle != dst) {
                animate(ObjectAnimator.ofInt(this, "angle", this.angle, dst).setDuration(duration))
            }
        }
    }

    override fun gotoMin(duration: Long) =
        goto(angleRange.first, duration)

    override fun gotoMax(duration: Long) =
        goto(angleRange.last, duration)

    override fun gotoCenter(duration: Long) =
        goto((angleRange.first + angleRange.last) / 2, duration)

    override fun sweep(range: IntRange, duration: Long) =
        animate(ObjectAnimator.ofInt(this, "angle", range.first, range.last).apply {
            this.duration = duration
            this.repeatMode = ObjectAnimator.REVERSE
            this.repeatCount = ObjectAnimator.INFINITE
        })

    abstract fun updateAngle(angle: Int)
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultLed)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.Servo(
    pinAddress: Int,
    angleRange: IntRange? = null,
    pwmRange: IntRange? = null
): DefaultServo = DefaultServo(this, pinAddress, angleRange, pwmRange)

@Suppress("FunctionName")
fun Board.Servo(
    pinName: String,
    angleRange: IntRange? = null,
    pwmRange: IntRange? = null
): DefaultServo = DefaultServo(this, pinName, angleRange, pwmRange)

class DefaultServo private constructor(
    board: Board,
    private val pin: DefaultPin,
    angleRange: IntRange? = null,
    pwmRange: IntRange? = null
) : BaseServo(board, angleRange) {

    private val mPwmRange: IntRange? = pwmRange

    constructor(
        board: Board,
        pinAddress: Int,
        angleRange: IntRange? = null,
        pwmRange: IntRange? = null
    ) : this(board, board.Pin(pinAddress), angleRange, pwmRange)

    constructor(
        board: Board,
        pinName: String,
        angleRange: IntRange? = null,
        pwmRange: IntRange? = null
    ) : this(board, board.Pin(pinName), angleRange, pwmRange)

    init {
        pin.pinMode(PIN_MODE_SERVO)

        mPwmRange?.let {
            board.io.sendServoConfig(pin.spec.address, it.first, it.last)
        }
    }

    override fun updateAngle(angle: Int) {
        val pwmRange = mPwmRange
        if (pwmRange != null) {
            pin.analogWrite(angle.map(angleRange, pwmRange))
        } else {
            pin.analogWrite(angle)
        }
    }

    override fun onClose() {
        super.onClose()

        pin.close()
    }
}