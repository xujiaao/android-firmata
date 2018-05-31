[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board.driver.pca9685](../index.md) / [Pca9685](./index.md)

# Pca9685

`class Pca9685 : `[`BasePeripheralGroup`](../../com.xujiaao.android.firmata.board/-base-peripheral-group/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `Pca9685(board: `[`Board`](../../com.xujiaao.android.firmata.board/-board/index.md)`, address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = PCA9685_ADDRESS, frequency: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = PCA9685_FREQ_DEFAULT)` |

### Functions

| Name | Summary |
|---|---|
| [onClose](on-close.md) | `fun onClose(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [write](write.md) | `fun write(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun write(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Sets the PWM output of one of the PCA9685 channels. |
| [writeDutyCircle](write-duty-circle.md) | `fun writeDutyCircle(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [writeMicroseconds](write-microseconds.md) | `fun writeMicroseconds(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>Writes a value in microseconds (uS). |

### Inherited Functions

| Name | Summary |
|---|---|
| [addOnCloseListener](../../com.xujiaao.android.firmata.board/-base-peripheral-group/add-on-close-listener.md) | `open fun addOnCloseListener(listener: `[`OnPeripheralCloseListener`](../../com.xujiaao.android.firmata.board/-on-peripheral-close-listener.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeOnCloseListener](../../com.xujiaao.android.firmata.board/-base-peripheral-group/remove-on-close-listener.md) | `open fun removeOnCloseListener(listener: `[`OnPeripheralCloseListener`](../../com.xujiaao.android.firmata.board/-on-peripheral-close-listener.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [Led](../-led.md) | `fun `[`Pca9685`](./index.md)`.Led(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pca9685Led`](../-pca9685-led/index.md) |
| [Motor](../-motor.md) | `fun `[`Pca9685`](./index.md)`.Motor(pwm: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, cdir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`Pca9685Motor`](../-pca9685-motor/index.md) |
| [Pin](../-pin.md) | `fun `[`Pca9685`](./index.md)`.Pin(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pca9685Pin`](../-pca9685-pin/index.md) |
| [Servo](../-servo.md) | `fun `[`Pca9685`](./index.md)`.Servo(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`Pca9685Servo`](../-pca9685-servo/index.md) |
| [animate](../../com.xujiaao.android.firmata.board/animate.md) | `fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](../../com.xujiaao.android.firmata.board/-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../../com.xujiaao.android.firmata.board/animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
