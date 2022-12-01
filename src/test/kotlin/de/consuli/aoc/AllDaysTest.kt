package de.consuli.aoc

import de.consuli.aoc.days.*
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.TestFactory
import kotlin.test.assertEquals

data class Answer(val instance: () -> Day, val partOne: Any, val partTwo: Any)

class AllDaysTest {

    @TestFactory
    fun answers() = listOf(
        Answer({ Day012022() }, 71471, 211189),
    ).map {
        val day = it.instance()

        DynamicTest.dynamicTest("Day ${day.dayNumber} - ${day.title}") {
            print("Testing Part 1 - Expecting ${it.partOne} ..")
            assertEquals(it.partOne, day.partOne())

            print("Testing Part 2 - Expecting ${it.partTwo} ..")
            assertEquals(it.partTwo, day.partTwo())
        }
    }
}
