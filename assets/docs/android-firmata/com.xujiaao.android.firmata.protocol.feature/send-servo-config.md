[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendServoConfig](./send-servo-config.md)

# sendServoConfig

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendServoConfig(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, min: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, max: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Send a Servo config message to configure a pin a servo.

```
// minPulse and maxPulse are 14-bit unsigned integers
0  START_SYSEX          (0xF0)
1  SERVO_CONFIG         (0x70)
2  pin number           (0-127)
3  minPulse LSB         (0-6)
4  minPulse MSB         (7-13)
5  maxPulse LSB         (0-6)
6  maxPulse MSB         (7-13)
7  END_SYSEX            (0xF7)
```

