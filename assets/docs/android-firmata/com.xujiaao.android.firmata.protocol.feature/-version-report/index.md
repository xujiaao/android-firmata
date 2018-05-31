[android-firmata](../../index.md) / [com.xujiaao.android.firmata.protocol.feature](../index.md) / [VersionReport](./index.md)

# VersionReport

`class VersionReport : `[`FirmataMessage`](../../com.xujiaao.android.firmata.protocol/-firmata-message.md)

Version report.

```
0  version report header (0xF9) (MIDI Undefined)
1  major version (0-127)
2  minor version (0-127)
```

### Constructors

| Name | Summary |
|---|---|
| [&lt;init&gt;](-init-.md) | `VersionReport(data: `[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`)`<br>Version report. |

### Properties

| Name | Summary |
|---|---|
| [majorVersion](major-version.md) | `val majorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [minorVersion](minor-version.md) | `val minorVersion: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |

### Companion Object Properties

| Name | Summary |
|---|---|
| [PARSER](-p-a-r-s-e-r.md) | `val PARSER: `[`MidiMessageParser`](../../com.xujiaao.android.firmata.protocol/-midi-message-parser/index.md)`<`[`VersionReport`](./index.md)`>` |
