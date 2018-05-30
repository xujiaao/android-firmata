@file:JvmName("FirmataConstants")

package com.xujiaao.android.firmata.protocol

// -------------------------------------------------------------------------------------------------
// Midi Commands (128-255 / 0x80-0xFF)
// -------------------------------------------------------------------------------------------------

/**
 * Send data for a digital port (collection of 8 pins).
 */
const val MIDI_DIGITAL_MESSAGE = 0x90.toByte()

/**
 * Send data for an analog pin (or PWM).
 */
const val MIDI_ANALOG_MESSAGE = 0xE0.toByte()

/**
 * Enable analog input by pin #.
 */
const val MIDI_REPORT_ANALOG = 0xC0.toByte()

/**
 * Enable digital input by port pair.
 */
const val MIDI_REPORT_DIGITAL = 0xD0.toByte()

/**
 * Set a pin to INPUT/OUTPUT/PWM/etc.
 */
const val MIDI_SET_PIN_MODE = 0xF4.toByte()

/**
 * Set digitalValue of an individual digital pin.
 */
const val MIDI_SET_DIGITAL_PIN_VALUE = 0xF5.toByte()

/**
 * Report protocol version.
 */
const val MIDI_REPORT_VERSION = 0xF9.toByte()

/**
 * Reset from MIDI.
 */
const val MIDI_SYSTEM_RESET = 0xFF.toByte()

/**
 * Start a MIDI Sysex message.
 */
const val MIDI_START_SYSEX = 0xF0.toByte()

/**
 * End a MIDI Sysex message.
 */
const val MIDI_END_SYSEX = 0xF7.toByte()

// -------------------------------------------------------------------------------------------------
// Sysex Commands (0-127 / 0x00-0x7F), (0x00-0x0F reserved for user-defined commands)
// -------------------------------------------------------------------------------------------------

/**
 * Communicate with serial devices, including other boards.
 */
const val SYSEX_SERIAL_DATA = 0x60.toByte()

/**
 * Reply with encoders current positions.
 */
const val SYSEX_ENCODER_DATA = 0x61.toByte()

/**
 * Set max angle, minPulse, maxPulse, freq.
 */
const val SYSEX_SERVO_CONFIG = 0x70.toByte()

/**
 * A string message with 14-bits per char.
 */
const val SYSEX_STRING_DATA = 0x71.toByte()

/**
 * Control a stepper motor.
 */
const val SYSEX_STEPPER_DATA = 0x72.toByte()

/**
 * Send an OneWire get/write/reset/select/skip/search request.
 */
const val SYSEX_ONEWIRE_DATA = 0x73.toByte()

/**
 * A bitstream to/from a shift register.
 */
const val SYSEX_SHIFT_DATA = 0x75.toByte()

/**
 * Send an I2C get/write request.
 */
const val SYSEX_I2C_REQUEST = 0x76.toByte()

/**
 * A reply to an I2C get request.
 */
const val SYSEX_I2C_REPLY = 0x77.toByte()

/**
 * Config I2C settings such as delay times and power pins.
 */
const val SYSEX_I2C_CONFIG = 0x78.toByte()

/**
 * Report name and version of the firmware.
 */
const val SYSEX_REPORT_FIRMWARE = 0x79.toByte()

/**
 * Analog write (PWM, Servo, etc) to any pin.
 */
const val SYSEX_EXTENDED_ANALOG = 0x6F.toByte()

/**
 * Ask for a pin's current mode and digitalValue.
 */
const val SYSEX_PIN_STATE_QUERY = 0x6D.toByte()

/**
 * Reply with pin's current mode and digitalValue.
 */
const val SYSEX_PIN_STATE_RESPONSE = 0x6E.toByte()

/**
 * Ask for supported modes and resolution of all pins.
 */
const val SYSEX_CAPABILITY_QUERY = 0x6B.toByte()

/**
 * Reply with supported modes and resolution.
 */
const val SYSEX_CAPABILITY_RESPONSE = 0x6C.toByte()

/**
 * Ask for mapping of analog to pin numbers.
 */
const val SYSEX_ANALOG_MAPPING_QUERY = 0x69.toByte()

/**
 * Reply with mapping info.
 */
const val SYSEX_ANALOG_MAPPING_RESPONSE = 0x6A.toByte()

/**
 * Set the poll rate of the main loop.
 */
const val SYSEX_SAMPLING_INTERVAL = 0x7A.toByte()

/**
 * Send a createtask/deletetask/addtotask/schedule/querytasks/querytask request to the scheduler.
 */
const val SYSEX_SCHEDULER_DATA = 0x7B.toByte()

/**
 * MIDI Reserved for non-realtime messages
 */
@Suppress("unused")
const val SYSEX_NON_REALTIME = 0x7E.toByte()

/**
 * MIDI Reserved for realtime messages.
 */
@Suppress("unused")
const val SYSEX_REALTIME = 0x7F.toByte()

// -------------------------------------------------------------------------------------------------
// Pin Modes
// -------------------------------------------------------------------------------------------------

/**
 * Input pin mode.
 */
const val PIN_MODE_INPUT = 0x00

/**
 * Output pin mode.
 */
const val PIN_MODE_OUTPUT = 0x01

/**
 * Analog pin in analogInput mode.
 */
const val PIN_MODE_ANALOG = 0x02

/**
 * Digital pin in PWM output mode.
 */
const val PIN_MODE_PWM = 0x03

/**
 * Digital pin in Servo output mode.
 */
const val PIN_MODE_SERVO = 0x04

/**
 * ShiftIn / shiftOut mode.
 */
const val PIN_MODE_SHIFT = 0x05

/**
 * Pin included in I2C setup.
 */
const val PIN_MODE_I2C = 0x06

/**
 * Pin configured for 1-wire.
 */
const val PIN_MODE_ONEWIRE = 0x07

/**
 * Pin configured for stepper motor.
 */
const val PIN_MODE_STEPPER = 0x08

/**
 * Pin configured for rotary encoders.
 */
const val PIN_MODE_ENCODER = 0x09

/**
 * Pin configured for serial communication.
 */
const val PIN_MODE_SERIAL = 0x0A

/**
 * Enable internal pull-up resistor for pin.
 */
const val PIN_MODE_PULLUP = 0x0B

/**
 * Pin configured to be ignored by digitalWrite and capabilityResponse
 */
const val PIN_MODE_IGNORE = 0x7F