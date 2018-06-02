package com.xujiaao.android.firmata.sample.motor

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.board.driver.Pin
import com.xujiaao.android.firmata.protocol.PIN_MODE_OUTPUT
import com.xujiaao.android.firmata.sample.R
import kotlinx.android.synthetic.main.activity_motor.*

class Tb6612MotorActivity : MotorActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tips.setTips(R.array.tips_untested)
        tips.setImage(R.drawable.tips_motor_tb6612)
    }

    override fun onBoardConnected(board: Board) {
        super.onBoardConnected(board)

        board.Pin(13).pinMode(PIN_MODE_OUTPUT).digitalWrite(true) // enable STBY.
    }

    override fun onCreateMotor(board: Board): Motor =
        board.Motor(pwm = 6, dir = 7, cdir = 8)
}