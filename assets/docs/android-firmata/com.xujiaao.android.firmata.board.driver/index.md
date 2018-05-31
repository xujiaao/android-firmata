[android-firmata](../index.md) / [com.xujiaao.android.firmata.board.driver](./index.md)

## Package com.xujiaao.android.firmata.board.driver

### Types

| Name | Summary |
|---|---|
| [BaseLed](-base-led/index.md) | `abstract class BaseLed : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Led`](-led/index.md) |
| [BaseMotor](-base-motor/index.md) | `abstract class BaseMotor<T> : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Motor`](-motor/index.md) |
| [BaseServo](-base-servo/index.md) | `abstract class BaseServo : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Servo`](-servo/index.md) |
| [DefaultI2cDevice](-default-i2c-device/index.md) | `class DefaultI2cDevice : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`I2cDevice`](-i2c-device/index.md) |
| [DefaultJoystick](-default-joystick/index.md) | `class DefaultJoystick : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Joystick`](-joystick/index.md) |
| [DefaultLed](-default-led/index.md) | `class DefaultLed : `[`BaseLed`](-base-led/index.md) |
| [DefaultMotor](-default-motor/index.md) | `class DefaultMotor : `[`BaseMotor`](-base-motor/index.md)`<`[`Pin`](-pin/index.md)`>` |
| [DefaultPin](-default-pin/index.md) | `class DefaultPin : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Pin`](-pin/index.md) |
| [DefaultQuadratureEncoder](-default-quadrature-encoder/index.md) | `class DefaultQuadratureEncoder : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`QuadratureEncoder`](-quadrature-encoder/index.md) |
| [DefaultServo](-default-servo/index.md) | `class DefaultServo : `[`BaseServo`](-base-servo/index.md) |
| [I2cDevice](-i2c-device/index.md) | `interface I2cDevice : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [Joystick](-joystick/index.md) | `interface Joystick : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [Led](-led/index.md) | `interface Led : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [Motor](-motor/index.md) | `interface Motor : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [Pin](-pin/index.md) | `interface Pin : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [QuadratureEncoder](-quadrature-encoder/index.md) | `interface QuadratureEncoder : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |
| [Servo](-servo/index.md) | `interface Servo : `[`Peripheral`](../com.xujiaao.android.firmata.board/-peripheral/index.md) |

### Type Aliases

| Name | Summary |
|---|---|
| [I2cReadCallback](-i2c-read-callback.md) | `typealias I2cReadCallback = `[`I2cDevice`](-i2c-device/index.md)`.(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Functions

| Name | Summary |
|---|---|
| [I2cDevice](-i2c-device.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.I2cDevice(address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultI2cDevice`](-default-i2c-device/index.md) |
| [Joystick](-joystick.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Joystick(x: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, y: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, invertX: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, invertY: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false): `[`DefaultJoystick`](-default-joystick/index.md) |
| [Led](-led.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Led(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultLed`](-default-led/index.md)<br>`fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Led(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultLed`](-default-led/index.md) |
| [Motor](-motor.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Motor(pwm: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = -1, cdir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = -1, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`DefaultMotor`](-default-motor/index.md)<br>`fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Motor(pwm: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, dir: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, cdir: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`? = null, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`DefaultMotor`](-default-motor/index.md) |
| [Pin](-pin.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Pin(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultPin`](-default-pin/index.md)<br>`fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Pin(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultPin`](-default-pin/index.md) |
| [QuadratureEncoder](-quadrature-encoder.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.QuadratureEncoder(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinA: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinB: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`DefaultQuadratureEncoder`](-default-quadrature-encoder/index.md)<br>`fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.QuadratureEncoder(id: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinA: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, pinB: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`): `[`DefaultQuadratureEncoder`](-default-quadrature-encoder/index.md) |
| [Servo](-servo.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Servo(pinAddress: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`DefaultServo`](-default-servo/index.md)<br>`fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Servo(pinName: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`DefaultServo`](-default-servo/index.md) |
