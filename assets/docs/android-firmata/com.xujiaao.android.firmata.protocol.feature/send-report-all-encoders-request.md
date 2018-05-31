[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendReportAllEncodersRequest](./send-report-all-encoders-request.md)

# sendReportAllEncodersRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendReportAllEncodersRequest(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Report all encoders positions.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 ENCODER_REPORT_POSITIONS   (0x02)
3 END_SYSEX                  (0xF7)
```

