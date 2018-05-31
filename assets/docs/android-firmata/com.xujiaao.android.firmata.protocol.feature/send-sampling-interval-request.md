[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendSamplingIntervalRequest](./send-sampling-interval-request.md)

# sendSamplingIntervalRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendSamplingIntervalRequest(samplingInterval: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

The sampling interval sets how often analog data and i2c data is reported to the client.
The default for the arduino implementation is 19ms. This means that every 19ms analog data
will be reported and any i2c devices with startUpdating continuous mode will be startUpdating.

```
0  START_SYSEX        (0xF0)
1  SAMPLING_INTERVAL  (0x7A)
2  sampling interval on the millisecond time scale (LSB)
3  sampling interval on the millisecond time scale (MSB)
4  END_SYSEX          (0xF7)
```

