package de.consuli.aoc.days

import de.consuli.aoc.common.Day

class Day06 : Day(6) {
    override fun partOne(testInput: Boolean): Any {
        return findFirstMarker(getInput(testInput).first(), 4)
    }

    override fun partTwo(testInput: Boolean): Any {
        return findFirstMarker(getInput(testInput).first(), 14)
    }

    private fun findFirstMarker(input: String, markerSize: Int): Int {
        return input.windowed(markerSize).indexOfFirst { it.toSet().size == markerSize } + markerSize
    }
}
