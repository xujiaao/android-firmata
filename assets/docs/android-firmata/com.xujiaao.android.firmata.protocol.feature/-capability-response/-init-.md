[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [CapabilityResponse](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`CapabilityResponse(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

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

