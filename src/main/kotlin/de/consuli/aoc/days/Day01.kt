package de.consuli.aoc.days

import de.consuli.aoc.common.Day

class Day01 : Day(1) {

    override fun partOne(testInput: Boolean): Int {
        return extractElvesCalorieIntakeFromInput(testInput).maxOf { it }
    }

    override fun partTwo(testInput: Boolean): Int {
        return extractElvesCalorieIntakeFromInput(testInput).map { it }.sorted().takeLast(3).sum()
    }

    private fun extractElvesCalorieIntakeFromInput(testInput: Boolean): List<Int> {
        val input = getInput(testInput)
        val groups = input.scan(0) { acc, line -> acc + if (line.isBlank()) 1 else 0 }
        return input.asSequence().map { it.toIntOrNull() }
            .withIndex()
            .filter { it.value != null }
            .groupBy { groups[it.index] }
            .map { elfCalorieIntake -> elfCalorieIntake.value.sumOf { it.value!! } }
    }

}
