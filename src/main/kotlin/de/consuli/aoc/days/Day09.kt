package de.consuli.aoc.days

import de.consuli.aoc.common.Day
import de.consuli.aoc.util.Point
import de.consuli.aoc.util.Point.Companion.printMatrixContainingPoints

class Day09 : Day(9) {

    private val movesTestInput: List<String>
    private val moves: List<String>

    init {
        moves = readMovesFromInput(false)
        movesTestInput = readMovesFromInput(true)
    }

    override fun partOne(testInput: Boolean): Any {
        return countFieldsVisitedByTail(getMoves(testInput), 2)
    }

    override fun partTwo(testInput: Boolean): Any {
        return countFieldsVisitedByTail(getMoves(testInput), 10)
    }

    private fun getMoves(testInput: Boolean): List<String> {
        return when (testInput) {
            true -> movesTestInput
            false -> moves
        }
    }

    private fun readMovesFromInput(testInput: Boolean) = getInput(testInput).flatMap { line ->
        val (direction, steps) = line.split(' ')
        List(steps.toInt()) { direction }
    }

    private fun countFieldsVisitedByTail(moves: List<String>, ropeLength: Int): Int {
        val ropeKnots = MutableList(ropeLength) { Point(0, 0) }
        val tailVisits = hashSetOf(ropeKnots.last())
        moves.forEach { direction ->
            ropeKnots[0] = ropeKnots[0].move(direction)
            (1 until ropeLength).forEach { knotIndex ->
                ropeKnots[knotIndex] = moveHead(ropeKnots[knotIndex - 1], ropeKnots[knotIndex])
            }
            tailVisits += ropeKnots.last()
        }

        tailVisits.printMatrixContainingPoints()
        return tailVisits.count()
    }

    private fun moveHead(head: Point, tail: Point): Point {
        if ((tail != head) && !tail.isDirectNeighbor(head)) {
            val nextPointInRope = head.minus(tail)
            return tail.move(nextPointInRope.x.coerceIn(-1..1), nextPointInRope.y.coerceIn(-1..1))
        }
        return tail
    }

}
