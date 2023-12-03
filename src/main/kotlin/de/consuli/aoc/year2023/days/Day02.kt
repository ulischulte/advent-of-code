package de.consuli.aoc.year2023.days

import de.consuli.aoc.common.Day

data class Cube(val color: String, val count: Int)

class Day02 : Day(2, 2023) {

    private val limits = mapOf("blue" to 14, "green" to 13, "red" to 12)

    override fun partOne(testInput: Boolean): Int =
        getInput(testInput)
            .filter(::isValidGameLine)
            .sumOf { it.substringAfter("Game ").substringBefore(":").toInt() }

    override fun partTwo(testInput: Boolean): Long {
        return getInput(testInput)
            .sumOf { gameLine ->
                val minimumRequiredCubes = mutableMapOf("blue" to 0, "green" to 0, "red" to 0)
                val cubes = extractCubes(gameLine)

                cubes.forEach { cube ->
                    if (minimumRequiredCubes[cube.color]!! < cube.count)
                        minimumRequiredCubes[cube.color] = cube.count
                }

                minimumRequiredCubes.values.product()
            }
    }

    private fun isValidGameLine(gameLine: String): Boolean {
        val cubes = extractCubes(gameLine)
        return cubes.all(::isValidCube)
    }

    private fun extractCubes(gameLine: String): List<Cube> {
        return gameLine.substringAfter(": ").split("; ").flatMap {
            it.split(", ").map { cube ->
                val color = cube.substringAfter(" ")
                val count = cube.substringBefore(" ").toInt()
                Cube(color, count)
            }
        }
    }

    private fun isValidCube(cube: Cube): Boolean {
        return cube.count <= (limits[cube.color] ?: 0)
    }

    private fun Collection<Int>.product(): Long = fold(1L) { acc, entry -> entry * acc }
}
