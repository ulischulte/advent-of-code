package de.consuli.aoc.year2023.days

import de.consuli.aoc.common.Day
import kotlin.math.pow

class Day04 : Day(4, 2023) {

    data class ScratchCard(val winningNumbers: Set<Int>, val numbers: Set<Int>)

    override fun partOne(testInput: Boolean): Int = mapInputToScratchCards(getInput(testInput))
        .sumOf { calculatePointsvorCard(it) }

    override fun partTwo(testInput: Boolean): Int {
        val scratchCards = mapInputToScratchCards(getInput(testInput))
        val cardCopies = IntArray(scratchCards.size) { initialCardCopies -> 1 }

        scratchCards.forEachIndexed { currentIndex, currentCard ->
            val winningNumberCounts = currentCard.numbers.count { number -> number in currentCard.winningNumbers }
            val cardCyclingIndexRange = (currentIndex + 1..currentIndex + winningNumberCounts)

            cardCyclingIndexRange.forEachIndexed { _, cyclingIndex ->
                if (cyclingIndex < cardCopies.size) cardCopies[cyclingIndex] += cardCopies[currentIndex]
            }
        }
        return cardCopies.sum()
    }

    private fun calculatePointsvorCard(card: ScratchCard): Int = card.numbers
        .count { it in card.winningNumbers }
        .takeIf { it > 0 }?.let { 2.0.pow(it - 1).toInt() } ?: 0

    private fun mapInputToScratchCards(input: List<String>): List<ScratchCard> =
        input.map { inputLine ->
            ScratchCard(
                getNumbersFromLine(inputLine, isWinning = true),
                getNumbersFromLine(inputLine, isWinning = false)
            )
        }

    private fun getNumbersFromLine(line: String, isWinning: Boolean): Set<Int> =
        line.substringAfter(':')
            .split('|')
            .run { if (isWinning) this[0] else this[1] }
            .trim()
            .split("\\s+".toRegex())
            .map { it.toInt() }
            .toSet()
}
