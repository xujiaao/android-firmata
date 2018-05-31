package com.xujiaao.android.firmata.protocol

import com.xujiaao.android.firmata.toolbox.ListenerRegistry
import kotlin.reflect.KClass

interface Firmata {

    fun sendRequest(data: ByteArray)

    fun processResponse(byte: Int, callback: (FirmataMessage) -> Unit)

    fun dispatchResponse(message: FirmataMessage)

    fun <T : FirmataMessage> registerReceiver(type: Class<T>, receiver: (T) -> Unit)

    fun <T : FirmataMessage> unregisterReceiver(type: Class<T>, receiver: (T) -> Unit)
}

// -------------------------------------------------------------------------------------------------
// Extension
// -------------------------------------------------------------------------------------------------

inline fun <reified T : FirmataMessage> Firmata.registerReceiver(
    noinline receiver: (T) -> Unit
) = registerReceiver(T::class, receiver)

fun <T : FirmataMessage> Firmata.registerReceiver(
    type: KClass<T>,
    receiver: (T) -> Unit
) = registerReceiver(type.java, receiver)

inline fun <reified T : FirmataMessage> Firmata.unregisterReceiver(
    noinline receiver: (T) -> Unit
) = unregisterReceiver(T::class, receiver)

fun <T : FirmataMessage> Firmata.unregisterReceiver(
    type: KClass<T>,
    receiver: (T) -> Unit
) = unregisterReceiver(type.java, receiver)

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultFirmata)
// -------------------------------------------------------------------------------------------------

class DefaultFirmata(private val sender: (data: ByteArray) -> Unit) : Firmata {

    private val mLock = Any()
    private val mProcessor = DefaultFirmataProcessor()
    private val mReceivers = HashMap<String, ListenerRegistry<(FirmataMessage) -> Unit>>()

    override fun sendRequest(data: ByteArray) =
        sender(data)

    override fun processResponse(byte: Int, callback: (FirmataMessage) -> Unit) =
        mProcessor.process(byte, callback)

    override fun dispatchResponse(message: FirmataMessage) {
        synchronized(mLock) {
            mReceivers[message.javaClass.name]?.getListeners()?.forEach {
                it(message)
            }
        }
    }

    override fun <T : FirmataMessage> registerReceiver(type: Class<T>, receiver: (T) -> Unit) {
        val parser = getFirmataMessageParser(type)
        synchronized(mLock) {
            mProcessor.attachParser(parser)

            @Suppress("UNCHECKED_CAST")
            mReceivers.getOrPut(type.name) {
                ListenerRegistry(emptyArray())
            }.add(receiver as ((FirmataMessage) -> Unit))
        }
    }

    override fun <T : FirmataMessage> unregisterReceiver(type: Class<T>, receiver: (T) -> Unit) {
        synchronized(mLock) {
            mReceivers[type.name]?.run {
                @Suppress("UNCHECKED_CAST")
                remove(receiver as (FirmataMessage) -> Unit)

                if (isEmpty()) {
                    mReceivers.remove(type.name)
                    mProcessor.detachParser(getFirmataMessageParser(type))
                }
            }
        }
    }
}