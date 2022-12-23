package de.consuli.aoc.days

import de.consuli.aoc.common.Day

class Day10 : Day(10) {
    override fun partOne(testInput: Boolean): Int {
        return intArrayOf(20, 60, 100, 140, 180, 220).sumOf { mapInputToCpuCycles(testInput).getSignalStrength(it) }
    }

    override fun partTwo(testInput: Boolean): String {
        val cpu = mapInputToCpuCycles(testInput)
        var outputString = ""
        (1..240).forEach { currentPixel ->
            val valueAtCurrentCycle = cpu.cycles.elementAt(currentPixel - 1).value
            outputString += if ((currentPixel) % 40 in (valueAtCurrentCycle)..(valueAtCurrentCycle + 2)) {
                "#"
            } else {
                "."
            }
        }
        return outputString.chunked(40).joinToString("\n", "\n")
    }

    private fun mapInputToCpuCycles(testInput: Boolean): CPU {
        val cpu = CPU()
        getInput(testInput).forEach { line ->
            if (line.trim().contains(" ")) {
                val (_, value) = line.split(' ')
                cpu.addX(value.toInt())
            } else if (line.trim() == "noop") {
                cpu.noop()
            }
        }
        return cpu
    }
}

internal class CPU {
    internal var registerValue: Int = 1
    var cycles: Iterable<IndexedValue<Int>> = ArrayList<Int>().withIndex()
    private var currentCycle: Int = 1

    fun addX(value: Int) {
        cycles = cycles.plusElement(IndexedValue(currentCycle++, registerValue))
        cycles = cycles.plusElement(IndexedValue(currentCycle++, registerValue))
        registerValue += value
    }

    fun noop() {
        cycles = cycles.plusElement(IndexedValue(currentCycle++, registerValue))
    }

    fun getSignalStrength(cycleNumber: Int): Int {
        return cycles.elementAt(cycleNumber - 1).value * cycleNumber
    }
}
