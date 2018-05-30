package com.xujiaao.android.firmata.protocol

// -------------------------------------------------------------------------------------------------
// FirmataMessage
// -------------------------------------------------------------------------------------------------

interface FirmataMessage

// -------------------------------------------------------------------------------------------------
// FirmataMessageParser
// -------------------------------------------------------------------------------------------------

sealed class FirmataMessageParser<out T : FirmataMessage> {

    abstract fun parse(data: ByteArray): T
}

abstract class MidiMessageParser<out T : FirmataMessage>(
    val command: Byte,
    val length: Int
) : FirmataMessageParser<T>()

abstract class SysexMessageParser<out T : FirmataMessage>(
    val command: Byte
) : FirmataMessageParser<T>()

inline fun <reified T : FirmataMessage> createMidiMessageParser(
    command: Byte,
    length: Int,
    crossinline parser: (ByteArray) -> T
) = object : MidiMessageParser<T>(command, length) {

    override fun parse(data: ByteArray): T = parser(data)
}

inline fun <reified T : FirmataMessage> createSysexMessageParser(
    command: Byte,
    crossinline parser: (ByteArray) -> T
) = object : SysexMessageParser<T>(command) {

    override fun parse(data: ByteArray): T = parser(data)
}

// -------------------------------------------------------------------------------------------------
// Parsers Loader
// -------------------------------------------------------------------------------------------------

private val PARSERS = HashMap<String, FirmataMessageParser<*>>()

@Throws(IllegalArgumentException::class)
fun <T : FirmataMessage> getFirmataMessageParser(type: Class<T>): FirmataMessageParser<T> =
    synchronized(PARSERS) {
        @Suppress("UNCHECKED_CAST")
        return PARSERS.getOrPut(type.name) {
            lookupFirmataMessageParser(type)
        } as FirmataMessageParser<T>
    }

@Throws(IllegalArgumentException::class)
private fun lookupFirmataMessageParser(type: Class<*>): FirmataMessageParser<*> {
    val field = try {
        type.getField("PARSER")
    } catch (e: NoSuchFieldException) {
        throw IllegalArgumentException(
            "FirmataMessage requires a FirmataMessageParser object " +
                    "called PARSER on class ${type.name}"
        )
    }

    if (field.modifiers and java.lang.reflect.Modifier.STATIC == 0) {
        throw IllegalArgumentException(
            "FirmataMessage requires the PARSER object to be static on class ${type.name}"
        )
    }

    if (!FirmataMessageParser::class.java.isAssignableFrom(field.type)) {
        throw IllegalArgumentException(
            "FirmataMessage requires a FirmataMessageParser object " +
                    "called PARSER on class ${type.name}"
        )
    }

    return try {
        field.get(null) as FirmataMessageParser<*>?
                ?: throw IllegalArgumentException(
                    "FirmataMessage requires a non-null FirmataMessageParser object " +
                            "called PARSER on class ${type.name}"
                )
    } catch (e: IllegalAccessException) {
        throw  IllegalArgumentException(
            "IllegalAccessException when looking up creator: ${type.name}"
        )
    }
}