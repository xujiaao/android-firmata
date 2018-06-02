package com.xujiaao.android.firmata.board.driver

import com.xujiaao.android.firmata.board.BasePeripheral
import com.xujiaao.android.firmata.board.Board
import com.xujiaao.android.firmata.board.Peripheral
import com.xujiaao.android.firmata.protocol.feature.*
import com.xujiaao.android.firmata.toolbox.ListenerRegistry

interface I2cDevice : Peripheral {

    val address: Int

    var readDelay: Int

    var autoRestartTransmission: Boolean

    fun write(data: Byte)

    fun write(data: ByteArray)

    fun writeReg(register: Int, data: Byte)

    fun writeReg(register: Int, data: ByteArray)

    fun read(length: Int, callback: I2cReadCallback)

    fun readReg(register: Int, length: Int, callback: I2cReadCallback)

    fun readContinuously(length: Int, callback: I2cReadCallback)

    fun readRegContinuously(register: Int, length: Int, callback: I2cReadCallback)

    fun stopReading()
}

typealias I2cReadCallback = I2cDevice.(data: ByteArray) -> Unit

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultI2cDevice)
// -------------------------------------------------------------------------------------------------

@Suppress("FunctionName")
fun Board.I2cDevice(address: Int): DefaultI2cDevice = DefaultI2cDevice(this, address)

class DefaultI2cDevice(
    private val board: Board,
    override val address: Int
) : BasePeripheral(board), I2cDevice {

    private var mI2cReplyReceiver: I2cReplyReceiver? = null
    private var mListenerRegistry: ListenerRegistry<CallbackWrapper>? = null

    override var readDelay: Int = 0
        set(value) {
            field = value
            board.io.sendI2cConfig(value)
        }

    override var autoRestartTransmission: Boolean = false

    override fun write(data: Byte) =
        write(byteArrayOf(data))

    override fun write(data: ByteArray) =
        board.io.sendI2cWriteRequest(address, data)

    override fun writeReg(register: Int, data: Byte) {
        if (register >= 0) {
            write(byteArrayOf(register.toByte(), data))
        } else {
            write(data)
        }
    }

    override fun writeReg(register: Int, data: ByteArray) {
        write(if (register >= 0) {
            ByteArray(data.size + 1).apply {
                this[0] = register.toByte()
                System.arraycopy(data, 0, this, 1, data.size)
            }
        } else data)
    }

    override fun read(length: Int, callback: I2cReadCallback) =
        readReg(-1, length, callback)

    override fun readReg(register: Int, length: Int, callback: I2cReadCallback) =
        startReading(register, length, false, callback)

    override fun readContinuously(length: Int, callback: I2cReadCallback) =
        readRegContinuously(-1, length, callback)

    override fun readRegContinuously(register: Int, length: Int, callback: I2cReadCallback) =
        startReading(register, length, true, callback)

    private fun startReading(
        register: Int,
        length: Int,
        readContinuously: Boolean,
        callback: I2cReadCallback
    ) {
        (mListenerRegistry ?: ListenerRegistry<CallbackWrapper>(emptyArray()).also {
            mListenerRegistry = it
        }).add(CallbackWrapper(register, readContinuously, callback))

        if (mI2cReplyReceiver == null) {
            val i2cReplyReceiver = receiver@{ data: I2cReply ->
                if (data.address != address) {
                    return@receiver
                }

                mListenerRegistry?.let { registry ->
                    registry.getListeners()?.forEach {
                        if (it.register == data.register) {
                            it.callback(this, data.data)

                            if (!it.readContinuously) {
                                registry.remove(it)
                            }
                        }
                    }
                }
            }

            mI2cReplyReceiver = i2cReplyReceiver
            board.io.registerI2cReplyReceiver(i2cReplyReceiver)
        }

        board.io.sendI2cReadRequest(
            address = address,
            data = if (register >= 0) {
                byteArrayOf(register.toByte(), length.toByte())
            } else {
                byteArrayOf(length.toByte())
            },
            readContinuously = readContinuously,
            autoRestartTransmission = autoRestartTransmission
        )
    }

    override fun stopReading() {
        mListenerRegistry?.clear()

        mI2cReplyReceiver?.let {
            board.io.unregisterI2cReplyReceiver(it)
            mI2cReplyReceiver = null
        }

        board.io.sendI2cStopReadingRequest(address)
    }

    override fun onClose() {
        super.onClose()

        stopReading()
    }

    private class CallbackWrapper(
        val register: Int,
        val readContinuously: Boolean,
        val callback: I2cReadCallback
    )
}