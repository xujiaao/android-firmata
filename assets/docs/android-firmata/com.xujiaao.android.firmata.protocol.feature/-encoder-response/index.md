[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [EncoderResponse](./index.md)

# EncoderResponse

`class EncoderResponse : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Encoder response.

```
0 START_SYSEX                (0xF0)
1 ENCODER_DATA               (0x61)
2 first enc. #  & first enc. dir.   [= (direction << 6) | (#)]
4 first enc. position, bits 0-6
5 first enc. position, bits 7-13
6 first enc. position, bits 14-20
7 first enc. position, bits 21-27
8 second enc. #  & second enc. dir. [= (direction << 6) | (#)]
...
N END_SYSEX                  (0xF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `EncoderResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Encoder response. |

### Functions

| Name | Summary |
|---|---|
| [getPosition](get-position.md) | `fun getPosition(encoder: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`EncoderResponse`](./index.md)`>` |
