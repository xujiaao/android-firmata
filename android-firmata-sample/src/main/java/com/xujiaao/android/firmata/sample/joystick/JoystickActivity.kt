package com.xujiaao.android.firmata.sample.joystick

import android.annotation.SuppressLint
import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Joystick
import com.xujiaao.android.firmata.board.driver.Led
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_joystick.*

class JoystickActivity : SampleActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_joystick)
        updateLabels(0F, 0F)
    }

    override fun onBoardConnected(board: Board) {
        board.Led(3).pulse(1000L)
        board.Joystick("A0", "A1").apply {
            startUpdating()
            loop(100, {
                updateLabels(x, y)
            })
        }
    }

    override fun onBoardDisconnected() = Unit

    @SuppressLint("SetTextI18n")
    private fun updateLabels(x: Float, y: Float) {
        label_x.text = "X: $x"
        label_y.text = "Y: $y"
    }
}