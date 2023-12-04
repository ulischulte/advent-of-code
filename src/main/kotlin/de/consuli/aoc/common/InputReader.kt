package de.consuli.aoc.common

import java.io.File
import java.net.URL

/**
 * A utility to read puzzle inputs from resources.
 */
object InputReader {

    /**
     * Reads the input resource with the specified day and year and returns its content as a list of lines.
     *
     * @param day the day of the input resource
     * @param year the year of the input resource
     * @param sampleInput whether to read the sample input
     *
     * @throws IllegalArgumentException if the resource cannot be found
     * @return a list of lines from the puzzle input resource
     */
    fun inputAsList(day: Int, year: Int, sampleInput: Boolean = false): List<String> {
        val resource = getResource(day, year, sampleInput)
            ?: throw IllegalArgumentException("Resource for day $day and year $year not found!")

        return File(resource.toURI()).readLines()
    }

    /**
     * Gets the resource with the specified day and year.
     *
     * @param day the day of the resource
     * @param year the year of the resource
     * @param sampleInput whether to get the sample resource
     *
     * @return a [URL] to the resource, or `null` if it cannot be found
     */
    private fun getResource(day: Int, year: Int, sampleInput: Boolean): URL? {
        val resourceName = "Day${"%02d".format(day)}$year${if (sampleInput) "_sample" else ""}.txt"
        return Thread.currentThread().contextClassLoader.getResource(resourceName)
    }

}
