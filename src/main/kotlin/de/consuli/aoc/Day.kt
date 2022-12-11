package de.consuli.aoc

abstract class Day {

    val dayNumber: Int

    private val testInputList: List<String>
    private val inputList: List<String>

    constructor(dayNumber: Int) {
        this.dayNumber = dayNumber
        this.testInputList = InputReader.testInputAsList(dayNumber)
        this.inputList = InputReader.inputAsList(dayNumber)
    }

    abstract fun partOne(testInput: Boolean): Any

    abstract fun partTwo(testInput: Boolean): Any

    fun getInput(testInput: Boolean): List<String> {
        return if (testInput) testInputList else inputList
    }

}
