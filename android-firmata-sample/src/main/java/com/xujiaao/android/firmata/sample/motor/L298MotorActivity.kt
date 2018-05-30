package com.xujiaao.android.firmata.sample.motor

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.sample.R
import kotlinx.android.synthetic.main.activity_motor.*

class L298MotorActivity : MotorActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tips.setImage(R.drawable.tips_motor_l298)
    }

    override fun onCreateMotor(board: Board): Motor =
        board.Motor(pwm = 8, dir = 9, invertPwm = true)
}