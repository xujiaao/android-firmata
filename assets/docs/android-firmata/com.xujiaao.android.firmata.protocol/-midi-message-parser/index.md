[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol](../index.md) / [MidiMessageParser](./index.md)

# MidiMessageParser

`abstract class MidiMessageParser<out T : `[`FirmataMessage`](../-firmata-message.md)`> : `[`FirmataMessageParser`](../-firmata-message-parser/index.md)`<`[`T`](index.md#T)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `MidiMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [command](command.md) | `val command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html) |
| [length](length.md) | `val length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [parse](../-firmata-message-parser/parse.md) | `abstract fun parse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`T`](../-firmata-message-parser/index.md#T) |
