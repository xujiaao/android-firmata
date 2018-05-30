package com.xujiaao.android.firmata.sample.motor

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_motor.*
import org.jetbrains.anko.sdk25.coroutines.onClick

abstract class MotorActivity : SampleActivity() {

    private var mMotor: Motor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motor)

        action_forward.onClick {
            mMotor?.start(speed.progress.toFloat() / speed.max)
        }

        action_backward.onClick {
            mMotor?.start(speed.progress.toFloat() / speed.max)
        }

        action_stop.onClick {
            mMotor?.stop()
        }
    }

    override fun onBoardConnected(board: Board) {
        mMotor = onCreateMotor(board)
    }

    override fun onBoardDisconnected() {
        mMotor = null
    }

    protected abstract fun onCreateMotor(board: Board): Motor
}