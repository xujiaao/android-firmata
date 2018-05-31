[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [FirmwareReport](index.md) / [&lt;init&gt;](./-init-.md)

# &lt;init&gt;

`FirmwareReport(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`

Receive Firmware Name and Version (after query).

```
0  START_SYSEX       (0xF0)
1  queryFirmware     (0x79)
2  major version     (0-127)
3  minor version     (0-127)
4  first char of firmware name (LSB)
5  first char of firmware name (MSB)
6  second char of firmware name (LSB)
7  second char of firmware name (MSB)
... for as many bytes as it needs
N  END_SYSEX         (0xF7)
```

