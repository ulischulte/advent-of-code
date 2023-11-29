package de.consuli.aoc.common

abstract class Day {

    val dayNumber: Int
    val yearNumber: Int

    private val testInputList: List<String>
    private val inputList: List<String>

    constructor(dayNumber: Int, yearNumber: Int) {
        this.dayNumber = dayNumber
        this.yearNumber = yearNumber
        this.testInputList = InputReader.testInputAsList(dayNumber, yearNumber)
        this.inputList = InputReader.inputAsList(dayNumber, yearNumber)
    }

    abstract fun partOne(testInput: Boolean): Any

    abstract fun partTwo(testInput: Boolean): Any

    fun getInput(testInput: Boolean): List<String> {
        return if (testInput) testInputList else inputList
    }

}
