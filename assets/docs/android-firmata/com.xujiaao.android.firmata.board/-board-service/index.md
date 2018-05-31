[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [BoardService](./index.md)

# BoardService

`interface BoardService`

### Types

| Name | Summary |
|---|---|
| [OnConnectionChangeListener](-on-connection-change-listener/index.md) | `interface OnConnectionChangeListener` |

### Functions

| Name | Summary |
|---|---|
| [addOnConnectionChangeListener](add-on-connection-change-listener.md) | `abstract fun addOnConnectionChangeListener(listener: `[`OnConnectionChangeListener`](-on-connection-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [connect](connect.md) | `abstract fun connect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnect](disconnect.md) | `abstract fun disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isConnected](is-connected.md) | `abstract fun isConnected(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [removeOnConnectionChangeListener](remove-on-connection-change-listener.md) | `abstract fun removeOnConnectionChangeListener(listener: `[`OnConnectionChangeListener`](-on-connection-change-listener/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [DefaultBoardService](../-default-board-service/index.md) | `class DefaultBoardService : `[`BoardService`](./index.md) |
