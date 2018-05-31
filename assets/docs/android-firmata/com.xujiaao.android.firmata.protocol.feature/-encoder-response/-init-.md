[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [EncoderResponse](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`EncoderResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

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

