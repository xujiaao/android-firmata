[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendReportEncoderRequest](./send-report-encoder-request.md)

# sendReportEncoderRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendReportEncoderRequest(encoder: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Report encoder's position.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_REPORT_POSITION    (0x01)
3 Encoder #                  ([0 - MAX_ENCODERS-1])
4 END_SYSEX                  (0xF7)
```

