[android-firmata](../../index.md) / [com.xujiaao.android.firmata.transport](../index.md) / [TransportWrapper](./index.md)

# TransportWrapper

`abstract class TransportWrapper : `[`Transport`](../-transport/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `TransportWrapper()` |

### Functions

| Name | Summary |
|---|---|
| [connect](connect.md) | `open fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](disconnect.md) | `open fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getBase](get-base.md) | `abstract fun getBase(): `[`Transport`](../-transport/index.md) |
| [read](read.md) | `open fun read(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [write](write.md) | `open fun write(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
