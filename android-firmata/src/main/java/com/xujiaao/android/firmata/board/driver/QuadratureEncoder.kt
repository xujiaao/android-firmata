package com.xujiaao.android.firmata.board.driver

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.Peripheral
import com.xujiaao.android.firmata.board.state.ReportState
import com.xujiaao.android.firmata.protocol.feature.*

interface QuadratureEncoder : Peripheral {

    val position: Int

    fun startUpdating(callback: (QuadratureEncoder.(position: Int) -> Unit)? = null)

    fun stopUpdating()

    fun reset()
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultQuadratureEncoder)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.QuadratureEncoder(id: Int, pinA: Int, pinB: Int): DefaultQuadratureEncoder =
    DefaultQuadratureEncoder(this, id, pinA, pinB)

@Suppress("FunctionName")
fun Board.QuadratureEncoder(id: Int, pinA: String, pinB: String): DefaultQuadratureEncoder =
    DefaultQuadratureEncoder(this, id, pinA, pinB)

class DefaultQuadratureEncoder(
    private val board: Board,
    private val id: Int,
    pinA: Board.PinSpec,
    pinB: Board.PinSpec
) : BasePeripheral(board), QuadratureEncoder {

    private var mPosition = 0

    private val mReportState = board.getSharedState(ReportState::class.java)
    private var mUpdatingCallback: (QuadratureEncoder.(Int) -> Unit)? = null

    override val position: Int get() = mPosition

    constructor(board: Board, id: Int, pinA: Int, pinB: Int) :
            this(board, id, board.getPinSpec(pinA), board.getPinSpec(pinB))

    constructor(board: Board, id: Int, pinA: String, pinB: String) :
            this(board, id, board.getPinSpec(pinA), board.getPinSpec(pinB))

    init {
        board.io.sendAttachEncoderRequest(id, pinA.address, pinB.address)
    }

    override fun startUpdating(callback: (QuadratureEncoder.(position: Int) -> Unit)?) {
        mUpdatingCallback = callback

        board.io.registerEncoderResponseReceiver(mEncoderResponseReceiver)
        board.io.sendAutoReportEncoderRequest(
            mReportState.setReportEnabled("encoders", this, true)
        )
    }

    override fun stopUpdating() {
        mUpdatingCallback = null

        board.io.unregisterEncoderResponseReceiver(mEncoderResponseReceiver)
        board.io.sendAutoReportEncoderRequest(
            mReportState.setReportEnabled("encoders", this, false)
        )
    }

    override fun reset() {
        mPosition = 0

        board.io.sendResetEncoderPositionRequest(id)
    }

    override fun onClose() {
        super.onClose()

        stopUpdating()

        board.io.sendDetachEncoderRequest(id)
    }

    private val mEncoderResponseReceiver: EncoderResponseReceiver = {
        it.getPosition(id).let {
            mPosition = it
            mUpdatingCallback?.invoke(this, it)
        }
    }
}