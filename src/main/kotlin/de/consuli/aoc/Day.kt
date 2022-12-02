package de.consuli.aoc

abstract class Day(dayNumber: Int) {

    private val testInputList: List<String> = InputReader.testInputAsList(dayNumber)
    private val inputList: List<String> = InputReader.inputAsList(dayNumber)

    abstract fun partOne(testInput: Boolean): Any

    abstract fun partTwo(testInput: Boolean): Any

    fun getInput(testInput: Boolean): List<String> {
        return if (testInput) testInputList else inputList
    }

}
