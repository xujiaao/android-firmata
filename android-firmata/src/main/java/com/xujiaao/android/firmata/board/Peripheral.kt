package com.xujiaao.android.firmata.board

import android.animation.Animator
import android.animation.TimeInterpolator
import android.os.Handler
import android.os.Looper
import android.support.annotation.CallSuper
import android.util.SparseArray
import android.view.animation.AnimationUtils
import android.view.animation.LinearInterpolator
import java.io.Closeable

interface Peripheral : Closeable {

    fun <T : Any> getSharedState(type: Class<T>): T

    fun animate(animatable: Animatable)

    fun clearAnimation()

    fun post(delay: Long, action: () -> Unit): Int

    fun loop(interval: Long, action: () -> Unit): Int

    fun cancel(id: Int)

    override fun close()

    interface Animatable {

        fun start()

        fun cancel()
    }
}

fun Peripheral.animate(animator: Animator) {
    animate(object : Peripheral.Animatable {

        override fun start() = animator.start()

        override fun cancel() = animator.cancel()
    })
}

fun Peripheral.animate(interval: Long, update: () -> Boolean) {
    animate(object : Peripheral.Animatable {

        private val mHandler = Handler(Looper.myLooper())

        override fun start() {
            mHandler.removeCallbacks(mUpdateRunnable)
            mUpdateRunnable.run()
        }

        override fun cancel() {
            mHandler.removeCallbacks(mUpdateRunnable)
        }

        private val mUpdateRunnable = object : Runnable {

            override fun run() {
                if (update()) {
                    mHandler.postDelayed(this, interval)
                }
            }
        }
    })
}

fun <T> Peripheral.animate(
    frames: Array<T>,
    interval: Long,
    repeat: Boolean = false,
    reverse: Boolean = false,
    interpolator: TimeInterpolator = LinearInterpolator(),
    update: (T) -> Unit
) {
    val start = AnimationUtils.currentAnimationTimeMillis()
    val duration = interval * frames.size
    var reversing = false

    animate(interval) {
        val offset = AnimationUtils.currentAnimationTimeMillis() - start

        val input = (offset % duration).toFloat() / duration
        val interpolation = interpolator.getInterpolation(if (reversing) 1F - input else input)
        val index = (interpolation * frames.size).toInt().coerceIn(0, frames.size - 1)
        update(frames[index])

        val complete = offset >= duration
        if (complete) {
            reversing = reverse && !reversing
        }

        !complete || repeat
    }
}

// -------------------------------------------------------------------------------------------------
// Implementation (BasePeripheral)
// -------------------------------------------------------------------------------------------------

open class BasePeripheral(protected val parent: PeripheralGroup? = null) : Peripheral {

    private var mAnimatable: Peripheral.Animatable? = null
    private var mScheduler: Scheduler? = null

    private lateinit var mOnCloseListener: OnPeripheralCloseListener

    init {
        parent?.run {
            mOnCloseListener = ::onClose
            addOnCloseListener(mOnCloseListener)
        }
    }

    override fun <T : Any> getSharedState(type: Class<T>): T {
        return parent?.getSharedState(type) ?: throw UnsupportedOperationException()
    }

    override fun animate(animatable: Peripheral.Animatable) {
        mAnimatable?.cancel()
        mAnimatable = animatable

        animatable.start()
    }

    override fun clearAnimation() {
        mAnimatable?.cancel()
        mAnimatable = null
    }

    override fun post(delay: Long, action: () -> Unit): Int =
        getScheduler().post(delay, action)

    override fun loop(interval: Long, action: () -> Unit): Int =
        getScheduler().loop(interval, action)

    private fun getScheduler(): Scheduler {
        var scheduler = mScheduler
        if (scheduler == null) {
            scheduler = Scheduler()
            mScheduler = scheduler
        }

        return scheduler
    }

    override fun cancel(id: Int) {
        mScheduler?.remove(id)
    }

    override fun close() {
        parent?.removeOnCloseListener(mOnCloseListener)

        onClose()
    }

    @CallSuper
    protected open fun onClose() {
        clearAnimation()

        mScheduler?.clear()
    }

    private class Scheduler {

        private var mNextId = 0
        private val mHandler = Handler()
        private val mCallbacks = SparseArray<Runnable>()

        fun post(delay: Long, action: () -> Unit): Int = (mNextId++).also { id ->
            val callback = Runnable {
                action()
                mCallbacks.remove(id)
            }

            mCallbacks.put(id, callback)
            mHandler.postDelayed(callback, delay)
        }

        fun loop(interval: Long, action: () -> Unit): Int = (mNextId++).also { id ->
            val callback = object : Runnable {

                override fun run() {
                    action()

                    if (mCallbacks.indexOfKey(id) >= 0) {
                        mHandler.postDelayed(this, interval)
                    }
                }
            }

            mCallbacks.put(id, callback)
            callback.run()
        }

        fun remove(id: Int) {
            val callback = mCallbacks.get(id, null)
            if (callback != null) {
                mCallbacks.remove(id)
                mHandler.removeCallbacks(callback)
            }
        }

        fun clear() {
            mCallbacks.clear()
            mHandler.removeCallbacksAndMessages(null)
        }
    }
}