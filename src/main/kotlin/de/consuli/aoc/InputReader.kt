package de.consuli.aoc

import java.io.File

object InputReader {

    fun inputAsList(day: Int, year: Int): List<String> {
        return fromResources(day, year, false).readLines()
    }

    fun testInputAsList(day: Int, year: Int): List<String> {
        return fromResources(day, year, true).readLines()
    }

    private fun fromResources(day: Int, year: Int, sampleInput: Boolean): File {
        return javaClass.classLoader.getResource(
            "Day${day.toString().padStart(2, '0')}_$year${if (sampleInput) "_sample" else ""}.txt"
        ).let { it -> File(it.toURI()) }
    }
    
}
