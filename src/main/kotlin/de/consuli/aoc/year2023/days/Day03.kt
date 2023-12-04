package de.consuli.aoc.year2023.days

import de.consuli.aoc.common.Day

class Day03 : Day(3, 2023) {

    data class Point(val y: Int, val x: Int)

    override fun partOne(testInput: Boolean): Int =
        calculateSumOfMetrics(toCharArrayList(getInput(testInput)), this::partOnePredicate, this::partOneSelector)

    override fun partTwo(testInput: Boolean): Int =
        calculateSumOfMetrics(toCharArrayList(getInput(testInput)), this::partTwoPredicate, this::partTwoSelector)

    private fun calculateSumOfMetrics(
        input: List<CharArray>,
        predicate: (Char) -> Boolean,
        selector: (Point, List<CharArray>) -> Int
    ): Int {
        return input.indices
            .flatMap { row -> input[row].indices.map { col -> Point(row, col) } }
            .filter { (row, col) -> predicate(input[row][col]) }.sumOf { selector(it, input) }
    }

    private fun partOnePredicate(c: Char) = !c.isDigit() && c != '.'
    private fun partOneSelector(point: Point, matrix: List<CharArray>) =
        getAdjacentNonZeroNumbers(matrix, point.y, point.x).sum()

    private fun partTwoPredicate(c: Char) = c == '*'
    private fun partTwoSelector(point: Point, matrix: List<CharArray>): Int {
        val numbersNextToStars = getAdjacentNonZeroNumbers(matrix, point.y, point.x)
        return if (numbersNextToStars.size == 2) numbersNextToStars[0] * numbersNextToStars[1]
        else 0
    }

    private fun getAdjacentNonZeroNumbers(matrix: List<CharArray>, row: Int, col: Int): List<Int> {
        return getAdjacentCoordinates(row, col)
            .filter { (row, column) -> row in matrix.indices && column in matrix.first().indices }
            .map { (row, column) -> extractNumberAt(matrix[row], column) }
            .filter { it != 0 }
            .toList()
    }

    private fun getAdjacentCoordinates(row: Int, column: Int): Sequence<Pair<Int, Int>> =
        sequenceOf(
            -1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1
        ).map { Pair(row + it.first, column + it.second) }

    private fun extractNumberAt(row: CharArray, index: Int): Int {
        if (!row[index].isDigit()) return 0

        val numberStart = (index downTo 0).find { !row[it].isDigit() }?.plus(1) ?: 0
        val numberEnd = (index..row.lastIndex).find { !row[it].isDigit() }?.minus(1) ?: row.lastIndex

        val number = row.slice(numberStart..numberEnd).joinToString(separator = "").toInt()

        // mask processed number chars with '.'
        for (i in numberStart..numberEnd) row[i] = '.'

        return number
    }

    private fun toCharArrayList(inputList: List<String>): List<CharArray> {
        return inputList.map { it.toCharArray() }
    }
}
