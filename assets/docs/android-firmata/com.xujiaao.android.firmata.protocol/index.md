[android-firmata](../index.md) / [com.xujiaao.android.firmata.protocol](./index.md)

## Package com.xujiaao.android.firmata.protocol

### Types

| Name | Summary |
|---|---|
| [DefaultFirmata](-default-firmata/index.md) | `class DefaultFirmata : `[`Firmata`](-firmata/index.md) |
| [DefaultFirmataProcessor](-default-firmata-processor/index.md) | `class DefaultFirmataProcessor : `[`FirmataProcessor`](-firmata-processor/index.md) |
| [Firmata](-firmata/index.md) | `interface Firmata` |
| [FirmataMessage](-firmata-message.md) | `interface FirmataMessage` |
| [FirmataMessageParser](-firmata-message-parser/index.md) | `sealed class FirmataMessageParser<out T : `[`FirmataMessage`](-firmata-message.md)`>` |
| [FirmataProcessor](-firmata-processor/index.md) | `interface FirmataProcessor` |
| [MidiMessageParser](-midi-message-parser/index.md) | `abstract class MidiMessageParser<out T : `[`FirmataMessage`](-firmata-message.md)`> : `[`FirmataMessageParser`](-firmata-message-parser/index.md)`<`[`T`](-midi-message-parser/index.md#T)`>` |
| [SysexMessageParser](-sysex-message-parser/index.md) | `abstract class SysexMessageParser<out T : `[`FirmataMessage`](-firmata-message.md)`> : `[`FirmataMessageParser`](-firmata-message-parser/index.md)`<`[`T`](-sysex-message-parser/index.md#T)`>` |

### Properties

| Name | Summary |
|---|---|
| [MIDI_ANALOG_MESSAGE](-m-i-d-i_-a-n-a-l-o-g_-m-e-s-s-a-g-e.md) | `const val MIDI_ANALOG_MESSAGE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Send data for an analog pin (or PWM). |
| [MIDI_DIGITAL_MESSAGE](-m-i-d-i_-d-i-g-i-t-a-l_-m-e-s-s-a-g-e.md) | `const val MIDI_DIGITAL_MESSAGE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Send data for a digital port (collection of 8 pins). |
| [MIDI_END_SYSEX](-m-i-d-i_-e-n-d_-s-y-s-e-x.md) | `const val MIDI_END_SYSEX: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>End a MIDI Sysex message. |
| [MIDI_REPORT_ANALOG](-m-i-d-i_-r-e-p-o-r-t_-a-n-a-l-o-g.md) | `const val MIDI_REPORT_ANALOG: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Enable analog input by pin #. |
| [MIDI_REPORT_DIGITAL](-m-i-d-i_-r-e-p-o-r-t_-d-i-g-i-t-a-l.md) | `const val MIDI_REPORT_DIGITAL: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Enable digital input by port pair. |
| [MIDI_REPORT_VERSION](-m-i-d-i_-r-e-p-o-r-t_-v-e-r-s-i-o-n.md) | `const val MIDI_REPORT_VERSION: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Report protocol version. |
| [MIDI_SET_DIGITAL_PIN_VALUE](-m-i-d-i_-s-e-t_-d-i-g-i-t-a-l_-p-i-n_-v-a-l-u-e.md) | `const val MIDI_SET_DIGITAL_PIN_VALUE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Set digitalValue of an individual digital pin. |
| [MIDI_SET_PIN_MODE](-m-i-d-i_-s-e-t_-p-i-n_-m-o-d-e.md) | `const val MIDI_SET_PIN_MODE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Set a pin to INPUT/OUTPUT/PWM/etc. |
| [MIDI_START_SYSEX](-m-i-d-i_-s-t-a-r-t_-s-y-s-e-x.md) | `const val MIDI_START_SYSEX: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Start a MIDI Sysex message. |
| [MIDI_SYSTEM_RESET](-m-i-d-i_-s-y-s-t-e-m_-r-e-s-e-t.md) | `const val MIDI_SYSTEM_RESET: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Reset from MIDI. |
| [PIN_MODE_ANALOG](-p-i-n_-m-o-d-e_-a-n-a-l-o-g.md) | `const val PIN_MODE_ANALOG: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Analog pin in analogInput mode. |
| [PIN_MODE_ENCODER](-p-i-n_-m-o-d-e_-e-n-c-o-d-e-r.md) | `const val PIN_MODE_ENCODER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin configured for rotary encoders. |
| [PIN_MODE_I2C](-p-i-n_-m-o-d-e_-i2-c.md) | `const val PIN_MODE_I2C: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin included in I2C setup. |
| [PIN_MODE_IGNORE](-p-i-n_-m-o-d-e_-i-g-n-o-r-e.md) | `const val PIN_MODE_IGNORE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin configured to be ignored by digitalWrite and capabilityResponse |
| [PIN_MODE_INPUT](-p-i-n_-m-o-d-e_-i-n-p-u-t.md) | `const val PIN_MODE_INPUT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Input pin mode. |
| [PIN_MODE_ONEWIRE](-p-i-n_-m-o-d-e_-o-n-e-w-i-r-e.md) | `const val PIN_MODE_ONEWIRE: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin configured for 1-wire. |
| [PIN_MODE_OUTPUT](-p-i-n_-m-o-d-e_-o-u-t-p-u-t.md) | `const val PIN_MODE_OUTPUT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Output pin mode. |
| [PIN_MODE_PULLUP](-p-i-n_-m-o-d-e_-p-u-l-l-u-p.md) | `const val PIN_MODE_PULLUP: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Enable internal pull-up resistor for pin. |
| [PIN_MODE_PWM](-p-i-n_-m-o-d-e_-p-w-m.md) | `const val PIN_MODE_PWM: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Digital pin in PWM output mode. |
| [PIN_MODE_SERIAL](-p-i-n_-m-o-d-e_-s-e-r-i-a-l.md) | `const val PIN_MODE_SERIAL: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin configured for serial communication. |
| [PIN_MODE_SERVO](-p-i-n_-m-o-d-e_-s-e-r-v-o.md) | `const val PIN_MODE_SERVO: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Digital pin in Servo output mode. |
| [PIN_MODE_SHIFT](-p-i-n_-m-o-d-e_-s-h-i-f-t.md) | `const val PIN_MODE_SHIFT: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>ShiftIn / shiftOut mode. |
| [PIN_MODE_STEPPER](-p-i-n_-m-o-d-e_-s-t-e-p-p-e-r.md) | `const val PIN_MODE_STEPPER: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>Pin configured for stepper motor. |
| [SYSEX_ANALOG_MAPPING_QUERY](-s-y-s-e-x_-a-n-a-l-o-g_-m-a-p-p-i-n-g_-q-u-e-r-y.md) | `const val SYSEX_ANALOG_MAPPING_QUERY: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Ask for mapping of analog to pin numbers. |
| [SYSEX_ANALOG_MAPPING_RESPONSE](-s-y-s-e-x_-a-n-a-l-o-g_-m-a-p-p-i-n-g_-r-e-s-p-o-n-s-e.md) | `const val SYSEX_ANALOG_MAPPING_RESPONSE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Reply with mapping info. |
| [SYSEX_CAPABILITY_QUERY](-s-y-s-e-x_-c-a-p-a-b-i-l-i-t-y_-q-u-e-r-y.md) | `const val SYSEX_CAPABILITY_QUERY: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Ask for supported modes and resolution of all pins. |
| [SYSEX_CAPABILITY_RESPONSE](-s-y-s-e-x_-c-a-p-a-b-i-l-i-t-y_-r-e-s-p-o-n-s-e.md) | `const val SYSEX_CAPABILITY_RESPONSE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Reply with supported modes and resolution. |
| [SYSEX_ENCODER_DATA](-s-y-s-e-x_-e-n-c-o-d-e-r_-d-a-t-a.md) | `const val SYSEX_ENCODER_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Reply with encoders current positions. |
| [SYSEX_EXTENDED_ANALOG](-s-y-s-e-x_-e-x-t-e-n-d-e-d_-a-n-a-l-o-g.md) | `const val SYSEX_EXTENDED_ANALOG: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Analog write (PWM, Servo, etc) to any pin. |
| [SYSEX_I2C_CONFIG](-s-y-s-e-x_-i2-c_-c-o-n-f-i-g.md) | `const val SYSEX_I2C_CONFIG: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Config I2C settings such as delay times and power pins. |
| [SYSEX_I2C_REPLY](-s-y-s-e-x_-i2-c_-r-e-p-l-y.md) | `const val SYSEX_I2C_REPLY: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>A reply to an I2C get request. |
| [SYSEX_I2C_REQUEST](-s-y-s-e-x_-i2-c_-r-e-q-u-e-s-t.md) | `const val SYSEX_I2C_REQUEST: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Send an I2C get/write request. |
| [SYSEX_NON_REALTIME](-s-y-s-e-x_-n-o-n_-r-e-a-l-t-i-m-e.md) | `const val SYSEX_NON_REALTIME: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>MIDI Reserved for non-realtime messages |
| [SYSEX_ONEWIRE_DATA](-s-y-s-e-x_-o-n-e-w-i-r-e_-d-a-t-a.md) | `const val SYSEX_ONEWIRE_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Send an OneWire get/write/reset/select/skip/search request. |
| [SYSEX_PIN_STATE_QUERY](-s-y-s-e-x_-p-i-n_-s-t-a-t-e_-q-u-e-r-y.md) | `const val SYSEX_PIN_STATE_QUERY: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Ask for a pin's current mode and digitalValue. |
| [SYSEX_PIN_STATE_RESPONSE](-s-y-s-e-x_-p-i-n_-s-t-a-t-e_-r-e-s-p-o-n-s-e.md) | `const val SYSEX_PIN_STATE_RESPONSE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Reply with pin's current mode and digitalValue. |
| [SYSEX_REALTIME](-s-y-s-e-x_-r-e-a-l-t-i-m-e.md) | `const val SYSEX_REALTIME: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>MIDI Reserved for realtime messages. |
| [SYSEX_REPORT_FIRMWARE](-s-y-s-e-x_-r-e-p-o-r-t_-f-i-r-m-w-a-r-e.md) | `const val SYSEX_REPORT_FIRMWARE: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Report name and version of the firmware. |
| [SYSEX_SAMPLING_INTERVAL](-s-y-s-e-x_-s-a-m-p-l-i-n-g_-i-n-t-e-r-v-a-l.md) | `const val SYSEX_SAMPLING_INTERVAL: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Set the poll rate of the main loop. |
| [SYSEX_SCHEDULER_DATA](-s-y-s-e-x_-s-c-h-e-d-u-l-e-r_-d-a-t-a.md) | `const val SYSEX_SCHEDULER_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Send a createtask/deletetask/addtotask/schedule/querytasks/querytask request to the scheduler. |
| [SYSEX_SERIAL_DATA](-s-y-s-e-x_-s-e-r-i-a-l_-d-a-t-a.md) | `const val SYSEX_SERIAL_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Communicate with serial devices, including other boards. |
| [SYSEX_SERVO_CONFIG](-s-y-s-e-x_-s-e-r-v-o_-c-o-n-f-i-g.md) | `const val SYSEX_SERVO_CONFIG: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Set max angle, minPulse, maxPulse, freq. |
| [SYSEX_SHIFT_DATA](-s-y-s-e-x_-s-h-i-f-t_-d-a-t-a.md) | `const val SYSEX_SHIFT_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>A bitstream to/from a shift register. |
| [SYSEX_STEPPER_DATA](-s-y-s-e-x_-s-t-e-p-p-e-r_-d-a-t-a.md) | `const val SYSEX_STEPPER_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>Control a stepper motor. |
| [SYSEX_STRING_DATA](-s-y-s-e-x_-s-t-r-i-n-g_-d-a-t-a.md) | `const val SYSEX_STRING_DATA: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)<br>A string message with 14-bits per char. |

### Functions

| Name | Summary |
|---|---|
| [createMidiMessageParser](create-midi-message-parser.md) | `fun <T : `[`FirmataMessage`](-firmata-message.md)`> createMidiMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`, length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`, parser: (`[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`) -> `[`T`](create-midi-message-parser.md#T)`): `[`MidiMessageParser`](-midi-message-parser/index.md)`<`[`T`](create-midi-message-parser.md#T)`>` |
| [createSysexMessageParser](create-sysex-message-parser.md) | `fun <T : `[`FirmataMessage`](-firmata-message.md)`> createSysexMessageParser(command: `[`Byte`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte/index.html)`, parser: (`[`ByteArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-byte-array/index.html)`) -> `[`T`](create-sysex-message-parser.md#T)`): `[`SysexMessageParser`](-sysex-message-parser/index.md)`<`[`T`](create-sysex-message-parser.md#T)`>` |
| [getFirmataMessageParser](get-firmata-message-parser.md) | `fun <T : `[`FirmataMessage`](-firmata-message.md)`> getFirmataMessageParser(type: `[`Class`](http://docs.oracle.com/javase/6/docs/api/java/lang/Class.html)`<`[`T`](get-firmata-message-parser.md#T)`>): `[`FirmataMessageParser`](-firmata-message-parser/index.md)`<`[`T`](get-firmata-message-parser.md#T)`>` |
| [registerReceiver](register-receiver.md) | `fun <T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.registerReceiver(receiver: (`[`T`](register-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.registerReceiver(type: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](register-receiver.md#T)`>, receiver: (`[`T`](register-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
| [unregisterReceiver](unregister-receiver.md) | `fun <T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.unregisterReceiver(receiver: (`[`T`](unregister-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)<br>`fun <T : `[`FirmataMessage`](-firmata-message.md)`> `[`Firmata`](-firmata/index.md)`.unregisterReceiver(type: `[`KClass`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin.reflect/-k-class/index.html)`<`[`T`](unregister-receiver.md#T)`>, receiver: (`[`T`](unregister-receiver.md#T)`) -> `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |
