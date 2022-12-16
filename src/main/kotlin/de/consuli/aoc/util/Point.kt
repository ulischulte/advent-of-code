package de.consuli.aoc.util

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

    companion object {
        fun Set<Point>.printMatrixContainingPoints() =
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

}
