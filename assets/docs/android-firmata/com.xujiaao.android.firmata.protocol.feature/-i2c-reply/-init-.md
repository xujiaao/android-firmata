[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [I2cReply](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`I2cReply(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

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

