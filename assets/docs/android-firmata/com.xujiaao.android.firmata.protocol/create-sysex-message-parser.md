[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol](index.md) / [createSysexMessageParser](./create-sysex-message-parser.md)

# createSysexMessageParser

`inline fun <reified T : `[`FirmataMessage`](-firmata-message.md)`> createSysexMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`, crossinline parser: (`[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`) -> `[`T`](create-sysex-message-parser.md#T)`): `[`SysexMessageParser`](-sysex-message-parser/index.md)`<`[`T`](create-sysex-message-parser.md#T)`>`