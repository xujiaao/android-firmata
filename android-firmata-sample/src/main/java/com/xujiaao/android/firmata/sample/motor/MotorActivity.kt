package com.xujiaao.android.firmata.sample.motor

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_motor.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onSeekBarChangeListener

abstract class MotorActivity : SampleActivity() {

    private var mMotor: Motor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_motor)

        speed.onSeekBarChangeListener {
            onProgressChanged { _, progress, _ ->
                val speed = progress.toFloat() / speed.max
                mMotor?.let {
                    it.speed = speed * (if (it.speed >= 0F) 1F else -1F)
                }
            }
        }

        action_forward.onClick {
            mMotor?.forward(speed.progress.toFloat() / speed.max)
        }

        action_backward.onClick {
            mMotor?.reverse(speed.progress.toFloat() / speed.max)
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