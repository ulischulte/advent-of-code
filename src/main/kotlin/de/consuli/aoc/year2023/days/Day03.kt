package de.consuli.aoc.year2023.days

import de.consuli.aoc.common.Day

class Day03 : Day(3, 2023) {

    override fun partOne(testInput: Boolean): Int = calculateSumOfParts(toCharArrayList(getInput(testInput)))

    override fun partTwo(testInput: Boolean): Int = calculateGearRatio(getInput(testInput))

    private fun calculateSumOfParts(input: List<CharArray>): Int {
        return sumValidPartNumbers(input, { !it.isDigit() && it != '.' }) { row: Int, col: Int ->
            getAdjacentNonZeroNumbers(input, row, col).sum()
        }
    }

    private fun calculateGearRatio(input: List<String>): Int {
        data class YX(val y: Int, val x: Int)

        val starList = mutableListOf<YX>()
        val charArrayList = toCharArrayList(input)
        charArrayList.forEachIndexed { y, chars ->
            chars.forEachIndexed { x, char ->
                if (char == '*') starList.add(YX(y, x))
            }
        }
        val sumOfGearRatios = starList.sumOf { star ->
            val numbersNextToStars = getAdjacentNonZeroNumbers(charArrayList, star.y, star.x)
            if (numbersNextToStars.size == 2) numbersNextToStars[0] * numbersNextToStars[1]
            else 0
        }
        return sumOfGearRatios
    }

    private fun sumValidPartNumbers(
        matrix: List<CharArray>,
        predicate: (char: Char) -> Boolean,
        selector: (row: Int, col: Int) -> Int,
    ): Int {
        return matrix.indices.flatMap { row ->
            matrix[row].indices.map { col -> Pair(row, col) }
        }
            .filter { (row, col) -> predicate(matrix[row][col]) }.sumOf { (row, col) -> selector(row, col) }
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

    private fun extractNumberAt(matrix: CharArray, index: Int): Int {
        if (!matrix[index].isDigit()) return 0

        val numberStart = (index downTo 0).find { !matrix[it].isDigit() }?.plus(1) ?: 0
        val numberEnd = (index..matrix.lastIndex).find { !matrix[it].isDigit() }?.minus(1) ?: matrix.lastIndex

        val number = matrix.slice(numberStart..numberEnd).joinToString(separator = "").toInt()

        for (i in numberStart..numberEnd) matrix[i] = '.'

        return number
    }

    private fun toCharArrayList(inputList: List<String>): List<CharArray> {
        return inputList.map { it.toCharArray() }
    }
}
