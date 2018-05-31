[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [FirmwareReport](./index.md)

# FirmwareReport

`class FirmwareReport : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

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

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `FirmwareReport(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Receive Firmware Name and Version (after query). |

### Properties

| Name | Summary |
|---|---|
| [majorVersion](major-version.md) | `val majorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [minorVersion](minor-version.md) | `val minorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [name](name.md) | `val name: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`SysexMessageParser`](../../com.xujiaao.android.firmata.protocol/-sysex-message-parser/index.md)`<`[`FirmwareReport`](./index.md)`>` |
