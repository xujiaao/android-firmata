[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendI2cStopReadingRequest](./send-i2c-stop-reading-request.md)

# sendI2cStopReadingRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendI2cStopReadingRequest(address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Stop I2c reading.

```
0  START_SYSEX (0xF0)
1  I2C_REQUEST (0x76)
2  slave address (LSB)
3  slave address (MSB) + startUpdating/write and address mode bits
{bit 7: always 0}
{bit 6: auto restart transmission, 0 = stop (default), 1 = restart}
{bit 5: address mode, 1 = 10-bit mode}
{bits 4-3: startUpdating/write, 00 = write, 01 = startUpdating once, 10 = startUpdating continuously, 11 = stop reading}
{bits 2-0: slave address MSB in 10-bit mode, not used in 7-bit mode}
4  data 0 (LSB)
5  data 0 (MSB)
6  data 1 (LSB)
7  data 1 (MSB)
...
n  END_SYSEX (0xF7)
```

