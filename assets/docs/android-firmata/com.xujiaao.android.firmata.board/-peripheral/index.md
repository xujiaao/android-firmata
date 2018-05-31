[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [Peripheral](./index.md)

# Peripheral

`interface Peripheral : `[`Closeable`](http://docs.oracle.com/javase/6/docs/api/java/io/Closeable.html)

### Types

| Name | Summary |
|---|---|
| [Animatable](-animatable/index.md) | `interface Animatable` |

### Functions

| Name | Summary |
|---|---|
| [animate](animate.md) | `abstract fun animate(animatable: `[`Animatable`](-animatable/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancel](cancel.md) | `abstract fun cancel(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearAnimation](clear-animation.md) | `abstract fun clearAnimation(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](close.md) | `abstract fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getSharedState](get-shared-state.md) | `abstract fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getSharedState(type: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`T`](get-shared-state.md#T)`>): `[`T`](get-shared-state.md#T) |
| [loop](loop.md) | `abstract fun loop(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [post](post.md) | `abstract fun post(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [animate](../animate.md) | `fun `[`Peripheral`](./index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](./index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](./index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BasePeripheral](../-base-peripheral/index.md) | `open class BasePeripheral : `[`Peripheral`](./index.md) |
| [I2cDevice](../../com.xujiaao.android.firmata.board.driver/-i2c-device/index.md) | `interface I2cDevice : `[`Peripheral`](./index.md) |
| [Joystick](../../com.xujiaao.android.firmata.board.driver/-joystick/index.md) | `interface Joystick : `[`Peripheral`](./index.md) |
| [Led](../../com.xujiaao.android.firmata.board.driver/-led/index.md) | `interface Led : `[`Peripheral`](./index.md) |
| [Motor](../../com.xujiaao.android.firmata.board.driver/-motor/index.md) | `interface Motor : `[`Peripheral`](./index.md) |
| [PeripheralGroup](../-peripheral-group/index.md) | `interface PeripheralGroup : `[`Peripheral`](./index.md) |
| [Pin](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) | `interface Pin : `[`Peripheral`](./index.md) |
| [QuadratureEncoder](../../com.xujiaao.android.firmata.board.driver/-quadrature-encoder/index.md) | `interface QuadratureEncoder : `[`Peripheral`](./index.md) |
| [Servo](../../com.xujiaao.android.firmata.board.driver/-servo/index.md) | `interface Servo : `[`Peripheral`](./index.md) |
