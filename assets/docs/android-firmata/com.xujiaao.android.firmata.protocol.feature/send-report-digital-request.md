[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendReportDigitalRequest](./send-report-digital-request.md)

# sendReportDigitalRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendReportDigitalRequest(port: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Toggle digital port reporting by port (second nibble of byte 0).

```
0  toggle digital port reporting (0xD0-0xDF) (MIDI Aftertouch)
1  disable(0) / enable(non-zero)
```

