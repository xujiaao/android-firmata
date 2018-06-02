package com.xujiaao.android.firmata.board.driver

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.Peripheral
import com.xujiaao.android.firmata.board.state.ReportState
import com.xujiaao.android.firmata.protocol.feature.*

interface Pin : Peripheral {

    fun pinMode(mode: Int): Pin

    fun digitalWrite(value: Boolean): Pin

    fun digitalRead(callback: Pin.(value: Boolean) -> Unit): Pin

    fun stopDigitalReading(): Pin

    fun analogWrite(value: Int): Pin

    fun analogRead(callback: Pin.(value: Int) -> Unit): Pin

    fun stopAnalogReading(): Pin

    fun query(callback: Pin.(mode: Int, state: Int) -> Unit): Pin

    fun stopQuerying(): Pin
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultPin)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.Pin(pinAddress: Int): DefaultPin = DefaultPin(this, pinAddress)

@Suppress("FunctionName")
fun Board.Pin(pinName: String): DefaultPin = DefaultPin(this, pinName)

class DefaultPin(private val board: Board, val spec: Board.PinSpec) : BasePeripheral(board), Pin {

    private val mReportState = board.getSharedState(ReportState::class.java)

    constructor(board: Board, pinAddress: Int) : this(board, board.getPinSpec(pinAddress))
    constructor(board: Board, pinName: String) : this(board, board.getPinSpec(pinName))

    override fun onClose() {
        super.onClose()

        stopDigitalReading()
        stopAnalogReading()
        stopQuerying()
    }

    override fun pinMode(mode: Int): Pin = apply {
        board.io.sendSetPinModeRequest(spec.address, mode)
    }

    // ---------------------------------------------------------------------------------------------
    // Digital
    // ---------------------------------------------------------------------------------------------

    private var mDigitalReadCallback: (Pin.(Boolean) -> Unit)? = null

    override fun digitalWrite(value: Boolean): Pin = apply {
        board.io.sendSetDigitalPinValueRequest(spec.address, value)
    }

    override fun digitalRead(callback: Pin.(value: Boolean) -> Unit): Pin =
        configureDigitalReading(callback)

    override fun stopDigitalReading(): Pin =
        configureDigitalReading(null)

    private fun configureDigitalReading(callback: (Pin.(value: Boolean) -> Unit)?) = apply {
        mDigitalReadCallback = callback

        val reportId = "digital-startUpdating-${spec.port}"
        val reportEnabled = callback != null

        board.io.sendReportDigitalRequest(
            spec.port,
            mReportState.setReportEnabled(reportId, this, reportEnabled)
        )

        if (reportEnabled) {
            board.io.registerDigitalMessageReceiver(mDigitalMessageReceiver)
        } else {
            board.io.unregisterDigitalMessageReceiver(mDigitalMessageReceiver)
        }
    }

    private val mDigitalMessageReceiver: DigitalMessageReceiver = {
        if (it.port == spec.port) {
            val value = it.values and (1 shl (spec.address and 0x07)) != 0
            mDigitalReadCallback?.invoke(this, value)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Analog
    // ---------------------------------------------------------------------------------------------

    private var mAnalogReadCallback: (Pin.(Int) -> Unit)? = null

    override fun analogWrite(value: Int): Pin = apply {
        if (spec.address <= 15) {
            board.io.sendAnalogMessage(spec.address, value)
        } else {
            board.io.sendExtendedAnalogRequest(spec.address, value)
        }
    }

    override fun analogRead(callback: Pin.(value: Int) -> Unit): Pin =
        configureAnalogReading(callback)

    override fun stopAnalogReading(): Pin =
        configureAnalogReading(null)

    private fun configureAnalogReading(callback: (Pin.(value: Int) -> Unit)?): Pin = apply {
        mAnalogReadCallback = callback

        val reportId = "analog-startUpdating-${spec.address}"
        val reportEnabled = callback != null

        board.io.sendReportAnalogRequest(
            spec.address,
            mReportState.setReportEnabled(reportId, this, reportEnabled)
        )

        if (reportEnabled) {
            board.io.registerAnalogMessageReceiver(mAnalogMessageReceiver)
        } else {
            board.io.unregisterAnalogMessageReceiver(mAnalogMessageReceiver)
        }
    }

    private val mAnalogMessageReceiver: AnalogMessageReceiver = {
        if (it.pin == spec.analogChannel) {
            mAnalogReadCallback?.invoke(this, it.value)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Query
    // ---------------------------------------------------------------------------------------------

    private var mQueryCallback: (Pin.(Int, Int) -> Unit)? = null

    override fun query(callback: Pin.(mode: Int, state: Int) -> Unit): Pin =
        configureQuerying(callback)

    override fun stopQuerying(): Pin =
        configureQuerying(null)

    private fun configureQuerying(callback: (Pin.(mode: Int, state: Int) -> Unit)?): Pin = apply {
        mQueryCallback = callback

        if (callback != null) {
            board.io.sendPinStateQueryRequest(spec.address)
            board.io.registerPinStateResponseReceiver(mPinStateResponseReceiver)
        } else {
            board.io.unregisterPinStateResponseReceiver(mPinStateResponseReceiver)
        }
    }

    private val mPinStateResponseReceiver: PinStateResponseReceiver = {
        if (it.pin == spec.address) {
            mQueryCallback?.invoke(this, it.mode, it.state)
            stopQuerying()
        }
    }
}