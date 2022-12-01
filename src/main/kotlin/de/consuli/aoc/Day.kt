package de.consuli.aoc

abstract class Day(val dayNumber: Int, val year: Int, val testInput: List<String>, val title: String) {

    abstract fun partOne(): Any

    abstract fun partTwo(): Any
}
