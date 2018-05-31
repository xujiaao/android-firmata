[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendI2cConfig](./send-i2c-config.md)

# sendI2cConfig

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendI2cConfig(delay: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

I2c config. The optional Delay is for I2c devices that require a delay between when the register
is written to and the data in that register can be startUpdating.

```
0  START_SYSEX (0xF0)
1  I2C_CONFIG (0x78)
2  Delay in microseconds (LSB) [optional]
3  Delay in microseconds (MSB) [optional]
... user defined for special cases, etc
n  END_SYSEX (0xF7)
```

