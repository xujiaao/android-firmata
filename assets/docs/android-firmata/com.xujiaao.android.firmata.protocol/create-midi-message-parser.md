[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol](index.md) / [createMidiMessageParser](./create-midi-message-parser.md)

# createMidiMessageParser

`inline fun <reified T : `[`FirmataMessage`](-firmata-message.md)`> createMidiMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, crossinline parser: (`[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`) -> `[`T`](create-midi-message-parser.md#T)`): `[`MidiMessageParser`](-midi-message-parser/index.md)`<`[`T`](create-midi-message-parser.md#T)`>`