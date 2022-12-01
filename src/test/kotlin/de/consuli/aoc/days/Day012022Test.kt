package de.consuli.aoc.days

import org.junit.jupiter.api.Test

internal class Day012022Test {
    var day01 = Day012022()

    @Test
    fun partOne() {
        kotlin.test.assertEquals(day01.partOne(), 71471)
        //kotlin.test.assertEquals(day01.partOne(), 24000);
    }

    @Test
    fun partTwo() {
        //kotlin.test.assertEquals(day01.partTwo(), 45000);
        kotlin.test.assertEquals(day01.partTwo(), 211189)
    }
}

