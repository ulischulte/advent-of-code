package de.consuli.aoc.year2022.days

import de.consuli.aoc.DayTest
import de.consuli.aoc.PuzzleOutput

class Day10Test : DayTest<Day10>(
    Day10(), PuzzleOutput(
        partOneExpectedSampleOutput = 13140,
        partOneExpectedOutput = 16020,
        partTwoExpectedSampleOutput = "\n##..##..##..##..##..##..##..##..##..##..\n" +
                "###...###...###...###...###...###...###.\n" +
                "####....####....####....####....####....\n" +
                "#####.....#####.....#####.....#####.....\n" +
                "######......######......######......###.\n" +
                "#######.......#######.......#######.....",
        partTwoExpectedOutput = "\n####..##..####.#..#.####..##..#....###.#\n" +
                "#....#..#....#.#..#....#.#..#.#....#..#.\n" +
                "###..#......#..#..#...#..#..#.#....#..#.\n" +
                "#....#.....#...#..#..#...####.#....###..\n" +
                "#....#..#.#....#..#.#....#..#.#....#.#.#\n" +
                "####..##..####..##..####.#..#.####.#..#."
    ), Day10()

)

