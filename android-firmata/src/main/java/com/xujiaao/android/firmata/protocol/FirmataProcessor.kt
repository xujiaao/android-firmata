package com.xujiaao.android.firmata.protocol

import android.util.SparseArray
import java.io.ByteArrayOutputStream
import kotlin.experimental.and

interface FirmataProcessor {

    @Throws(IllegalStateException::class)
    fun attachParser(parser: FirmataMessageParser<*>)

    @Throws(IllegalStateException::class)
    fun detachParser(parser: FirmataMessageParser<*>)

    fun process(curr: Int, callback: (FirmataMessage) -> Unit)
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultFirmata)
// -------------------------------------------------------------------------------------------------

private const val STATE_IDLE = 0
private const val STATE_PROCESSING_MIDI = 1
private const val STATE_PROCESSING_SYSEX = 2

class DefaultFirmataProcessor : FirmataProcessor {

    private val mMidiMessageParsers = SparseArray<MidiMessageParser<*>>()
    private val mSysexMessageParsers = SparseArray<SysexMessageParser<*>>()

    private val mBuffer = ByteArrayOutputStream(32)

    private var mState: Int = 0
    private var mCurrentMidiMessageParser: MidiMessageParser<*>? = null

    override fun attachParser(parser: FirmataMessageParser<*>) =
        when (parser) {
            is MidiMessageParser ->
                attachParser(parser.command, parser, mMidiMessageParsers)

            is SysexMessageParser ->
                attachParser(parser.command, parser, mSysexMessageParsers)
        }

    override fun detachParser(parser: FirmataMessageParser<*>) =
        when (parser) {
            is MidiMessageParser ->
                detachParser(parser.command, parser, mMidiMessageParsers)

            is SysexMessageParser ->
                detachParser(parser.command, parser, mSysexMessageParsers)
        }

    private fun <T : FirmataMessageParser<*>> attachParser(
        id: Byte,
        parser: T,
        parsers: SparseArray<T>
    ) = synchronized(parsers) {
        val byte = id.toInt()
        val last = parsers.get(byte)
        if (last != null && last != parser) {
            throw IllegalStateException("Conflicting parser '$id': '$last' vs. '$parser'")
        }

        parsers.put(byte, parser)
    }

    private fun <T : FirmataMessageParser<*>> detachParser(
        id: Byte,
        parser: T,
        parsers: SparseArray<T>
    ) = synchronized(parsers) {
        val byte = id.toInt()
        if (parsers.get(byte) == parser) {
            parsers.remove(byte)
        }
    }

    private fun <T : FirmataMessageParser<*>> getParser(
        id: Byte,
        parsers: SparseArray<T>
    ): T? =
        synchronized(parsers) {
            parsers.get(id.toInt())
        }

    override fun process(curr: Int, callback: (FirmataMessage) -> Unit) {
        val byte = curr.toByte()
        if (curr > 0x7F) {
            if (byte == MIDI_END_SYSEX) {
                if (mState == STATE_PROCESSING_SYSEX && mBuffer.size() >= 2) {
                    // sysex command completed.
                    mBuffer.write(curr)

                    processSysexResponse(mBuffer.toByteArray(), callback)
                }

                updateState(STATE_IDLE)
            } else if (byte == MIDI_START_SYSEX) {
                updateState(STATE_PROCESSING_SYSEX)

                mBuffer.write(curr)
            } else {
                var command = byte
                if (byte < MIDI_START_SYSEX) {
                    command = byte and MIDI_START_SYSEX
                }

                val parser = getParser(command, mMidiMessageParsers)
                if (parser != null) {
                    // registered midi command started.
                    updateState(STATE_PROCESSING_MIDI)

                    mBuffer.write(curr)
                    mCurrentMidiMessageParser = parser
                } else {
                    // unregistered midi command, skip it.
                    updateState(STATE_IDLE)
                }
            }
        } else {
            // data received.
            mBuffer.write(curr)
        }

        if (mState == STATE_PROCESSING_MIDI) {
            val parser = mCurrentMidiMessageParser!!
            if (parser.length == mBuffer.size()) {
                // registered midi command completed.
                processMidiResponse(parser, mBuffer.toByteArray(), callback)

                updateState(STATE_IDLE)
            }
        }
    }

    private fun processMidiResponse(
        parser: MidiMessageParser<*>,
        data: ByteArray,
        callback: (FirmataMessage) -> Unit
    ) {
        try {
            callback(parser.parse(data))
        } catch (e: Throwable) {
            e.printStackTrace()
        }
    }

    private fun processSysexResponse(
        data: ByteArray,
        callback: (FirmataMessage) -> Unit
    ) {
        val parser = getParser(data[1], mSysexMessageParsers)
        if (parser != null) {
            try {
                callback(parser.parse(data))
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }

    private fun updateState(state: Int) {
        mState = state
        mCurrentMidiMessageParser = null

        mBuffer.reset()
    }
}