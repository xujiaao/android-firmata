package com.xujiaao.android.firmata.toolbox

fun Byte.toUnsignedInt() = toInt() and 0xFF

fun Int.map(src: IntRange, dst: IntRange): Int =
    map(src.first, src.last, dst.first, dst.last)

fun Int.map(srcMin: Int, srcMax: Int, dstMin: Int, dstMax: Int): Int {
    val srcRange = (srcMax - srcMin).toFloat()
    if (srcRange == 0F) {
        return 0
    }

    return ((this - srcMin) * (dstMax - dstMin) / srcRange).toInt() + dstMin
}