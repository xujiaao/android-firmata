[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendReportAnalogRequest](./send-report-analog-request.md)

# sendReportAnalogRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendReportAnalogRequest(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Toggle analogIn reporting by pin.

```
0  toggle analogIn reporting (0xC0-0xCF) (MIDI Program Change)
1  disable(0) / enable(non-zero)
```

