[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol](../index.md) / [FirmataProcessor](./index.md)

# FirmataProcessor

`interface FirmataProcessor`

### Functions

| Name | Summary |
|---|---|
| [attachParser](attach-parser.md) | `abstract fun attachParser(parser: `[`FirmataMessageParser`](../-firmata-message-parser/index.md)`<*>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [detachParser](detach-parser.md) | `abstract fun detachParser(parser: `[`FirmataMessageParser`](../-firmata-message-parser/index.md)`<*>): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [process](process.md) | `abstract fun process(curr: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, callback: (`[`FirmataMessage`](../-firmata-message.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultFirmataProcessor](../-default-firmata-processor/index.md) | `class DefaultFirmataProcessor : `[`FirmataProcessor`](./index.md) |
