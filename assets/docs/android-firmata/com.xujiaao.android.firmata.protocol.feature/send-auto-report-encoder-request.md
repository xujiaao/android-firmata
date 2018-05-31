[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendAutoReportEncoderRequest](./send-auto-report-encoder-request.md)

# sendAutoReportEncoderRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendAutoReportEncoderRequest(enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Enable/disable reporting. Note: when reports are enabled, EncoderFirmata feature send the
message at every SAMPLING interval (19ms by default).

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_REPORT_AUTO        (0x04)
3 enable                     (0x00 => false, true otherwise)
4 END_SYSEX                  (0xF7)
```

