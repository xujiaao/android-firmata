package com.xujiaao.android.firmata.sample.led

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Led
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_led_simple.*
import org.jetbrains.anko.sdk25.coroutines.onCheckedChange
import org.jetbrains.anko.sdk25.coroutines.onClick

class SimpleLedActivity : SampleActivity() {

    private var mLed: Led? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_led_simple)

        action_switch.onCheckedChange { _, isChecked ->
            mLed?.clearAnimation()
            mLed?.setValue(isChecked)
        }

        action_blink.onClick {
            mLed?.blink(1000L)
        }
    }

    override fun onBoardConnected(board: Board) {
        mLed = board.Led(13)
    }

    override fun onBoardDisconnected() {
        mLed = null
    }
}