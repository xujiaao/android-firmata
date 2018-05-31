[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [Board](./index.md)

# Board

`interface Board : `[`PeripheralGroup`](../-peripheral-group/index.md)

### Types

| Name | Summary |
|---|---|
| [PinSpec](-pin-spec/index.md) | `interface PinSpec` |

### Properties

| Name | Summary |
|---|---|
| [firmwareMajorVersion](firmware-major-version.md) | `abstract val firmwareMajorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [firmwareMinorVersion](firmware-minor-version.md) | `abstract val firmwareMinorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [firmwareName](firmware-name.md) | `abstract val firmwareName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [io](io.md) | `abstract val io: `[`Firmata`](../../com.xujiaao.android.firmata.protocol/-firmata/index.md) |
| [pinsCount](pins-count.md) | `abstract val pinsCount: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [protocolMajorVersion](protocol-major-version.md) | `abstract val protocolMajorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [protocolMinorVersion](protocol-minor-version.md) | `abstract val protocolMinorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Functions

| Name | Summary |
|---|---|
| [getAnalogChannel](get-analog-channel.md) | `abstract fun getAnalogChannel(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getAnalogPin](get-analog-pin.md) | `abstract fun getAnalogPin(analogChannel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getPinModes](get-pin-modes.md) | `abstract fun getPinModes(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Set`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-set/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [getPinResolution](get-pin-resolution.md) | `abstract fun getPinResolution(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, mode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getPinSpec](get-pin-spec.md) | `abstract fun getPinSpec(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`PinSpec`](-pin-spec/index.md)<br>`abstract fun getPinSpec(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`PinSpec`](-pin-spec/index.md) |
| [setSamplingInterval](set-sampling-interval.md) | `abstract fun setSamplingInterval(samplingInterval: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [addOnCloseListener](../-peripheral-group/add-on-close-listener.md) | `abstract fun addOnCloseListener(listener: `[`OnPeripheralCloseListener`](../-on-peripheral-close-listener.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [removeOnCloseListener](../-peripheral-group/remove-on-close-listener.md) | `abstract fun removeOnCloseListener(listener: `[`OnPeripheralCloseListener`](../-on-peripheral-close-listener.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [I2cDevice](../../com.xujiaao.android.firmata.board.driver/-i2c-device.md) | `fun `[`Board`](./index.md)`.I2cDevice(address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultI2cDevice`](../../com.xujiaao.android.firmata.board.driver/-default-i2c-device/index.md) |
| [Joystick](../../com.xujiaao.android.firmata.board.driver/-joystick.md) | `fun `[`Board`](./index.md)`.Joystick(x: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, y: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, invertX: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, invertY: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`DefaultJoystick`](../../com.xujiaao.android.firmata.board.driver/-default-joystick/index.md) |
| [Led](../../com.xujiaao.android.firmata.board.driver/-led.md) | `fun `[`Board`](./index.md)`.Led(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultLed`](../../com.xujiaao.android.firmata.board.driver/-default-led/index.md)<br>`fun `[`Board`](./index.md)`.Led(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultLed`](../../com.xujiaao.android.firmata.board.driver/-default-led/index.md) |
| [Motor](../../com.xujiaao.android.firmata.board.driver/-motor.md) | `fun `[`Board`](./index.md)`.Motor(pwm: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = -1, cdir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = -1, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`DefaultMotor`](../../com.xujiaao.android.firmata.board.driver/-default-motor/index.md)<br>`fun `[`Board`](./index.md)`.Motor(pwm: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, dir: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, cdir: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`DefaultMotor`](../../com.xujiaao.android.firmata.board.driver/-default-motor/index.md) |
| [Pca9685](../../com.xujiaao.android.firmata.board.driver.pca9685/-pca9685.md) | `fun `[`Board`](./index.md)`.Pca9685(address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = PCA9685_ADDRESS, frequency: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = PCA9685_FREQ_DEFAULT): `[`Pca9685`](../../com.xujiaao.android.firmata.board.driver.pca9685/-pca9685/index.md) |
| [Pin](../../com.xujiaao.android.firmata.board.driver/-pin.md) | `fun `[`Board`](./index.md)`.Pin(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultPin`](../../com.xujiaao.android.firmata.board.driver/-default-pin/index.md)<br>`fun `[`Board`](./index.md)`.Pin(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultPin`](../../com.xujiaao.android.firmata.board.driver/-default-pin/index.md) |
| [QuadratureEncoder](../../com.xujiaao.android.firmata.board.driver/-quadrature-encoder.md) | `fun `[`Board`](./index.md)`.QuadratureEncoder(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinA: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinB: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultQuadratureEncoder`](../../com.xujiaao.android.firmata.board.driver/-default-quadrature-encoder/index.md)<br>`fun `[`Board`](./index.md)`.QuadratureEncoder(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinA: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, pinB: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultQuadratureEncoder`](../../com.xujiaao.android.firmata.board.driver/-default-quadrature-encoder/index.md) |
| [Servo](../../com.xujiaao.android.firmata.board.driver/-servo.md) | `fun `[`Board`](./index.md)`.Servo(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`DefaultServo`](../../com.xujiaao.android.firmata.board.driver/-default-servo/index.md)<br>`fun `[`Board`](./index.md)`.Servo(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`DefaultServo`](../../com.xujiaao.android.firmata.board.driver/-default-servo/index.md) |
| [animate](../animate.md) | `fun `[`Peripheral`](../-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](../-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](../-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](../animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](../animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [dumpProfile](../dump-profile.md) | `fun `[`Board`](./index.md)`.dumpProfile(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BoardWrapper](../-board-wrapper/index.md) | `open class BoardWrapper : `[`Board`](./index.md) |
| [DefaultBoard](../-default-board/index.md) | `class DefaultBoard : `[`BasePeripheralGroup`](../-base-peripheral-group/index.md)`, `[`Board`](./index.md) |
