package com.xujiaao.android.firmata.sample.led

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Led
import com.xujiaao.android.firmata.board.driver.pca9685.Led
import com.xujiaao.android.firmata.board.driver.pca9685.Pca9685
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_led_pca9685.*
import org.jetbrains.anko.sdk27.coroutines.onCheckedChange
import org.jetbrains.anko.sdk27.coroutines.onClick

class Pca9685LedActivity : SampleActivity() {

    private var mLed: Led? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led_pca9685)

        action_switch.onCheckedChange { _, isChecked ->
            mLed?.clearAnimation()
            mLed?.setValue(isChecked)
        }

        action_blink.onClick {
            mLed?.blink(1000L)
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
        mLed = board.Pca9685().Led(0)
    }

    override fun onBoardDisconnected() {
        mLed = null
    }
}