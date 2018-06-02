package com.xujiaao.android.firmata.sample.servo

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Servo
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import com.xujiaao.android.firmata.toolbox.map
import kotlinx.android.synthetic.main.activity_servo.*
import org.jetbrains.anko.sdk25.coroutines.onClick
import org.jetbrains.anko.sdk25.coroutines.onSeekBarChangeListener

private const val ANIMATION_DURATION = 500L

abstract class ServoActivity : SampleActivity() {

    private var mServo: Servo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_servo)

        angle.onSeekBarChangeListener {
            onStopTrackingTouch {
                mServo?.let {
                    val value = angle.progress.map(0..angle.max, it.angleRange)
                    if (animation_enabled.isChecked) {
                        it.goto(value, ANIMATION_DURATION)
                    } else {
                        it.goto(value)
                    }
                }
            }
        }

        action_min.onClick {
            if (animation_enabled.isChecked) {
                mServo?.gotoMin(ANIMATION_DURATION)
            } else {
                mServo?.gotoMin()
            }
        }

        action_max.onClick {
            if (animation_enabled.isChecked) {
                mServo?.gotoMax(ANIMATION_DURATION)
            } else {
                mServo?.gotoMax()
            }
        }

        action_center.onClick {
            if (animation_enabled.isChecked) {
                mServo?.gotoCenter(ANIMATION_DURATION)
            } else {
                mServo?.gotoCenter()
            }
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