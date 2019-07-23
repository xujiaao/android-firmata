package com.xujiaao.android.firmata.sample.led

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Led
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_led_pwm.*
import org.jetbrains.anko.sdk27.coroutines.onClick
import org.jetbrains.anko.sdk27.coroutines.onSeekBarChangeListener

class PwmLedActivity : SampleActivity() {

    private var mLed: Led? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led_pwm)

        brightness.onSeekBarChangeListener {
            onProgressChanged { _, progress, _ ->
                mLed?.clearAnimation()
                mLed?.setValue(progress.toFloat() / brightness.max)
            }
        }

        action_pulse.onClick {
            mLed?.pulse(1000)
        }

        action_fade_in.onClick {
            mLed?.fadeIn(1000)
        }

        action_fade_out.onClick {
            mLed?.fadeOut(1000)
        }
    }

    override fun onBoardConnected(board: Board) {
        mLed = board.Led(11)
    }

    override fun onBoardDisconnected() {
        mLed = null
    }
}