package com.xujiaao.android.firmata.sample.servo

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Servo
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_servo.*
import org.jetbrains.anko.sdk25.coroutines.onClick

abstract class ServoActivity : SampleActivity() {

    private var mServo: Servo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servo)

        action_min.onClick {
            mServo?.gotoMin(duration.progress.toLong())
        }

        action_max.onClick {
            mServo?.gotoMax(duration.progress.toLong())
        }

        action_center.onClick {
            mServo?.gotoCenter(duration.progress.toLong())
        }

        action_sweep.onClick {
            mServo?.sweep()
        }
    }

    override fun onBoardConnected(board: Board) {
        mServo = onCreateServo(board)
    }

    override fun onBoardDisconnected() {
        mServo = null
    }

    protected abstract fun onCreateServo(board: Board): Servo
}