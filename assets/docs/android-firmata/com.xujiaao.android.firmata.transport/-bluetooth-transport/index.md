[android-firmata](../../index.md) / [com.xujiaao.android.firmata.transport](../index.md) / [BluetoothTransport](./index.md)

# BluetoothTransport

`abstract class BluetoothTransport : `[`Transport`](../-transport/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BluetoothTransport()` |

### Functions

| Name | Summary |
|---|---|
| [connect](connect.md) | `open fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](disconnect.md) | `open fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [getBluetoothSocket](get-bluetooth-socket.md) | `abstract fun getBluetoothSocket(): BluetoothSocket` |
| [read](read.md) | `open fun read(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [write](write.md) | `open fun write(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BluetoothClientTransport](../-bluetooth-client-transport/index.md) | `abstract class BluetoothClientTransport : `[`BluetoothTransport`](./index.md) |
