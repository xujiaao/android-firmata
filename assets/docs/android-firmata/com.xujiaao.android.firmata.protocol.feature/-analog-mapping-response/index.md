[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [AnalogMappingResponse](./index.md)

# AnalogMappingResponse

`class AnalogMappingResponse : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Analog mMapping response.

```
0  START_SYSEX              (0xF0)
1  analog mMapping response  (0x6A)
2  analog channel corresponding to pin 0, or 127 if pin 0 does not support analog
3  analog channel corresponding to pin 1, or 127 if pin 1 does not support analog
4  analog channel corresponding to pin 2, or 127 if pin 2 does not support analog
... etc, one byte for each pin
N  END_SYSEX                (0xF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `AnalogMappingResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Analog mMapping response. |

### Functions

| Name | Summary |
|---|---|
| [getAnalogChannel](get-analog-channel.md) | `fun getAnalogChannel(pin: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [getAnalogPin](get-analog-pin.md) | `fun getAnalogPin(analogChannel: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`AnalogMappingResponse`](./index.md)`>` |
