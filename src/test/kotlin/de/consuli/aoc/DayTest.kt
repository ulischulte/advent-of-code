package de.consuli.aoc

import de.consuli.aoc.common.Day
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class DayTest<T>(
    private val day: Day,
    private val expectedOutput: PuzzleOutput,
    private val implementation: T? = null
) {

    fun getImplementation(): T? = implementation

    @BeforeAll
    internal fun displayTestOutputHeader() {
        println("===== Testing solution for day ${day.dayNumber} in ${day.yearNumber} =====")
    }

    @Test
    fun testPartOne() {
        executePart(1, expectedOutput.partOneExpectedSampleOutput, true, day::partOne)
        executePart(1, expectedOutput.partOneExpectedOutput, false, day::partOne)
    }

    @Test
    fun testPartTwo() {
        executePart(2, expectedOutput.partTwoExpectedSampleOutput, true, day::partTwo)
        executePart(2, expectedOutput.partTwoExpectedOutput, false, day::partTwo)
    }

    private fun executePart(part: Int, expectedOutput: Any?, isSample: Boolean, function: (Boolean) -> Any) {
        val (output, duration) = measureFunction(isSample, function)
        println("Day ${day.dayNumber} part $part ${if (isSample) "sample" else ""} took $duration ms")
        assertEquals(expectedOutput, output)
    }

    private fun measureFunction(isSample: Boolean, function: (Boolean) -> Any): Pair<Any, Long> {
        lateinit var computedOutput: Any
        val duration = measureTimeMillis {
            computedOutput = function(isSample)
        }
        return computedOutput to duration
    }
}

data class PuzzleOutput(
    val partOneExpectedSampleOutput: Any? = null,
    val partOneExpectedOutput: Any? = null,
    val partTwoExpectedSampleOutput: Any? = null,
    val partTwoExpectedOutput: Any? = null
)
