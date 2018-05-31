[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendDetachEncoderRequest](./send-detach-encoder-request.md)

# sendDetachEncoderRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendDetachEncoderRequest(encoder: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Detach encoder query.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_DETACH             (0x05)
3 encoder #                  ([0 - MAX_ENCODERS-1])
4 END_SYSEX                  (0xF7)
```

