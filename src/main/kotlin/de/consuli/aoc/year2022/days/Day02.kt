package de.consuli.aoc.year2022.days

import de.consuli.aoc.common.Day

class Day02 : Day(2, 2022) {

    private val strategyPointsMap =
        mapOf("A X" to 3, "A Y" to 6, "B Y" to 3, "B Z" to 6, "C X" to 6, "C Z" to 3)

    override fun partOne(testInput: Boolean): Int {
        return getInput(testInput).sumOf { strategy ->
            calculatePointsForRound(strategy)
        }
    }

    override fun partTwo(testInput: Boolean): Int {
        return getInput(testInput).map { "${it[0]} ${getNewStrategyMove(it)}" }.sumOf { strategy ->
            calculatePointsForRound(strategy)
        }
    }

    private fun calculatePointsForRound(strategy: String) =
        strategyPointsMap.getOrDefault(strategy, 0) + " XYZ".indexOf(
            strategy[2]
        )

    private fun getNewStrategyMove(oldStrategy: String): Char {
        val indexOfFirstShape = "ABC".indexOf(oldStrategy[0])
        return when (oldStrategy[2]) {
            'X' -> "ZXY"[indexOfFirstShape]
            'Y' -> "XYZ"[indexOfFirstShape]
            'Z' -> "YZX"[indexOfFirstShape]
            else -> throw IllegalArgumentException()
        }
    }

}
