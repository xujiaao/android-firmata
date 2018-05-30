package com.xujiaao.android.firmata.sample.servo

import android.os.Bundle
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.driver.Servo
import com.xujiaao.android.firmata.board.driver.pca9685.Pca9685
import com.xujiaao.android.firmata.board.driver.pca9685.Servo
import com.xujiaao.android.firmata.sample.R
import kotlinx.android.synthetic.main.activity_servo.*

class Pca9685ServoActivity : ServoActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tips.setImage(R.drawable.tips_servo_pca9685)
    }

    override fun onCreateServo(board: Board): Servo = board.Pca9685().Servo(0)
}