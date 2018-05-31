[android-firmata](../index.md) / [com.xujiaao.android.firmata.board.driver.pca9685](./index.md)

## Package com.xujiaao.android.firmata.board.driver.pca9685

### Types

| Name | Summary |
|---|---|
| [Pca9685](-pca9685/index.md) | `class Pca9685 : `[`BasePeripheralGroup`](../com.xujiaao.android.firmata.board/-base-peripheral-group/index.md) |
| [Pca9685Led](-pca9685-led/index.md) | `class Pca9685Led : `[`BaseLed`](../com.xujiaao.android.firmata.board.driver/-base-led/index.md) |
| [Pca9685Motor](-pca9685-motor/index.md) | `class Pca9685Motor : `[`BaseMotor`](../com.xujiaao.android.firmata.board.driver/-base-motor/index.md)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [Pca9685Pin](-pca9685-pin/index.md) | `class Pca9685Pin : `[`BasePeripheral`](../com.xujiaao.android.firmata.board/-base-peripheral/index.md)`, `[`Pin`](../com.xujiaao.android.firmata.board.driver/-pin/index.md) |
| [Pca9685Servo](-pca9685-servo/index.md) | `class Pca9685Servo : `[`BaseServo`](../com.xujiaao.android.firmata.board.driver/-base-servo/index.md) |

### Properties

| Name | Summary |
|---|---|
| [PCA9685_ADDRESS](-p-c-a9685_-a-d-d-r-e-s-s.md) | `const val PCA9685_ADDRESS: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [PCA9685_FREQ_DEFAULT](-p-c-a9685_-f-r-e-q_-d-e-f-a-u-l-t.md) | `const val PCA9685_FREQ_DEFAULT: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [PCA9685_FREQ_MAX](-p-c-a9685_-f-r-e-q_-m-a-x.md) | `const val PCA9685_FREQ_MAX: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |
| [PCA9685_FREQ_MIN](-p-c-a9685_-f-r-e-q_-m-i-n.md) | `const val PCA9685_FREQ_MIN: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html) |

### Functions

| Name | Summary |
|---|---|
| [Led](-led.md) | `fun `[`Pca9685`](-pca9685/index.md)`.Led(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pca9685Led`](-pca9685-led/index.md) |
| [Motor](-motor.md) | `fun `[`Pca9685`](-pca9685/index.md)`.Motor(pwm: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, dir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, cdir: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`? = null, invertPwm: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`? = null, threshold: `[`Float`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-float/index.html)`? = null): `[`Pca9685Motor`](-pca9685-motor/index.md) |
| [Pca9685](-pca9685.md) | `fun `[`Board`](../com.xujiaao.android.firmata.board/-board/index.md)`.Pca9685(address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = PCA9685_ADDRESS, frequency: `[`Double`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-double/index.html)` = PCA9685_FREQ_DEFAULT): `[`Pca9685`](-pca9685/index.md) |
| [Pin](-pin.md) | `fun `[`Pca9685`](-pca9685/index.md)`.Pin(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Pca9685Pin`](-pca9685-pin/index.md) |
| [Servo](-servo.md) | `fun `[`Pca9685`](-pca9685/index.md)`.Servo(channel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, angleRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null, pwmRange: `[`IntRange`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.ranges/-int-range/index.html)`? = null): `[`Pca9685Servo`](-pca9685-servo/index.md) |
