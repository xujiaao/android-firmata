[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board.driver.pca9685](../index.md) / [Pca9685Pin](./index.md)

# Pca9685Pin

`class Pca9685Pin : `[`BasePeripheral`](../../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Pca9685Pin(pca9685: `[`Pca9685`](../-pca9685/index.md)`, channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Inherited Properties

| Name | Summary |
|---|---|
| [parent](../../com.xujiaao.android.firmata.board/-base-peripheral/parent.md) | `val parent: `[`PeripheralGroup`](../../com.xujiaao.android.firmata.board/-peripheral-group/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [analogRead](analog-read.md) | `fun analogRead(callback: `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md)`.(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [analogWrite](analog-write.md) | `fun analogWrite(value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [digitalRead](digital-read.md) | `fun digitalRead(callback: `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md)`.(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [digitalWrite](digital-write.md) | `fun digitalWrite(value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [pinMode](pin-mode.md) | `fun pinMode(mode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [query](query.md) | `fun query(callback: `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md)`.(mode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, state: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [stopAnalogReading](stop-analog-reading.md) | `fun stopAnalogReading(): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [stopDigitalReading](stop-digital-reading.md) | `fun stopDigitalReading(): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [stopQuerying](stop-querying.md) | `fun stopQuerying(): `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |

### Inherited Functions

| Name | Summary |
|---|---|
| [animate](../../com.xujiaao.android.firmata.board/-base-peripheral/animate.md) | `open fun animate(animatable: `[`Animatable`](../../com.xujiaao.android.firmata.board/-peripheral/-animatable/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancel](../../com.xujiaao.android.firmata.board/-base-peripheral/cancel.md) | `open fun cancel(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearAnimation](../../com.xujiaao.android.firmata.board/-base-peripheral/clear-animation.md) | `open fun clearAnimation(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](../../com.xujiaao.android.firmata.board/-base-peripheral/close.md) | `open fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getSharedState](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md) | `open fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getSharedState(type: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`T`](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md#T)`>): `[`T`](../../com.xujiaao.android.firmata.board/-base-peripheral/get-shared-state.md#T) |
| [loop](../../com.xujiaao.android.firmata.board/-base-peripheral/loop.md) | `open fun loop(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onClose](../../com.xujiaao.android.firmata.board/-base-peripheral/on-close.md) | `open fun onClose(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [post](../../com.xujiaao.android.firmata.board/-base-peripheral/post.md) | `open fun post(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [animate](../../com.xujiaao.android.firmata.board/animate.md) | `fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
