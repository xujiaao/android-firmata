[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [DigitalMessage](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`DigitalMessage(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

Two byte digital data format, second nibble of byte 0 gives the port number.

```
0  digital data, 0x90-0x9F, (MIDI NoteOn, bud different data format)
1  digital pins 0-6 bitmask
2  digital pin 7 bitmask
```

