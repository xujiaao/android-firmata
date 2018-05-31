[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol](../index.md) / [SysexMessageParser](./index.md)

# SysexMessageParser

`abstract class SysexMessageParser<out T : `[`FirmataMessage`](../-firmata-message.md)`> : `[`FirmataMessageParser`](../-firmata-message-parser/index.md)`<`[`T`](index.md#T)`>`

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `SysexMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`)` |

### Properties

| Name | Summary |
|---|---|
| [command](command.md) | `val command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html) |

### Inherited Functions

| Name | Summary |
|---|---|
| [parse](../-firmata-message-parser/parse.md) | `abstract fun parse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`T`](../-firmata-message-parser/index.md#T) |
