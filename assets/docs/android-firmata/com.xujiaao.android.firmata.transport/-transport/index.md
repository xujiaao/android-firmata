[android-firmata](../../index.md) / [com.xujiaao.android.firmata.transport](../index.md) / [Transport](./index.md)

# Transport

`interface Transport`

### Functions

| Name | Summary |
|---|---|
| [connect](connect.md) | `abstract fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](disconnect.md) | `abstract fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [read](read.md) | `abstract fun read(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [write](write.md) | `abstract fun write(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BluetoothTransport](../-bluetooth-transport/index.md) | `abstract class BluetoothTransport : `[`Transport`](./index.md) |
| [NetworkTransport](../-network-transport/index.md) | `class NetworkTransport : `[`Transport`](./index.md) |
| [TransportWrapper](../-transport-wrapper/index.md) | `abstract class TransportWrapper : `[`Transport`](./index.md) |
