package de.consuli.aoc.year2022.days

import de.consuli.aoc.common.Day

class Day08 : Day(8, 2022) {

    private val treesTestInput: List<Tree>
    private val trees: List<Tree>

    init {
        trees = readTrees(false)
        treesTestInput = readTrees(true)
    }

    override fun partOne(testInput: Boolean): Any {
        return getTrees(testInput).count { it.isVisible() }
    }

    override fun partTwo(testInput: Boolean): Any {
        return getTrees(testInput).maxOf { it.scenicScore }
    }

    private fun readTrees(testInput: Boolean): List<Tree> {
        val treesInput = getInput(testInput).withIndex()
        return treesInput.map { indexedTreeRow ->
            val treeRowIndexed = indexedTreeRow.value.toCharArray().withIndex()
            treeRowIndexed.map { treeRowIndex ->
                Tree(
                    treeRowIndex.value.code,
                    getMaxTreeSizeForRowPart(treeRowIndexed.filter { it.index < treeRowIndex.index }
                        .map { it.value }),
                    getMaxTreeSizeForRowPart(treeRowIndexed.filter { it.index > treeRowIndex.index }
                        .map { it.value }),
                    getMaxTreeSizeForColumn(
                        treesInput.filter { it.index > indexedTreeRow.index },
                        treeRowIndex.index
                    ),
                    getMaxTreeSizeForColumn(
                        treesInput.filter { it.index < indexedTreeRow.index },
                        treeRowIndex.index
                    ),
                    calculateScenicScore(
                        indexedTreeRow.index,
                        treeRowIndex.index,
                        treeRowIndex.value.code,
                        testInput
                    )
                )
            }
        }.flatten()
    }

    private fun getColumn(testInput: Boolean, column: Int): String {
        return getInput(testInput).joinToString("") { it.toCharArray()[column].toString() }
    }

    private fun calculateScenicScore(rowIndex: Int, columnIndex: Int, treeSize: Int, testInput: Boolean): Int {
        val column = getColumn(testInput, columnIndex)
        val row = getInput(testInput)[rowIndex]

        val scoreUp = calculateScoreForDirection(column.substring(0, rowIndex).reversed().toCharArray(), treeSize)
        val scoreLeft = calculateScoreForDirection(row.substring(0, columnIndex).reversed().toCharArray(), treeSize)
        val scoreDown = calculateScoreForDirection(column.substring(rowIndex + 1).toCharArray(), treeSize)
        val scoreRight = calculateScoreForDirection(row.substring(columnIndex + 1).toCharArray(), treeSize)

        return scoreUp * scoreDown * scoreLeft * scoreRight
    }

    private fun calculateScoreForDirection(remainingDirectionChars: CharArray, size: Int): Int {
        return (remainingDirectionChars
            .indexOfFirst { it.code >= size } + 1).addRemainingVisibleTreesToEdge(remainingDirectionChars.size)
    }

    private fun getTrees(testInput: Boolean): List<Tree> {
        return when (testInput) {
            true -> treesTestInput
            false -> trees
        }
    }

    private fun Int.addRemainingVisibleTreesToEdge(reaminingTreesInSight: Int) =
        if (toInt() == 0) reaminingTreesInSight else toInt()

    private fun getMaxTreeSizeForColumn(rows: List<IndexedValue<String>>?, columnIndex: Int): Int {
        return rows?.maxOfOrNull { it.value.toCharArray()[columnIndex].code } ?: 0
    }

    private fun getMaxTreeSizeForRowPart(treeRow: Iterable<Char>): Int {
        return treeRow.maxOfOrNull { it.code } ?: 0
    }

}

class Tree(
    private val size: Int,
    private val maxSizeTreeLeft: Int,
    private val maxSizeTreeRight: Int,
    private val maxSizeTreesBottom: Int,
    private val maxSizeTreesTop: Int,
    val scenicScore: Int
) {
    fun isVisible(): Boolean {
        return size > sequenceOf(
            this.maxSizeTreesBottom,
            this.maxSizeTreeLeft,
            this.maxSizeTreeRight,
            this.maxSizeTreesTop
        ).min()
    }
}
