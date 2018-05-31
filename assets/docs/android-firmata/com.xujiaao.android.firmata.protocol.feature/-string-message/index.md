[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [StringMessage](./index.md)

# StringMessage

`class StringMessage : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Receive string.

```
0  START_SYSEX        (0xF0)
1  STRING_DATA        (0x71)
2  first char LSB
3  first char MSB
4  second char LSB
5  second char MSB
... additional bytes up to half the buffer size - 3 (START_SYSEX, STRING_DATA, END_SYSEX)
N  END_SYSEX          (0xF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `StringMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Receive string. |

### Properties

| Name | Summary |
|---|---|
| [string](string.md) | `val string: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`FirmwareReport`](../-firmware-report/index.md)`>` |
