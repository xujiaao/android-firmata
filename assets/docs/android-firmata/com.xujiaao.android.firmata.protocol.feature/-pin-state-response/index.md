[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [PinStateResponse](./index.md)

# PinStateResponse

`class PinStateResponse : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Pin state response.

Note about pin state: For output modes, the state is any digitalValue that has been previously written
to the pin. For input modes, the state is the status of the pull-up resistor.

```
0  START_SYSEX              (0xF0)
1  pin state response       (0x6E)
2  pin                      (0-127)
3  pin mode (the currently configured mode)
4  pin state, bits 0-6
5  (optional) pin state, bits 7-13
6  (optional) pin state, bits 14-20
... additional optional bytes, as many as needed
N  END_SYSEX                (0xF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `PinStateResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Pin state response. |

### Properties

| Name | Summary |
|---|---|
| [mode](mode.md) | `val mode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [pin](pin.md) | `val pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [state](state.md) | `val state: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`FirmwareReport`](../-firmware-report/index.md)`>` |
