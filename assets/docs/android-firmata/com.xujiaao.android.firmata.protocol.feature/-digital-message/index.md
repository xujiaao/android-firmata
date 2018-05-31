[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [DigitalMessage](./index.md)

# DigitalMessage

`class DigitalMessage : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Two byte digital data format, second nibble of byte 0 gives the port number.

```
0  digital data, 0x90-0x9F, (MIDI NoteOn, bud different data format)
1  digital pins 0-6 bitmask
2  digital pin 7 bitmask
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `DigitalMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Two byte digital data format, second nibble of byte 0 gives the port number. |

### Properties

| Name | Summary |
|---|---|
| [port](port.md) | `val port: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [values](values.md) | `val values: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`MidiMessageParser`](../../com.xujiaao.android.firmata.protocol/-midi-message-parser/index.md)`<`[`DigitalMessage`](./index.md)`>` |
