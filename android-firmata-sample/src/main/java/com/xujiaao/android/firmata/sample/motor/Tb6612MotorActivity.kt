package com.xujiaao.android.firmata.sample.motor

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.sample.R
import kotlinx.android.synthetic.main.activity_motor.*

class Tb6612MotorActivity : MotorActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tips.setImage(R.drawable.tips_motor_tb6612)
    }

    override fun onCreateMotor(board: Board): Motor = board.Motor("A5", "A4", "A3")
}