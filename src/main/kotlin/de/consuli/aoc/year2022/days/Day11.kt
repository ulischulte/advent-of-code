package de.consuli.aoc.year2022.days

import de.consuli.aoc.common.Day

class Day11 : Day(11, 2022) {
    override fun partOne(testInput: Boolean): Any {
        return doMonkeyBusiness(testInput, 20, true)
    }

    override fun partTwo(testInput: Boolean): Any {
        return doMonkeyBusiness(testInput, 10000, false)
    }

    private fun doMonkeyBusiness(testInput: Boolean, repeat: Int, divideWorryLevel: Boolean): Long {
        val monkeys = getMonkeysFromInput(testInput)
        repeat(repeat) {
            run {
                round(monkeys, divideWorryLevel)
            }
        }
        val sortedDescending = monkeys.map { it.inspections }.sortedDescending()
        return sortedDescending[0] * sortedDescending[1]
    }

    fun getMonkeysFromInput(testInput: Boolean): List<Monkey> {
        val monkeys = ArrayList<Monkey>()
        getInput(testInput).chunked(7).forEach { monkeyInput ->
            monkeys.add(
                Monkey(
                    monkeyInput[1].split(":")[1].trim().split(",").map { it.trim().toLong() }
                        .map { Item(it) },
                    monkeyInput[2].split("new = ")[1],
                    TestAction(
                        monkeyInput[3].split("divisible by")[1].trim().toLong(),
                        Pair(
                            monkeyInput[4].split("throw to monkey ")[1].trim().toInt(),
                            monkeyInput[5].split("throw to monkey ")[1].trim().toInt()
                        )
                    )
                )
            )
        }
        return monkeys
    }

    private fun round(monkeys: List<Monkey>, divideWorryLevel: Boolean): List<Monkey> {
        monkeys.forEach { monkey ->
            run {
                monkey.currentItems.forEach { item ->
                    monkey.inspections++
                    val operation = monkey.operation.split(" ")
                    val operand = if (operation[2] == "old") item.worryLevel else operation[2].toLong()
                    when (operation[1]) {
                        "+" -> item.worryLevel = item.worryLevel + operand
                        "*" -> item.worryLevel = item.worryLevel * operand
                    }
                    if (divideWorryLevel) {
                        item.worryLevel = item.worryLevel.floorDiv(3)
                    }
                    val receivingMonkey = monkeys[monkey.testAction.getTestResult(item.worryLevel)]
                    receivingMonkey.currentItems = receivingMonkey.currentItems.plus(item)
                    monkey.currentItems = monkey.currentItems.minus(item)
                }
            }

        }
        return monkeys
    }
}

class Monkey(
    var currentItems: List<Item>,
    var operation: String,
    var testAction: TestAction,
    var inspections: Long = 0L
)

class TestAction(var divisibleBy: Long, var actions: Pair<Int, Int>) {

    fun getTestResult(input: Long): Int {
        return if (input % divisibleBy == 0L) {
            actions.first
        } else {
            actions.second
        }
    }
}

class Item(var worryLevel: Long)
