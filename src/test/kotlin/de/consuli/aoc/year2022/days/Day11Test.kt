package de.consuli.aoc.year2022.days

import de.consuli.aoc.DayTest
import de.consuli.aoc.PuzzleOutput
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test

class Day11Test : DayTest<Day11>(
    Day11(), PuzzleOutput(
        partOneExpectedSampleOutput = 10605L,
        partOneExpectedOutput = 100345L
    ), Day11()
) {
    @Test
    fun getMonkeysFromInput() {
        val monkeysFromInput = getImplementation()!!.getMonkeysFromInput(true)
        assertSoftly(monkeysFromInput) {
            shouldHaveSize(4)
            assertSoftly(monkeysFromInput[1]) {
                currentItems shouldHaveSize 4
                operation shouldBe "old + 6"
                testAction.actions shouldBe Pair(2, 0)
                inspections shouldBe 0
            }
        }
    }
}


