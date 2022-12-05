package de.consuli.aoc.days

import de.consuli.aoc.Day

class Day05 : Day(5) {
    override fun partOne(testInput: Boolean): Any {
        return calculateFinalCrateOrder(testInput, false)
    }

    override fun partTwo(testInput: Boolean): Any {
        return calculateFinalCrateOrder(testInput, true)
    }

    private fun calculateFinalCrateOrder(testInput: Boolean, moveMultipleCrates: Boolean): String {
        val stacks = getStacks(testInput).toMutableList()
        getRearrangements(testInput).forEach {
            stacks[it.third - 1] = stacks[it.second - 1].take(it.first)
                .let { crates -> if (!moveMultipleCrates) crates.reversed() else crates } + stacks[it.third - 1]
            stacks[it.second - 1] = stacks[it.second - 1].drop(it.first)
        }
        return stacks.filter { it.isNotEmpty() }.joinToString("") { it[0] }
    }


    private fun getStacks(testInput: Boolean): List<List<String>> {
        val stacks = List(9) { ArrayList<String>() }
        getInput(testInput).subList(
            0,
            getInput(testInput).indexOf("") - 1
        ).reversed().forEach {
            var stackIndex = 0
            for (crateIndex in 1 until it.length step 4) {
                val crate = it[crateIndex]
                stackIndex++
                if (crate !in 'A'..'Z') continue
                stacks[stackIndex - 1].add(0, crate.toString())
            }
        }
        return stacks
    }

    private fun getRearrangements(testInput: Boolean): List<Triple<Int, Int, Int>> {
        return getInput(testInput).subList(
            getInput(testInput).indexOf("") + 1,
            getInput(testInput).size
        ).map {
            val (amount, from, to) = "(\\d+)".toRegex().findAll(it).map { it.value.toInt() }.toList()
            Triple(amount, from, to)
        }
    }

}
