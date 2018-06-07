package com.xujiaao.android.firmata.board

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.content.Context
import android.os.Looper
import com.xujiaao.android.firmata.transport.Transport

interface BoardConnection {

    fun onBoardConnecting()

    fun onBoardConnected(board: Board)

    fun onBoardDisconnected(error: Throwable?)
}

fun BoardConnection.isConnecting() =
    isBoardConnecting(this)

fun BoardConnection.isConnected() =
    isBoardConnected(this)

@Suppress("unused")
@Throws(IllegalArgumentException::class)
fun BoardConnection.connect(context: Context, transport: String) =
    connectBoard(context, transport, this)

fun BoardConnection.connect(transport: Transport) =
    connectBoard(transport, this)

fun BoardConnection.disconnect() =
    disconnectBoard(this)

// -------------------------------------------------------------------------------------------------
// Convenient (Basic)
// -------------------------------------------------------------------------------------------------

private val SERVICES = HashMap<BoardConnection, BoardService>()

fun isBoardConnecting(connection: BoardConnection): Boolean {
    return getBoardService(connection).let {
        it != null && !it.isConnected()
    }
}

fun isBoardConnected(connection: BoardConnection): Boolean {
    return getBoardService(connection)?.isConnected() ?: false
}

fun connectBoard(
    context: Context,
    transport: String,
    connection: BoardConnection
): BoardConnection = connectBoard(Transport(context, transport), connection)

fun connectBoard(transport: Transport, connection: BoardConnection): BoardConnection {
    val service = DefaultBoardService(Looper.myLooper(), transport).apply {
        addOnConnectionChangeListener(object : BoardService.OnConnectionChangeListener {

            override fun onConnected(service: BoardService, board: Board) {
                connection.onBoardConnected(board)
            }

            override fun onDisconnected(service: BoardService, error: Throwable?) {
                service.removeOnConnectionChangeListener(this)

                synchronized(SERVICES) {
                    SERVICES.remove(connection)
                }

                connection.onBoardDisconnected(error)
            }
        })

        connect()
    }

    synchronized(SERVICES) {
        SERVICES.put(connection, service)
    }?.run {
        disconnect()
    }

    connection.onBoardConnecting()

    return connection
}

fun disconnectBoard(connection: BoardConnection) {
    getBoardService(connection)?.disconnect()
}

private fun getBoardService(connection: BoardConnection) =
    synchronized(SERVICES) {
        SERVICES[connection]
    }

// -------------------------------------------------------------------------------------------------
// Convenient (Spec)
// -------------------------------------------------------------------------------------------------

@Suppress("unused")
fun connectBoard(
    context: Context,
    transport: String,
    init: BoardConnectionSpec.() -> Unit
): BoardConnection = connectBoard(Transport(context, transport), init)

fun connectBoard(transport: Transport, init: BoardConnectionSpec.() -> Unit): BoardConnection =
    connectBoard(transport, BoardConnectionSpec().apply(init))

class BoardConnectionSpec internal constructor() : BoardConnection {

    private var mOnBoardConnectingAction: (() -> Unit)? = null

    fun onConnecting(action: () -> Unit) {
        mOnBoardConnectingAction = action
    }

    override fun onBoardConnecting() {
        mOnBoardConnectingAction?.invoke()
    }

    private var mOnBoardConnectedAction: ((Board) -> Unit)? = null

    fun onConnected(action: (board: Board) -> Unit) {
        mOnBoardConnectedAction = action
    }

    override fun onBoardConnected(board: Board) {
        mOnBoardConnectedAction?.invoke(board)
    }

    private var mOnBoardDisconnectedAction: ((Throwable?) -> Unit)? = null

    fun onDisconnected(action: (error: Throwable?) -> Unit) {
        mOnBoardDisconnectedAction = action
    }

    override fun onBoardDisconnected(error: Throwable?) {
        mOnBoardDisconnectedAction?.invoke(error)
    }
}

// -------------------------------------------------------------------------------------------------
// Convenient (Lifecycle)
// -------------------------------------------------------------------------------------------------

fun connectBoardWithLifecycle(
    context: Context,
    transport: String,
    lifecycle: Lifecycle,
    init: BoardConnectionSpec.() -> Unit
): BoardConnection = connectBoardWithLifecycle(Transport(context, transport), lifecycle, init)

fun connectBoardWithLifecycle(
    transport: Transport,
    lifecycle: Lifecycle,
    init: BoardConnectionSpec.() -> Unit
): BoardConnection {
    return connectBoard(transport, init).apply {
        lifecycle.addObserver(object : LifecycleObserver {

            @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
            fun onDestroy() {
                lifecycle.removeObserver(this)

                this@apply.disconnect()
            }
        })
    }
}