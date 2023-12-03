package de.consuli.aoc

import de.consuli.aoc.common.Day
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import java.util.function.Supplier
import kotlin.system.measureTimeMillis
import kotlin.test.assertEquals

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
abstract class DayTest<out T>(
    private val day: Day,
    private val expectedOutput: PuzzleOutput,
    private val implementation: T? = null
) {

    constructor(
        day: Day,
        expectedOutput: PuzzleOutput
    ) : this(day, expectedOutput, null)

    fun get(): T? {
        return this.implementation
    }

    @BeforeAll
    internal fun printTestOutputHeader() {
        println("===== Testing solution for day ${day.dayNumber} in ${day.yearNumber} =====")
    }

    @Test
    fun partOne() {
        this.expectedOutput.partOneExpectedSampleOutput?.let {
            executePart(
                1,
                it,
                true,
                outputSupplier = { day.partOne(true) })
        }
        this.expectedOutput.partOneExpectedOutput?.let {
            executePart(
                1,
                it,
                false,
                outputSupplier = { day.partOne(false) })
        }
    }

    @Test
    fun partTwo() {
        this.expectedOutput.partTwoExpectedOutput?.let {
            executePart(
                2,
                it,
                false,
                outputSupplier = { day.partTwo(false) })
        }
        this.expectedOutput.partTwoExpectedSampleOutput?.let {
            executePart(
                2,
                it,
                true,
                outputSupplier = { day.partTwo(true) })
        }
    }

    private fun executePart(part: Int, expectedOutput: Any, isSample: Boolean, outputSupplier: Supplier<Any>) {
        val computedOutput: Any
        val timeInMillis = measureTimeMillis {
            computedOutput = outputSupplier.get()
        }
        assertEquals(expectedOutput, computedOutput)
        println("Day ${day.dayNumber} part $part ${if (isSample) " sample " else " "}took $timeInMillis ms")
    }

}

data class PuzzleOutput(
    val partOneExpectedSampleOutput: Any? = null,
    val partOneExpectedOutput: Any? = null,
    val partTwoExpectedSampleOutput: Any? = null,
    val partTwoExpectedOutput: Any? = null
)
