[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol](index.md) / [registerReceiver](./register-receiver.md)

# registerReceiver

`inline fun <reified T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.registerReceiver(noinline receiver: (`[`T`](register-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)
`fun <T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.registerReceiver(type: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](register-receiver.md#T)`>, receiver: (`[`T`](register-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)