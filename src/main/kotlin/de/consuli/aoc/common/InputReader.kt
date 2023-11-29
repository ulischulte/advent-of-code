package de.consuli.aoc.common

import java.io.File

object InputReader {

    fun inputAsList(day: Int, year: Int): List<String> {
        return fromResources(day, year, false).readLines()
    }

    fun testInputAsList(day: Int, year: Int): List<String> {
        return fromResources(day, year, true).readLines()
    }

    private fun fromResources(day: Int, year: Int, sampleInput: Boolean): File =
        Thread.currentThread().contextClassLoader.getResource(
            "Day${day.toString().padStart(2, '0')}$year${if (sampleInput) "_sample" else ""}.txt"
        ).let { File(it.toURI()) }

}
