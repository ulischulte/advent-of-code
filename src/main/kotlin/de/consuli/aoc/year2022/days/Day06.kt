package de.consuli.aoc.year2022.days

import de.consuli.aoc.common.Day

class Day06 : Day(6, 2022) {
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
