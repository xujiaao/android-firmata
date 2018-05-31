[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendPinStateQueryRequest](./send-pin-state-query-request.md)

# sendPinStateQueryRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendPinStateQueryRequest(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

Pin state query.

```
0  START_SYSEX              (0xF0)
1  pin state query          (0x6D)
2  pin                      (0-127)
3  END_SYSEX                (0xF7)
```

