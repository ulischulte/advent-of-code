package de.consuli.aoc.days

import org.junit.jupiter.api.Test

class Day02Test {
    private var day02 = Day02()

    @Test
    fun partOne() {
        kotlin.test.assertEquals(15, day02.partOne(true))
        kotlin.test.assertEquals(10941, day02.partOne(false))
    }

    @Test
    fun partTwo() {
        kotlin.test.assertEquals(12, day02.partTwo(true))
        kotlin.test.assertEquals(13071, day02.partTwo(false))
    }
}
