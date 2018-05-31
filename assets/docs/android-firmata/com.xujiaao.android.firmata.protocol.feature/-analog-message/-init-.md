[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [AnalogMessage](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`AnalogMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

Analog 14-bit data format.

```
0  analog pin, 0xE0-0xEF, (MIDI Pitch Wheel)
1  analog least significant 7 bits
2  analog most significant 7 bits
```

