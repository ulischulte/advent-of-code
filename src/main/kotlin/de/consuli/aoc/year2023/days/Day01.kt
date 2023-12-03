package de.consuli.aoc.year2023.days

import de.consuli.aoc.common.Day

class Day01 : Day(1, 2023) {

    private val digitStrings = mapOf(
        "one" to "1",
        "two" to "2",
        "three" to "3",
        "four" to "4",
        "five" to "5",
        "six" to "6",
        "seven" to "7",
        "eight" to "8",
        "nine" to "9"
    )

    private val digits = (1..9).map(Int::toString) + digitStrings.keys

    override fun partOne(testInput: Boolean): Int = calculateCalibrationValue(testInput)

    override fun partTwo(testInput: Boolean): Int =
        getInput(testInput).sumOf { getCalibrationValueWithDigitStrings(it) }

    private fun String.parseDigit(): String = digitStrings[this] ?: this

    private fun getCalibrationValueWithDigitStrings(input: String): Int {
        val first = input.findAnyOf(digits)?.second?.parseDigit()
        val second = input.findLastAnyOf(digits)?.second?.parseDigit()
        return "$first$second".toInt()
    }

    private fun calculateCalibrationValue(testInput: Boolean): Int = getInput(testInput)
        .map(::removeNonDigitChars)
        .map(::getFirstAndLastChar)
        .sumOf(String::toInt)

    private fun removeNonDigitChars(input: String) = input.replace("\\D".toRegex(), "")

    private fun getFirstAndLastChar(input: String) = "${input.first()}${input.last()}"
}
