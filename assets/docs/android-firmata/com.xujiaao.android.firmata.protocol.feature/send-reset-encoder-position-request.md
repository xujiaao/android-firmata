[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendResetEncoderPositionRequest](./send-reset-encoder-position-request.md)

# sendResetEncoderPositionRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendResetEncoderPositionRequest(encoder: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Reset encoder position to zero.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_RESET_POSITION     (0x03)
3 encoder #                  ([0 - MAX_ENCODERS-1])
4 END_SYSEX                  (0xF7)
```

