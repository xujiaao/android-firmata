[android-firmata](../../index.md) / [com.xujiaao.android.firmata.transport](../index.md) / [BluetoothClientTransport](./index.md)

# BluetoothClientTransport

`abstract class BluetoothClientTransport : `[`BluetoothTransport`](../-bluetooth-transport/index.md)

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `BluetoothClientTransport()` |

### Functions

| Name | Summary |
|---|---|
| [getBluetoothDevice](get-bluetooth-device.md) | `abstract fun getBluetoothDevice(): BluetoothDevice` |
| [getBluetoothSocket](get-bluetooth-socket.md) | `open fun getBluetoothSocket(): BluetoothSocket` |

### Inherited Functions

| Name | Summary |
|---|---|
| [connect](../-bluetooth-transport/connect.md) | `open fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](../-bluetooth-transport/disconnect.md) | `open fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [read](../-bluetooth-transport/read.md) | `open fun read(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [write](../-bluetooth-transport/write.md) | `open fun write(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [AddressedBluetoothClientTransport](../-addressed-bluetooth-client-transport/index.md) | `class AddressedBluetoothClientTransport : `[`BluetoothClientTransport`](./index.md) |
| [NamedBluetoothClientTransport](../-named-bluetooth-client-transport/index.md) | `class NamedBluetoothClientTransport : `[`BluetoothClientTransport`](./index.md) |
