[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol.feature](index.md) / [sendExtendedAnalogRequest](./send-extended-analog-request.md)

# sendExtendedAnalogRequest

`fun `[`Firmata`](../com.xujiaao.android.firmata.protocol/-firmata/index.md)`.sendExtendedAnalogRequest(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)

As an alternative to the normal analog message, this extended version allows addressing beyond
pin 15 and supports sending analog values with any number of bits.
The number of data bits is inferred by the length of the message.

```
0  START_SYSEX              (0xF0)
1  extended analog message  (0x6F)
2  pin                      (0-127)
3  bits 0-6                 (least significant byte)
4  bits 7-13                (most significant byte)
... additional bytes may be sent if more bits are needed
N  END_SYSEX                (0xF7)
```

