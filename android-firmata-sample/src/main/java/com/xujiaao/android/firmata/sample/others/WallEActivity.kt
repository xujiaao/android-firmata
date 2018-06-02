package com.xujiaao.android.firmata.sample.others

import android.os.Bundle
import android.util.Log
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.BoardWrapper
import com.xujiaao.android.firmata.board.driver.Motor
import com.xujiaao.android.firmata.board.dumpProfile
import com.xujiaao.android.firmata.sample.R
import com.xujiaao.android.firmata.sample.SampleActivity
import kotlinx.android.synthetic.main.activity_others_wall_e.*

private const val SP_NAME = "sample_wall_e"
private const val SP_KEY_SCALE_L = "scale_l"
private const val SP_KEY_SCALE_R = "scale_r"

private const val DEFAULT_SCALE_L = 1F
private const val DEFAULT_SCALE_R = 1F

class WallEActivity : SampleActivity() {

    private var mWallE: WallEBoard? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_others_wall_e)

        val sp = getSharedPreferences(SP_NAME, MODE_PRIVATE)
        scale_l.progress = (sp.getFloat(SP_KEY_SCALE_L, DEFAULT_SCALE_L) * scale_l.max).toInt()
        scale_r.progress = (sp.getFloat(SP_KEY_SCALE_R, DEFAULT_SCALE_R) * scale_r.max).toInt()

        joystick.setOnMoveListener { angle, strength ->
            mWallE?.setMotor(
                radians = Math.toRadians(angle.toDouble()),
                strength = strength / 100.0,
                scaleL = scale_l.progress.toDouble() / scale_l.max,
                scaleR = scale_r.progress.toDouble() / scale_r.max
            )
        }
    }

    override fun onPause() {
        super.onPause()

        getSharedPreferences(SP_NAME, MODE_PRIVATE).edit()
            .putFloat(SP_KEY_SCALE_L, scale_l.progress.toFloat() / scale_l.max)
            .putFloat(SP_KEY_SCALE_R, scale_r.progress.toFloat() / scale_r.max)
            .apply()
    }

    override fun onBoardConnected(board: Board) {
        mWallE = WallEBoard(board)
    }

    override fun onBoardDisconnected() {
        mWallE = null
    }
}

private class WallEBoard(board: Board) : BoardWrapper(board) {

    private val mMotorL = Motor(pwm = 6, dir = 7, cdir = 8)
    private val mMotorR = Motor(pwm = 11, dir = 10, cdir = 9)

    init {
        Log.d("WallE", dumpProfile())
    }

    fun setMotor(radians: Double, strength: Double, scaleL: Double, scaleR: Double) {
        val x = Math.cos(radians)
        val y = Math.sin(radians)

        mMotorL.speed = ((y - x) * strength * scaleL).toFloat()
        mMotorR.speed = ((y + x) * strength * scaleR).toFloat()
    }
}