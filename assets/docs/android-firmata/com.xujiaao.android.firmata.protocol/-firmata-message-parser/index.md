[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol](../index.md) / [FirmataMessageParser](./index.md)

# FirmataMessageParser

`sealed class FirmataMessageParser<out T : `[`FirmataMessage`](../-firmata-message.md)`>`

### Functions

| Name | Summary |
|---|---|
| [parse](parse.md) | `abstract fun parse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`T`](index.md#T) |

### Inheritors

| Name | Summary |
|---|---|
| [MidiMessageParser](../-midi-message-parser/index.md) | `abstract class MidiMessageParser<out T : `[`FirmataMessage`](../-firmata-message.md)`> : `[`FirmataMessageParser`](./index.md)`<`[`T`](../-midi-message-parser/index.md#T)`>` |
| [SysexMessageParser](../-sysex-message-parser/index.md) | `abstract class SysexMessageParser<out T : `[`FirmataMessage`](../-firmata-message.md)`> : `[`FirmataMessageParser`](./index.md)`<`[`T`](../-sysex-message-parser/index.md#T)`>` |
