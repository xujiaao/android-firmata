[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [CapabilityResponse](./index.md)

# CapabilityResponse

`class CapabilityResponse : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Capabilities response.

```
0  START_SYSEX              (0xF0)
1  CAPABILITY_RESPONSE      (0x6C)
2  1st supported mode of pin 0
3  1st mode's resolution of pin 0
4  2nd supported mode of pin 0
5  2nd mode's resolution of pin 0
... additional modes/resolutions, followed by `0x7F`,
to mark the end of the pin's modes. Subsequently, each pin
follows with its modes/resolutions and `0x7F`,
until all mPins are defined.
N  END_SYSEX                (0xF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `CapabilityResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Capabilities response. |

### Functions

| Name | Summary |
|---|---|
| [getPinModes](get-pin-modes.md) | `fun getPinModes(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Map`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.collections/-map/index.html)`<`[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`>` |
| [getPinsCount](get-pins-count.md) | `fun getPinsCount(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`CapabilityResponse`](./index.md)`>` |
