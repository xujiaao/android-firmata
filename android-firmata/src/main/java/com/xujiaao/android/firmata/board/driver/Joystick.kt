package com.xujiaao.android.firmata.board.driver

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.Peripheral
import com.xujiaao.android.firmata.protocol.PIN_MODE_ANALOG

interface Joystick : Peripheral {

    val x: Float

    val y: Float

    fun startUpdating(callback: (Joystick.(x: Float, y: Float) -> Unit)? = null)

    fun stopUpdating()
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultJoystick)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.Joystick(
    x: String,
    y: String,
    invertX: Boolean = false,
    invertY: Boolean = false
): DefaultJoystick = DefaultJoystick(this, x, y, invertX, invertY)

class DefaultJoystick(
    private val board: Board,
    private val pinX: DefaultPin,
    private val pinY: DefaultPin,
    invertX: Boolean,
    invertY: Boolean
) : BasePeripheral(board), Joystick {

    private val mBaseX = toBase(pinX)
    private val mBaseY = toBase(pinY)

    private var mX = 0F
    private var mY = 0F
    private var mUpdatingCallback: (Joystick.(Float, Float) -> Unit)? = null

    override val x: Float get() = mX
    override val y: Float get() = mY

    constructor(
        board: Board,
        x: String,
        y: String,
        invertX: Boolean = false,
        invertY: Boolean = false
    ) : this(board, board.Pin(x), board.Pin(y), invertX, invertY)

    init {
        pinX.pinMode(PIN_MODE_ANALOG)
        pinY.pinMode(PIN_MODE_ANALOG)
    }

    override fun startUpdating(callback: (Joystick.(x: Float, y: Float) -> Unit)?) {
        mUpdatingCallback = callback

        pinX.analogRead(mAnalogReadCallback)
        pinY.analogRead(mAnalogReadCallback)
    }

    override fun stopUpdating() {
        mUpdatingCallback = null

        pinX.stopAnalogReading()
        pinY.stopAnalogReading()
    }

    override fun onClose() {
        super.onClose()

        stopUpdating()

        pinX.close()
        pinY.close()
    }

    private fun toBase(pin: DefaultPin): Float {
        val resolution = board.getPinResolution(pin.spec.address, PIN_MODE_ANALOG)
        if (resolution <= 0) {
            return 0F
        }

        return (((1 shl resolution) - 1) / 2).toFloat()
    }


    private fun toAxis(value: Int, base: Float, invert: Boolean): Float {
        return if (base != 0F) {
            ((value - base) / base) * (if (invert) 1 else -1)
        } else 0F
    }

    private val mAnalogReadCallback: Pin.(Int) -> Unit = { value ->
        if (this === pinX) {
            mX = toAxis(value, mBaseX, invertX)
        } else {
            mY = toAxis(value, mBaseY, invertY)
        }

        mUpdatingCallback?.invoke(this@DefaultJoystick, mX, mY)
    }
}