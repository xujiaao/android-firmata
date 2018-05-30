package com.xujiaao.android.firmata.sample.servo

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Servo
import com.xujiaao.android.firmata.sample.R
import kotlinx.android.synthetic.main.activity_servo.*

class SimpleServoActivity : ServoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tips.setImage(R.drawable.tips_servo_simple)
    }

    override fun onCreateServo(board: Board): Servo = board.Servo(10)
}