[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [AnalogMappingResponse](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`AnalogMappingResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

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

