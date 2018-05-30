package com.xujiaao.android.firmata.board

interface PeripheralGroup : Peripheral {

    fun addOnCloseListener(listener: OnPeripheralCloseListener)

    fun removeOnCloseListener(listener: OnPeripheralCloseListener)
}

typealias OnPeripheralCloseListener = () -> Unit

// -------------------------------------------------------------------------------------------------
// Implementation (BasePeripheralGroup)
// -------------------------------------------------------------------------------------------------

open class BasePeripheralGroup(
    parent: PeripheralGroup? = null
) : BasePeripheral(parent), PeripheralGroup {

    private var mOnCloseListeners: LinkedHashSet<OnPeripheralCloseListener>? = null

    override fun addOnCloseListener(listener: OnPeripheralCloseListener) {
        var listeners = mOnCloseListeners
        if (listeners == null) {
            listeners = LinkedHashSet()
            mOnCloseListeners = listeners
        }

        listeners.add(listener)
    }

    override fun removeOnCloseListener(listener: OnPeripheralCloseListener) {
        mOnCloseListeners?.remove(listener)
    }

    override fun onClose() {
        mOnCloseListeners?.let {
            val listeners = it.toTypedArray()
            listeners.forEach { listener ->
                listener()
            }
        }

        super.onClose()
    }
}