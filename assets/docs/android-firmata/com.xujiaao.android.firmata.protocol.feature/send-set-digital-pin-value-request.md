[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendSetDigitalPinValueRequest](./send-set-digital-pin-value-request.md)

# sendSetDigitalPinValueRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendSetDigitalPinValueRequest(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Set digital pin value (added in v2.5)

```
0  set digital pin value (0xF5) (MIDI Undefined)
1  set pin number (0-127)
2  value (LOW/HIGH, 0/1)
```

