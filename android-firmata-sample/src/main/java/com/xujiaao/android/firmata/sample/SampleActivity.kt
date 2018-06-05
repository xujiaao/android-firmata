package com.xujiaao.android.firmata.sample

import android.app.Dialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.xujiaao.android.firmata.board.*
import com.xujiaao.android.firmata.transport.toTransport
import org.jetbrains.anko.*
import org.jetbrains.anko.appcompat.v7.Appcompat

private const val TAG = "SampleActivity"

abstract class SampleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportActionBar?.run {
            setDisplayHomeAsUpEnabled(true)
        }

        if (isAutoConnectEnabled()) {
            mBoardConnection.connect()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        mBoardConnection.disconnect()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.activity_smaple, menu)
        menu.findItem(R.id.connection)?.run {
            if (mBoardConnection.isConnecting()) {
                isVisible = false
            } else {
                isVisible = true
                setTitle(
                    if (mBoardConnection.isConnected()) {
                        R.string.menu_disconnect
                    } else {
                        R.string.menu_connect
                    }
                )
            }
        }

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()

                true
            }
            R.id.connection -> {
                if (mBoardConnection.isConnected()) {
                    mBoardConnection.disconnect()
                } else {
                    mBoardConnection.connect()
                }

                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }

    abstract fun onBoardConnected(board: Board)

    abstract fun onBoardDisconnected()

    private val mBoardConnection = object : BoardConnection {

        private var mProgressDialog: Dialog? = null

        fun connect() {
            val transportUri = getPreferredTransport()
            val transport = try {
                transportUri.toTransport(this@SampleActivity)
            } catch (e: IllegalArgumentException) {
                alert(
                    Appcompat,
                    e.message ?: "Invalid transport uri '$transportUri'",
                    getString(R.string.dialog_error_title)
                ) {
                    okButton { /* dismiss */ }
                }.show()

                return
            }

            connect(transport)
        }

        override fun onBoardConnecting() {
            mProgressDialog = indeterminateProgressDialog(R.string.dialog_connecting_message) {
                setCanceledOnTouchOutside(false)
                setOnCancelListener {
                    disconnect()
                }
            }

            invalidateOptionsMenu()
        }

        override fun onBoardConnected(board: Board) {
            dismissProgressDialog()
            invalidateOptionsMenu()

            Log.d(TAG, board.dumpProfile())

            this@SampleActivity.onBoardConnected(board)
        }

        override fun onBoardDisconnected(error: Throwable?) {
            dismissProgressDialog()
            invalidateOptionsMenu()

            if (error != null) {
                alert(
                    Appcompat,
                    getString(R.string.dialog_error_message_format, error.message?.capitalize()),
                    getString(R.string.dialog_error_title)
                ) {
                    noButton { /* dismiss */ }
                    yesButton {
                        connect()
                    }
                }.show()
            }

            this@SampleActivity.onBoardDisconnected()
        }

        private fun dismissProgressDialog() {
            mProgressDialog?.dismiss()
            mProgressDialog = null
        }
    }
}