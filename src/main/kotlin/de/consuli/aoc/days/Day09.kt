package de.consuli.aoc.days

import de.consuli.aoc.Day

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

        tailVisits.printTailVisits()
        return tailVisits.count()
    }

    private fun moveHead(head: Point, tail: Point): Point {
        if ((tail != head) && !tail.isDirectNeighbor(head)) {
            val nextPointInRope = head.minus(tail)
            return tail.move(nextPointInRope.x.coerceIn(-1..1), nextPointInRope.y.coerceIn(-1..1))
        }
        return tail
    }

    private fun Set<Point>.printTailVisits() =
        (0 until maxOfOrNull { it.x }!!).forEach { x ->
            run {
                (0 until maxOfOrNull { it.y }!!).forEach { y ->
                    run {
                        if (contains(Point(x, y))) {
                            print("#")
                        } else {
                            print(".")
                        }
                    }
                }
                println()
            }
        }
}

data class Point(val x: Int, val y: Int) {
    fun move(direction: String): Point {
        return when (direction) {
            "R" -> Point(this.x + 1, this.y)
            "U" -> Point(this.x, this.y + 1)
            "D" -> Point(this.x, this.y - 1)
            "L" -> Point(this.x - 1, this.y)
            else -> error("Unknown direction '$direction'.")
        }
    }

    fun move(dx: Int, dy: Int) = Point(x + dx, y + dy)

    operator fun minus(point: Point) = Point(x - point.x, y - point.y)

    fun isDirectNeighbor(other: Point): Boolean {
        val point = this - other
        return point.x in -1..1 && point.y in -1..1
    }

}
