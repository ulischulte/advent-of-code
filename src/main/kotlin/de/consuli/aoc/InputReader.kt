package de.consuli.aoc

import java.io.File

object InputReader {

    fun inputAsList(day: Int): List<String> {
        return fromResources(day, false).readLines()
    }

    fun testInputAsList(day: Int): List<String> {
        return fromResources(day, true).readLines()
    }

    private fun fromResources(day: Int, sampleInput: Boolean): File =
        Thread.currentThread().contextClassLoader.getResource(
            "Day${day.toString().padStart(2, '0')}${if (sampleInput) "_sample" else ""}.txt"
        ).let { File(it.toURI()) }

}
