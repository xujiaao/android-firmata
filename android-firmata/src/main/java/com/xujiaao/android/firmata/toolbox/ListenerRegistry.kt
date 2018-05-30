package com.xujiaao.android.firmata.toolbox

class ListenerRegistry<T> {

    private val mListeners: LinkedHashSet<T> = LinkedHashSet()

    private var mDirty = true
    private var mArray: Array<T>? = null

    fun isEmpty(): Boolean = mListeners.isEmpty()

    fun add(listener: T) {
        if (mListeners.add(listener)) {
            mDirty = true
            mArray = null
        }
    }

    fun remove(listener: T) {
        if (mListeners.remove(listener)) {
            mDirty = true
            mArray = null
        }
    }

    fun clear() {
        mListeners.clear()

        mDirty = true
        mArray = null
    }

    fun getListeners(): Array<T>? {
        if (mDirty) {
            mDirty = false
            mArray = if (mListeners.isEmpty()) {
                null
            } else {
                @Suppress("UNCHECKED_CAST")
                mListeners.toArray() as Array<T>
            }
        }

        return mArray
    }
}