[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [I2cReply](./index.md)

# I2cReply

`class I2cReply : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

I2c reply.

```
0  START_SYSEX (0xF0)
1  I2C_REPLY (0x77)
2  slave address (LSB)
3  slave address (MSB)
4  register (LSB)
5  register (MSB)
6  data 0 (LSB)
7  data 0 (MSB)
...
n  END_SYSEX (0XF7)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `I2cReply(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>I2c reply. |

### Properties

| Name | Summary |
|---|---|
| [address](address.md) | `val address: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [data](data.md) | `val data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html) |
| [register](register.md) | `val register: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`I2cReply`](./index.md)`>` |
