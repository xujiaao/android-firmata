[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [StringMessage](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`StringMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

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

