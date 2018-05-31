[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [BasePeripheral](./index.md)

# BasePeripheral

`open class BasePeripheral : `[`Peripheral`](../-peripheral/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BasePeripheral(parent: `[`PeripheralGroup`](../-peripheral-group/index.md)`? = null)` |

### Properties

| Name | Summary |
|---|---|
| [parent](parent.md) | `val parent: `[`PeripheralGroup`](../-peripheral-group/index.md)`?` |

### Functions

| Name | Summary |
|---|---|
| [animate](animate.md) | `open fun animate(animatable: `[`Animatable`](../-peripheral/-animatable/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [cancel](cancel.md) | `open fun cancel(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [clearAnimation](clear-animation.md) | `open fun clearAnimation(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [close](close.md) | `open fun close(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getSharedState](get-shared-state.md) | `open fun <T : `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`> getSharedState(type: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`T`](get-shared-state.md#T)`>): `[`T`](get-shared-state.md#T) |
| [loop](loop.md) | `open fun loop(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [onClose](on-close.md) | `open fun onClose(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [post](post.md) | `open fun post(delay: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [animate](../animate.md) | `fun `[`Peripheral`](../-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](../-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](../-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BaseLed](../../com.xujiaao.android.firmata.board.driver/-base-led/index.md) | `abstract class BaseLed : `[`BasePeripheral`](./index.md)`, `[`Led`](../../com.xujiaao.android.firmata.board.driver/-led/index.md) |
| [BaseMotor](../../com.xujiaao.android.firmata.board.driver/-base-motor/index.md) | `abstract class BaseMotor<T> : `[`BasePeripheral`](./index.md)`, `[`Motor`](../../com.xujiaao.android.firmata.board.driver/-motor/index.md) |
| [BasePeripheralGroup](../-base-peripheral-group/index.md) | `open class BasePeripheralGroup : `[`BasePeripheral`](./index.md)`, `[`PeripheralGroup`](../-peripheral-group/index.md) |
| [BaseServo](../../com.xujiaao.android.firmata.board.driver/-base-servo/index.md) | `abstract class BaseServo : `[`BasePeripheral`](./index.md)`, `[`Servo`](../../com.xujiaao.android.firmata.board.driver/-servo/index.md) |
| [DefaultI2cDevice](../../com.xujiaao.android.firmata.board.driver/-default-i2c-device/index.md) | `class DefaultI2cDevice : `[`BasePeripheral`](./index.md)`, `[`I2cDevice`](../../com.xujiaao.android.firmata.board.driver/-i2c-device/index.md) |
| [DefaultJoystick](../../com.xujiaao.android.firmata.board.driver/-default-joystick/index.md) | `class DefaultJoystick : `[`BasePeripheral`](./index.md)`, `[`Joystick`](../../com.xujiaao.android.firmata.board.driver/-joystick/index.md) |
| [DefaultPin](../../com.xujiaao.android.firmata.board.driver/-default-pin/index.md) | `class DefaultPin : `[`BasePeripheral`](./index.md)`, `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [DefaultQuadratureEncoder](../../com.xujiaao.android.firmata.board.driver/-default-quadrature-encoder/index.md) | `class DefaultQuadratureEncoder : `[`BasePeripheral`](./index.md)`, `[`QuadratureEncoder`](../../com.xujiaao.android.firmata.board.driver/-quadrature-encoder/index.md) |
| [Pca9685Pin](../../com.xujiaao.android.firmata.board.driver.pca9685/-pca9685-pin/index.md) | `class Pca9685Pin : `[`BasePeripheral`](./index.md)`, `[`Pin`](../../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
