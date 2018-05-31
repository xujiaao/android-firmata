[android-firmata](../index.md) / [com.xujiaao.android.firmata.board](./index.md)

## Package com.xujiaao.android.firmata.board

### Types

| Name | Summary |
|---|---|
| [BasePeripheral](-base-peripheral/index.md) | `open class BasePeripheral : `[`Peripheral`](-peripheral/index.md) |
| [BasePeripheralGroup](-base-peripheral-group/index.md) | `open class BasePeripheralGroup : `[`BasePeripheral`](-base-peripheral/index.md)`, `[`PeripheralGroup`](-peripheral-group/index.md) |
| [Board](-board/index.md) | `interface Board : `[`PeripheralGroup`](-peripheral-group/index.md) |
| [BoardConnection](-board-connection/index.md) | `interface BoardConnection` |
| [BoardConnectionSpec](-board-connection-spec/index.md) | `class BoardConnectionSpec : `[`BoardConnection`](-board-connection/index.md) |
| [BoardService](-board-service/index.md) | `interface BoardService` |
| [BoardWrapper](-board-wrapper/index.md) | `open class BoardWrapper : `[`Board`](-board/index.md) |
| [DefaultBoard](-default-board/index.md) | `class DefaultBoard : `[`BasePeripheralGroup`](-base-peripheral-group/index.md)`, `[`Board`](-board/index.md) |
| [DefaultBoardService](-default-board-service/index.md) | `class DefaultBoardService : `[`BoardService`](-board-service/index.md) |
| [DefaultPinSpec](-default-pin-spec/index.md) | `class DefaultPinSpec : `[`PinSpec`](-board/-pin-spec/index.md) |
| [Peripheral](-peripheral/index.md) | `interface Peripheral : `[`Closeable`](http://docs.oracle.com/javase/6/docs/api/java/io/Closeable.html) |
| [PeripheralGroup](-peripheral-group/index.md) | `interface PeripheralGroup : `[`Peripheral`](-peripheral/index.md) |

### Type Aliases

| Name | Summary |
|---|---|
| [OnPeripheralCloseListener](-on-peripheral-close-listener.md) | `typealias OnPeripheralCloseListener = () -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Functions

| Name | Summary |
|---|---|
| [animate](animate.md) | `fun `[`Peripheral`](-peripheral/index.md)`.animate(animator: Animator): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun `[`Peripheral`](-peripheral/index.md)`.animate(interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, update: () -> `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T> `[`Peripheral`](-peripheral/index.md)`.animate(frames: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`T`](animate.md#T)`>, interval: `[`Long`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-long/index.html)`, repeat: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, reverse: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, interpolator: TimeInterpolator = LinearInterpolator(), update: (`[`T`](animate.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [connect](connect.md) | `fun `[`BoardConnection`](-board-connection/index.md)`.connect(transport: `[`Transport`](../com.xujiaao.android.firmata.transport/-transport/index.md)`): `[`BoardConnection`](-board-connection/index.md) |
| [connectBoard](connect-board.md) | `fun connectBoard(transport: `[`Transport`](../com.xujiaao.android.firmata.transport/-transport/index.md)`, connection: `[`BoardConnection`](-board-connection/index.md)`): `[`BoardConnection`](-board-connection/index.md)<br>`fun connectBoard(transport: `[`Transport`](../com.xujiaao.android.firmata.transport/-transport/index.md)`, init: `[`BoardConnectionSpec`](-board-connection-spec/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`BoardConnection`](-board-connection/index.md) |
| [connectBoardWithLifecycle](connect-board-with-lifecycle.md) | `fun connectBoardWithLifecycle(transport: `[`Transport`](../com.xujiaao.android.firmata.transport/-transport/index.md)`, lifecycle: Lifecycle, init: `[`BoardConnectionSpec`](-board-connection-spec/index.md)`.() -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`BoardConnection`](-board-connection/index.md) |
| [disconnect](disconnect.md) | `fun `[`BoardConnection`](-board-connection/index.md)`.disconnect(): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [disconnectBoard](disconnect-board.md) | `fun disconnectBoard(connection: `[`BoardConnection`](-board-connection/index.md)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [dumpProfile](dump-profile.md) | `fun `[`Board`](-board/index.md)`.dumpProfile(): `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |
| [isBoardConnected](is-board-connected.md) | `fun isBoardConnected(connection: `[`BoardConnection`](-board-connection/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isBoardConnecting](is-board-connecting.md) | `fun isBoardConnecting(connection: `[`BoardConnection`](-board-connection/index.md)`): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnected](is-connected.md) | `fun `[`BoardConnection`](-board-connection/index.md)`.isConnected(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [isConnecting](is-connecting.md) | `fun `[`BoardConnection`](-board-connection/index.md)`.isConnecting(): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
