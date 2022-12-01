package de.consuli.aoc.days

import de.consuli.aoc.Day
import de.consuli.aoc.InputReader

class Day012022 : Day(1, 2022, InputReader.inputAsList(1, 2022), "Calorie Counting") {

    override fun partOne(): Int {
        val elves = extractElvesFromInput()

        return elves.maxOf { elf -> elf.calories }
    }

    override fun partTwo(): Int {
        val elves = extractElvesFromInput()

        elves.sortByDescending { it.calories }

        return elves.subList(0, 3).sumOf { e -> e.calories }
    }

    private fun extractElvesFromInput(): MutableList<Elf> {
        var elfCalorieIntake = 0
        val elves = mutableListOf<Elf>()

        for (inputLine in testInput.map { if (it.isEmpty()) 0 else it.toInt() }) {
            if (inputLine == 0) {
                elves.add(Elf(elfCalorieIntake))
                elfCalorieIntake = 0
            } else {
                elfCalorieIntake += inputLine
            }
        }

        if (elfCalorieIntake > 0) {
            elves.add(Elf(elfCalorieIntake))
        }
        return elves
    }

}

internal class Elf(val calories: Int)
