[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendAttachEncoderRequest](./send-attach-encoder-request.md)

# sendAttachEncoderRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendAttachEncoderRequest(encoder: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinA: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, pinB: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Attach encoder query.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_ATTACH             (0x00)
3 encoder #                  ([0 - MAX_ENCODERS-1])
4 pin A #                    (first pin)
5 pin B #                    (second pin)
6 END_SYSEX                  (0xF7)
```

