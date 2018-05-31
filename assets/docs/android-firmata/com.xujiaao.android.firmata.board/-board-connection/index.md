[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [BoardConnection](./index.md)

# BoardConnection

`interface BoardConnection`

### Functions

| Name | Summary |
|---|---|
| [onBoardConnected](on-board-connected.md) | `abstract fun onBoardConnected(board: `[`Board`](../-board/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBoardConnecting](on-board-connecting.md) | `abstract fun onBoardConnecting(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBoardDisconnected](on-board-disconnected.md) | `abstract fun onBoardDisconnected(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [connect](../connect.md) | `fun `[`BoardConnection`](./index.md)`.connect(transport: `[`Transport`](../../com.xujiaao.android.firmata.transport/-transport/index.md)`): `[`BoardConnection`](./index.md) |
| [disconnect](../disconnect.md) | `fun `[`BoardConnection`](./index.md)`.disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isConnected](../is-connected.md) | `fun `[`BoardConnection`](./index.md)`.isConnected(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnecting](../is-connecting.md) | `fun `[`BoardConnection`](./index.md)`.isConnecting(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |

### Inheritors

| Name | Summary |
|---|---|
| [BoardConnectionSpec](../-board-connection-spec/index.md) | `class BoardConnectionSpec : `[`BoardConnection`](./index.md) |
