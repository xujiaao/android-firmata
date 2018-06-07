package com.xujiaao.android.firmata.board

import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.os.Message
import com.xujiaao.android.firmata.protocol.DefaultFirmata
import com.xujiaao.android.firmata.protocol.Firmata
import com.xujiaao.android.firmata.protocol.FirmataMessage
import com.xujiaao.android.firmata.protocol.feature.*
import com.xujiaao.android.firmata.transport.Transport
import java.io.Closeable
import java.io.EOFException
import java.io.IOException

interface BoardService {

    fun isConnected(): Boolean

    @Throws(IllegalStateException::class)
    fun connect()

    @Throws(IllegalStateException::class)
    fun disconnect()

    fun addOnConnectionChangeListener(listener: OnConnectionChangeListener)

    fun removeOnConnectionChangeListener(listener: OnConnectionChangeListener)

    interface OnConnectionChangeListener {

        fun onConnected(service: BoardService, board: Board)

        fun onDisconnected(service: BoardService, error: Throwable?)
    }
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultBoardService)
// -------------------------------------------------------------------------------------------------

private const val INITIALIZE_TIMEOUT = 10_000L

private const val HEARTBEATS_TIMEOUT = 1000L
private const val HEARTBEATS_REQUEST_DELAY = 500L
private const val HEARTBEATS_MAX_RETRIES = 2

class DefaultBoardService(
    private val looper: Looper,
    private val transport: Transport
) : BoardService {

    private val mWriteLock = Any()
    private val mFirmata: Firmata = DefaultFirmata {
        synchronized(mWriteLock) {
            mWriterThread?.send(it)
        }
    }

    private var mBoard: Board? = null
    private var mConnection: Transport.Connection? = null

    private var mWriterThread: WriterThread? = null
    private var mReaderThread: ReaderThread? = null
    private var mConnectorThread: ConnectorThread? = null
    private var mBoardInitializer: BoardInitializer? = null
    private var mBoardHeartbeats: BoardHeartbeats? = null

    private val mOnConnectionChangeListeners =
        LinkedHashSet<BoardService.OnConnectionChangeListener>()

    override fun isConnected(): Boolean = mBoard != null

    override fun connect() = ensureLooperThread("connect") {
        if (mConnection == null) {
            connectInternal()
        }
    }

    private fun connectInternal() {
        val connection = transport.openConnection()
        mConnection = connection

        mConnectorThread = ConnectorThread(
            connection = connection,
            onConnected = ::onTransportConnected,
            onError = ::onError
        )
    }

    override fun disconnect() = ensureLooperThread("disconnectInternal") {
        if (mConnection != null) {
            disconnectInternal(null)
        }
    }

    private fun disconnectInternal(error: Throwable?) {
        mBoard?.close()
        mBoard = null

        synchronized(mWriteLock) {
            mWriterThread?.close()
            mWriterThread = null
        }

        mReaderThread?.close()
        mReaderThread = null

        mConnectorThread?.close()
        mConnectorThread = null

        mBoardInitializer?.close()
        mBoardInitializer = null

        mBoardHeartbeats?.close()
        mBoardHeartbeats = null

        mConnection?.close()
        mConnection = null

        collectOnConnectionChangeListeners()?.forEach {
            it.onDisconnected(this, error)
        }
    }

    override fun addOnConnectionChangeListener(
        listener: BoardService.OnConnectionChangeListener
    ) {
        synchronized(mOnConnectionChangeListeners) {
            mOnConnectionChangeListeners.add(listener)
        }
    }

    override fun removeOnConnectionChangeListener(
        listener: BoardService.OnConnectionChangeListener
    ) {
        synchronized(mOnConnectionChangeListeners) {
            mOnConnectionChangeListeners.remove(listener)
        }
    }

    // ---------------------------------------------------------------------------------------------
    // Events
    // ---------------------------------------------------------------------------------------------

    private fun onTransportConnected(connection: Transport.Connection) {
        mConnectorThread?.close()
        mConnectorThread = null

        synchronized(mWriteLock) {
            mWriterThread?.close()
            mWriterThread = WriterThread(
                connection = connection,
                onError = ::onError
            )
        }

        mReaderThread?.close()
        mReaderThread = ReaderThread(
            connection = connection,
            firmata = mFirmata,
            onError = ::onError
        )

        mBoardInitializer?.close()
        mBoardInitializer = BoardInitializer(
            firmata = mFirmata,
            onInitialized = ::onBoardInitialized,
            onError = ::onError
        )
    }

    private fun onBoardInitialized(board: Board) {
        mBoard = board

        mBoardInitializer?.close()
        mBoardInitializer = null

        mBoardHeartbeats?.close()
        mBoardHeartbeats = BoardHeartbeats(
            firmata = mFirmata,
            onError = ::onError
        )

        collectOnConnectionChangeListeners()?.forEach {
            it.onConnected(this, board)
        }
    }

    private fun onError(error: Throwable) {
        disconnectInternal(error)
    }

    @Throws(IllegalStateException::class)
    private inline fun <T> ensureLooperThread(name: String, action: () -> T): T {
        if (Looper.myLooper() !== looper) {
            throw IllegalStateException(
                "Board.$name() can only be called in thread '${looper.thread.name}'"
            )
        }

        return action()
    }

    private fun collectOnConnectionChangeListeners():
            Array<BoardService.OnConnectionChangeListener>? {
        var listeners: Array<BoardService.OnConnectionChangeListener>? = null
        synchronized(mOnConnectionChangeListeners) {
            if (mOnConnectionChangeListeners.size > 0) {
                listeners = mOnConnectionChangeListeners.toTypedArray()
            }
        }

        return listeners
    }
}

// -------------------------------------------------------------------------------------------------
// Background Threads
// -------------------------------------------------------------------------------------------------

private class BackgroundThreadDelegate(
    handleMessage: ((message: Message) -> Boolean)? = null
) : Closeable {

    private var mRunning = true
    private val mHandler = Handler(Looper.myLooper(), handleMessage)

    fun isRunning(): Boolean = mRunning

    fun post(action: () -> Unit) = synchronized(this) {
        if (mRunning) {
            mHandler.post(action)
        }
    }

    fun sendMessage(what: Int, obj: Any) = synchronized(this) {
        if (mRunning) {
            mHandler.obtainMessage(what, obj).sendToTarget()
        }
    }

    override fun close() = synchronized(this) {
        mRunning = false
    }
}

private class ConnectorThread(
    private val connection: Transport.Connection,
    private val onConnected: (connection: Transport.Connection) -> Unit,
    private val onError: (error: Throwable) -> Unit
) : Thread("board-connector"), Closeable {

    private val mDelegate = BackgroundThreadDelegate()

    init {
        start()
    }

    override fun run() {
        var error: Throwable? = null

        try {
            connection.connect()
        } catch (e: IOException) {
            error = e
        }

        mDelegate.post {
            if (error == null) {
                onConnected(connection)
            } else {
                onError(error)
            }
        }
    }

    override fun close() {
        mDelegate.close()
        interrupt()
    }
}

private const val WRITER_MSG_DATA = 1

private class WriterThread(
    private val connection: Transport.Connection,
    private val onError: (error: Throwable) -> Unit
) : HandlerThread("board-writer"), Closeable {

    private val mDelegate = BackgroundThreadDelegate()

    private val mWriterHandler by lazy {
        Handler(looper, ::handleMessage)
    }

    init {
        start()
    }

    fun send(data: ByteArray): Boolean {
        return data.isNotEmpty()
                && mWriterHandler.sendMessage(mWriterHandler.obtainMessage(WRITER_MSG_DATA, data))
    }

    private fun handleMessage(message: Message): Boolean {
        when (message.what) {
            WRITER_MSG_DATA -> {
                write(message.obj as ByteArray)

                return true
            }
        }

        return false
    }

    private fun write(data: ByteArray) {
        var error: Throwable? = null

        try {
            connection.write(data)
        } catch (e: IOException) {
            error = e
        }

        if (error != null) {
            mDelegate.post {
                onError(error)
            }
        }
    }

    override fun close() {
        quit()

        mDelegate.close()
        interrupt()
    }
}

private const val READER_MSG_FIRMATA = 1

private class ReaderThread(
    private val connection: Transport.Connection,
    private val firmata: Firmata,
    private val onError: (error: Throwable) -> Unit
) : Thread("board-reader"), Closeable {

    private val mDelegate = BackgroundThreadDelegate { message ->
        (message.what == READER_MSG_FIRMATA).also {
            if (it) {
                firmata.dispatchResponse(message.obj as FirmataMessage)
            }
        }
    }

    init {
        start()
    }

    override fun run() {
        val processCallback = { message: FirmataMessage ->
            mDelegate.sendMessage(READER_MSG_FIRMATA, message)
        }

        while (mDelegate.isRunning()) {
            var byte = -1
            var error: Throwable? = null

            try {
                byte = connection.read()
            } catch (e: IOException) {
                error = e
            }

            if (byte != -1) {
                firmata.processResponse(byte, processCallback)
            } else {
                mDelegate.post {
                    onError(error ?: EOFException("End of the stream is reached"))
                }
            }
        }
    }

    override fun close() {
        mDelegate.close()
        interrupt()
    }
}

private class BoardInitializer(
    private val firmata: Firmata,
    private val onInitialized: ((Board) -> Unit),
    private val onError: ((Throwable) -> Unit)
) : Closeable {

    private val mHandler = Handler(Looper.myLooper())

    private var mRunning = true
    private var mVersionReport: VersionReport? = null
    private var mFirmwareReport: FirmwareReport? = null
    private var mCapabilityResponse: CapabilityResponse? = null
    private var mAnalogMappingResponse: AnalogMappingResponse? = null

    private val mVersionReportReceiver: VersionReportReceiver =
        wrap {
            mVersionReport = it

            // hack for usb transport. send requests after usb device being prepared...
            firmata.sendReportFirmwareRequest()
            firmata.sendCapabilityQueryRequest()
            firmata.sendAnalogMappingQueryRequest()
        }

    private val mFirmwareReportReceiver: FirmwareReportReceiver =
        wrap { mFirmwareReport = it }

    private val mCapabilityResponseReceiver: CapabilityResponseReceiver =
        wrap { mCapabilityResponse = it }

    private val mAnalogMappingResponseReceiver: AnalogMappingResponseReceiver =
        wrap { mAnalogMappingResponse = it }

    private val mTimeoutRunnable: Runnable = Runnable {
        close()

        onError(IOException("Timeout to get board info"))
    }

    init {
        firmata.registerVersionReportReceiver(mVersionReportReceiver)
        firmata.registerFirmwareReportReceiver(mFirmwareReportReceiver)
        firmata.registerCapabilityResponseReceiver(mCapabilityResponseReceiver)
        firmata.registerAnalogMappingResponseReceiver(mAnalogMappingResponseReceiver)

        firmata.sendSystemResetRequest() // reset board.
        firmata.sendReportVersionRequest()

        mHandler.postDelayed(mTimeoutRunnable, INITIALIZE_TIMEOUT)
    }

    override fun close() {
        if (mRunning) {
            mRunning = false

            firmata.unregisterVersionReportReceiver(mVersionReportReceiver)
            firmata.unregisterFirmwareReportReceiver(mFirmwareReportReceiver)
            firmata.unregisterCapabilityResponseReceiver(mCapabilityResponseReceiver)
            firmata.unregisterAnalogMappingResponseReceiver(mAnalogMappingResponseReceiver)

            mHandler.removeCallbacks(mTimeoutRunnable)
        }
    }

    private fun isReady(): Boolean {
        return mVersionReport != null
                && mFirmwareReport != null
                && mCapabilityResponse != null
                && mAnalogMappingResponse != null
    }

    private fun onReady() {
        close()

        onInitialized(
            DefaultBoard(
                io = firmata,
                versionReport = mVersionReport!!,
                firmwareReport = mFirmwareReport!!,
                capabilityResponse = mCapabilityResponse!!,
                analogMappingResponse = mAnalogMappingResponse!!
            )
        )
    }

    private fun <T> wrap(action: (T) -> Unit): (T) -> Unit {
        return {
            action(it)

            if (isReady()) {
                onReady()
            }
        }
    }
}

private class BoardHeartbeats(
    private val firmata: Firmata,
    private val onError: ((Throwable) -> Unit)
) : Closeable {

    private val mHandler = Handler(Looper.myLooper())
    private var mCurrentRetryCount = 0

    private val mHeartbeatReceiver: VersionReportReceiver = {
        mCurrentRetryCount = 0

        mHandler.removeCallbacks(mHeartbeatTimeoutRunnable)
        mHandler.removeCallbacks(mHeartbeatRequestRunnable)
        mHandler.postDelayed(mHeartbeatRequestRunnable, HEARTBEATS_REQUEST_DELAY)
    }

    private val mHeartbeatRequestRunnable: Runnable = Runnable {
        firmata.sendReportVersionRequest()

        mHandler.removeCallbacks(mHeartbeatTimeoutRunnable)
        mHandler.postDelayed(mHeartbeatTimeoutRunnable, HEARTBEATS_TIMEOUT)
    }

    private val mHeartbeatTimeoutRunnable: Runnable = Runnable {
        if (mCurrentRetryCount >= HEARTBEATS_MAX_RETRIES) {
            onError(IOException("Timeout to receive board heartbeats"))
        } else {
            mCurrentRetryCount++

            mHeartbeatRequestRunnable.run()
        }
    }

    init {
        firmata.registerVersionReportReceiver(mHeartbeatReceiver)

        mHeartbeatRequestRunnable.run()
    }

    override fun close() {
        firmata.unregisterVersionReportReceiver(mHeartbeatReceiver)

        mHandler.removeCallbacks(mHeartbeatTimeoutRunnable)
        mHandler.removeCallbacks(mHeartbeatRequestRunnable)
    }
}