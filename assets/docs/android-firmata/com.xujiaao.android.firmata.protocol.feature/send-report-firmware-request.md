[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendReportFirmwareRequest](./send-report-firmware-request.md)

# sendReportFirmwareRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendReportFirmwareRequest(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Query firmware Name and Version.

```
0  START_SYSEX       (0xF0)
1  queryFirmware     (0x79)
2  END_SYSEX         (0xF7)
```

