[android-firmata](../../index.md) / [com.xujiaao.android.firmata.board](../index.md) / [BoardConnectionSpec](./index.md)

# BoardConnectionSpec

`class BoardConnectionSpec : `[`BoardConnection`](../-board-connection/index.md)

### Functions

| Name | Summary |
|---|---|
| [onBoardConnected](on-board-connected.md) | `fun onBoardConnected(board: `[`Board`](../-board/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBoardConnecting](on-board-connecting.md) | `fun onBoardConnecting(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onBoardDisconnected](on-board-disconnected.md) | `fun onBoardDisconnected(error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onConnected](on-connected.md) | `fun onConnected(action: (board: `[`Board`](../-board/index.md)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onConnecting](on-connecting.md) | `fun onConnecting(action: () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [onDisconnected](on-disconnected.md) | `fun onDisconnected(action: (error: `[`Throwable`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-throwable/index.html)`?) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Extension Functions

| Name | Summary |
|---|---|
| [connect](../connect.md) | `fun `[`BoardConnection`](../-board-connection/index.md)`.connect(transport: `[`Transport`](../../com.xujiaao.android.firmata.transport/-transport/index.md)`): `[`BoardConnection`](../-board-connection/index.md) |
| [disconnect](../disconnect.md) | `fun `[`BoardConnection`](../-board-connection/index.md)`.disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [isConnected](../is-connected.md) | `fun `[`BoardConnection`](../-board-connection/index.md)`.isConnected(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnecting](../is-connecting.md) | `fun `[`BoardConnection`](../-board-connection/index.md)`.isConnecting(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
