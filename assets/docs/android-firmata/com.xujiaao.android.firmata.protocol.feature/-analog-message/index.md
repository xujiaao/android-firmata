[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [AnalogMessage](./index.md)

# AnalogMessage

`class AnalogMessage : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Analog 14-bit data format.

```
0  analog pin, 0xE0-0xEF, (MIDI Pitch Wheel)
1  analog least significant 7 bits
2  analog most significant 7 bits
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AnalogMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Analog 14-bit data format. |

### Properties

| Name | Summary |
|---|---|
| [pin](pin.md) | `val pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [value](value.md) | `val value: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`MidiMessageParser`](../../com.xujiaao.android.firmata.protocol/-midi-message-parser/index.md)`<`[`AnalogMessage`](./index.md)`>` |
