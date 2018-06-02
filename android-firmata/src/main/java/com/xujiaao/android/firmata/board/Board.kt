package com.xujiaao.android.firmata.board

import com.xujiaao.android.firmata.protocol.Firmata
import com.xujiaao.android.firmata.protocol.feature.*

interface Board : PeripheralGroup {

    val io: Firmata

    val firmwareName: String
    val firmwareMajorVersion: Int
    val firmwareMinorVersion: Int

    val protocolMajorVersion: Int
    val protocolMinorVersion: Int

    val pinsCount: Int

    fun getPinSpec(pinAddress: Int): PinSpec
    fun getPinSpec(pinName: String): PinSpec

    fun getPinModes(pin: Int): Set<Int>
    fun getPinResolution(pin: Int, mode: Int): Int

    fun getAnalogPin(analogChannel: Int): Int
    fun getAnalogChannel(pin: Int): Int

    fun setSamplingInterval(samplingInterval: Int)

    interface PinSpec {

        val name: String

        val port: Int
        val address: Int
        val analogChannel: Int

        val isAnalog: Boolean
        val isValid: Boolean

        val pinModes: Set<Int>
    }
}

// -------------------------------------------------------------------------------------------------
// Implementation (BoardWrapper)
// -------------------------------------------------------------------------------------------------

open class BoardWrapper(protected val base: Board) : Board by base {

    private val mOnCloseListener: OnPeripheralCloseListener = ::onClose

    init {
        base.addOnCloseListener(mOnCloseListener)
    }

    override fun close() {
        base.removeOnCloseListener(mOnCloseListener)
        base.close()
    }

    protected open fun onClose() = Unit
}

// -------------------------------------------------------------------------------------------------
// Implementation (DefaultBoard)
// -------------------------------------------------------------------------------------------------

class DefaultBoard(
    override val io: Firmata,
    versionReport: VersionReport,
    firmwareReport: FirmwareReport,
    private val capabilityResponse: CapabilityResponse,
    private val analogMappingResponse: AnalogMappingResponse
) : BasePeripheralGroup(), Board {

    private val mStates = HashMap<String, Any>()

    override val firmwareName: String = firmwareReport.name
    override val firmwareMajorVersion: Int = firmwareReport.majorVersion
    override val firmwareMinorVersion: Int = firmwareReport.minorVersion

    override val protocolMajorVersion: Int = versionReport.majorVersion
    override val protocolMinorVersion: Int = versionReport.minorVersion

    override val pinsCount: Int = capabilityResponse.getPinsCount()

    override fun getPinSpec(pinAddress: Int): Board.PinSpec =
        DefaultPinSpec.from(this, pinAddress)

    override fun getPinSpec(pinName: String): Board.PinSpec =
        DefaultPinSpec.from(this, pinName)

    override fun getPinModes(pin: Int): Set<Int> =
        capabilityResponse.getPinModes(pin).keys

    override fun getPinResolution(pin: Int, mode: Int): Int =
        capabilityResponse.getPinModes(pin)[mode] ?: -1

    override fun getAnalogPin(analogChannel: Int): Int =
        analogMappingResponse.getAnalogPin(analogChannel)

    override fun getAnalogChannel(pin: Int): Int =
        analogMappingResponse.getAnalogChannel(pin)

    override fun setSamplingInterval(samplingInterval: Int) =
        io.sendSamplingIntervalRequest(samplingInterval)

    override fun <T : Any> getSharedState(type: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return mStates.getOrPut(type.name) {
            try {
                val constructor = type.getConstructor()
                constructor.isAccessible = true
                constructor.newInstance()
            } catch (e: NoSuchMethodException) {
                throw IllegalStateException("Class ${type.name} must have an empty constructor")
            }
        } as T
    }

    override fun toString(): String = dumpProfile()
}

class DefaultPinSpec private constructor(
    board: Board,
    override val name: String,
    override val address: Int,
    override val analogChannel: Int,
    override val isAnalog: Boolean
) : Board.PinSpec {

    override val isValid: Boolean = address >= 0 && address < board.pinsCount

    override val port = address ushr 3
    override val pinModes = board.getPinModes(address)

    companion object {

        fun from(board: Board, pinAddress: Int): DefaultPinSpec =
            DefaultPinSpec(board, "$pinAddress", pinAddress, -1, false)

        fun from(board: Board, pinName: String): DefaultPinSpec {
            return when {
                pinName.startsWith('A', true) || pinName.startsWith('I', true) -> {
                    val analogChannel = pinName.substring(1).toInt()
                    val pin = board.getAnalogPin(analogChannel)
                    DefaultPinSpec(board, pinName, pin, analogChannel, true)
                }
                else -> {
                    DefaultPinSpec(board, pinName, pinName.toInt(), -1, false)
                }
            }
        }
    }
}

// -------------------------------------------------------------------------------------------------
// Tools
// -------------------------------------------------------------------------------------------------

fun Board.dumpProfile() =
    StringBuilder()
        .append("Board Profile\n")
        .append("  - Firmware Name: ").append(firmwareName).append("\n")
        .append("  - Firmware Version: ").append(firmwareMajorVersion)
        .append(".").append(firmwareMinorVersion).append("\n")
        .append("  - Protocol Version: ").append(protocolMajorVersion)
        .append(".").append(protocolMinorVersion).append("\n")
        .append("  - Pins:\n")
        .apply {
            for (pin in 0 until pinsCount) {
                append("    * pin #").append((pin + 1).toString().padEnd(2))

                getAnalogChannel(pin).let {
                    if (it != -1) {
                        append(" (analog ").append(it + 1).append(")")
                    }
                }

                getPinModes(pin).let {
                    if (it.isNotEmpty()) {
                        it.joinTo(this, prefix = ": ") {
                            "$it/${getPinResolution(pin, it)}"
                        }
                    }
                }

                append("\n")
            }
        }
        .toString()