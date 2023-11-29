package de.consuli.aoc.year2022.days

import de.consuli.aoc.common.Day

class Day04 : Day(4, 2022) {
    override fun partOne(testInput: Boolean): Any {
        return getInput(testInput).count { sectionAssignment ->
            val sections = getSections(sectionAssignment)
            sections.first.first in sections.second && sections.first.last in sections.second
                    || sections.second.first in sections.first && sections.second.last in sections.first
        }
    }

    override fun partTwo(testInput: Boolean): Any {
        return getInput(testInput).count { sectionAssignment ->
            val sections = getSections(sectionAssignment)
            sections.first.first in sections.second || sections.first.last in sections.second
                    || sections.second.first in sections.first || sections.second.last in sections.first
        }
    }

    private fun getSections(sectionAssignment: String): Pair<IntRange, IntRange> {
        val sectionAssignmentPairs = sectionAssignment.split(",")
        val (startSection1, endSection1) = sectionAssignmentPairs[0].split("-").map { it.toInt() }
        val (startSection2, endSection2) = sectionAssignmentPairs[1].split("-").map { it.toInt() }
        return Pair(startSection1..endSection1, startSection2..endSection2)
    }
}
