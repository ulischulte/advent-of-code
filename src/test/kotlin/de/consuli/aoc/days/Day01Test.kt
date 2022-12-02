package de.consuli.aoc.days

import org.junit.jupiter.api.Test

internal class Day01Test {
    private var day01 = Day01()

    @Test
    fun partOne() {
        kotlin.test.assertEquals(24000, day01.partOne(true))
        kotlin.test.assertEquals(71471, day01.partOne(false))
    }

    @Test
    fun partTwo() {
        kotlin.test.assertEquals(45000, day01.partTwo(true))
        kotlin.test.assertEquals(211189, day01.partTwo(false))
    }
}

