package de.consuli.aoc.days

import de.consuli.aoc.common.Day

class Day03 : Day(3) {
    override fun partOne(testInput: Boolean): Any {
        return getInput(testInput).sumOf {
            getPriorityForItemInBothCompartments(it)
        }
    }

    override fun partTwo(testInput: Boolean): Any {
        return getInput(testInput).windowed(3, 3).sumOf { rucksacks ->
            val commonItem = rucksacks[0].find { it in rucksacks[1] && it in rucksacks[2] } ?: ' '
            getPriority(commonItem)
        }
    }

    private fun getPriorityForItemInBothCompartments(line: String): Int {
        val itemsInCompartment = line.length / 2
        val itemsFirstCompartment = line.substring(0, itemsInCompartment)
        val itemsSecondCompartment = line.substring(itemsInCompartment, line.length)
        return getPriority(itemsFirstCompartment.find { it in itemsSecondCompartment } ?: ' ')
    }

    private fun getPriority(item: Char): Int {
        return if (item in 'a'..'z') item - 'a' + 1 else item - 'A' + 27
    }

}
