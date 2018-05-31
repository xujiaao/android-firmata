[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board.driver](../index.md) / [DefaultI2cDevice](./index.md)

# DefaultI2cDevice

`class DefaultI2cDevice : `[`BasePeripheral`](../../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`I2cDevice`](../-i2c-device/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DefaultI2cDevice(board: `[`Board`](../../com.xujiaao.android.firmata.board/-board/index.md)`, address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [address](address.md) | `val address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [autoRestartTransmission](auto-restart-transmission.md) | `var autoRestartTransmission: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [readDelay](read-delay.md) | `var readDelay: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Inherited Properties

| Name | Summary |
|---|---|
| [parent](../../com.xujiaao.android.firmata.board/-base-peripheral/parent.md) | `val parent: `[`PeripheralGroup`](../../com.xujiaao.android.firmata.board/-peripheral-group/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [onClose](on-close.md) | `fun onClose(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [read](read.md) | `fun read(length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, callback: `[`I2cReadCallback`](../-i2c-read-callback.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [readContinuously](read-continuously.md) | `fun readContinuously(length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, callback: `[`I2cReadCallback`](../-i2c-read-callback.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [readReg](read-reg.md) | `fun readReg(register: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, callback: `[`I2cReadCallback`](../-i2c-read-callback.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [readRegContinuously](read-reg-continuously.md) | `fun readRegContinuously(register: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, callback: `[`I2cReadCallback`](../-i2c-read-callback.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [stopReading](stop-reading.md) | `fun stopReading(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [write](write.md) | `fun write(data: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun write(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [writeReg](write-reg.md) | `fun writeReg(register: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun writeReg(register: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [animate](../../com.xujiaao.android.firmata.board/-base-peripheral/animate.md) | `open fun animate(animatable: `[`Animatable`](../../com.xujiaao.android.firmata.board/-peripheral/-animatable/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancel](../../com.xujiaao.android.firmata.board/-base-peripheral/cancel.md) | `open fun cancel(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearAnimation](../../com.xujiaao.android.firmata.board/-base-peripheral/clear-animation.md) | `open fun clearAnimation(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](../../com.xujiaao.android.firmata.board/-base-peripheral/close.md) | `open fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getSharedState](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md) | `open fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getSharedState(type: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`T`](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md#T)`>): `[`T`](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md#T) |
| [loop](../../com.xujiaao.android.firmata.board/-base-peripheral/loop.md) | `open fun loop(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [post](../../com.xujiaao.android.firmata.board/-base-peripheral/post.md) | `open fun post(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [animate](../../com.xujiaao.android.firmata.board/animate.md) | `fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
