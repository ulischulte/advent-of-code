package de.consuli.aoc.days

import de.consuli.aoc.Day

class Day07 : Day(7) {
    
    override fun partOne(testInput: Boolean): Any {
        this.init()
        parseDirectories(testInput)
        return directories.map { it.totalSize }.filter { it <= 100000 }.sum()
    }

    override fun partTwo(testInput: Boolean): Any {
        this.init()
        val totalSize = parseDirectories(testInput)
        directories.sortBy { it.totalSize }
        val sizeToDelete = totalSize - 40000000L
        val smallestDirectoryToDelete = directories.find { it.totalSize >= sizeToDelete }
        return smallestDirectoryToDelete!!.totalSize
    }

    private fun parseDirectories(testInput: Boolean): Long {
        val rootDirectory = Directory()
        var currentDirectory = rootDirectory
        getInput(testInput).forEach {
            when {
                it.startsWith(CHANGE_DIRECTORY_COMMAND) ->
                    when (val directoryName = it.substringAfter(CHANGE_DIRECTORY_COMMAND).trim()) {
                        "/" -> currentDirectory = rootDirectory
                        ".." -> currentDirectory = currentDirectory.parentDirectory!!
                        else -> currentDirectory =
                            currentDirectory.subDirectories.getOrPut(directoryName) {
                                Directory(currentDirectory)
                            }
                    }

                it.startsWith(LIST_DIRECTORY_COMMAND) -> {
                    // do nothing
                }

                // line with either
                //      directory with name in the current directory
                //      size and filename in current directory
                else -> {
                    val (commandOrFilesize, name) = it.split(" ")
                    if (commandOrFilesize == "dir") {
                        currentDirectory.subDirectories.getOrPut(name) { Directory(currentDirectory) }
                    } else {
                        currentDirectory.files[name] = commandOrFilesize.toLong()
                    }
                }
            }
        }
        return scanDirectoryAndReturnTotalSize(rootDirectory)
    }

    private fun scanDirectoryAndReturnTotalSize(directoryToScan: Directory): Long {
        var totalSize = 0L
        directoryToScan.subDirectories.values.forEach { directory ->
            totalSize += scanDirectoryAndReturnTotalSize(directory)
        }
        totalSize += directoryToScan.directorySize()
        if (totalSize <= 100000) {
            totalSizeOfDirectoriesWithAtMost100000 += totalSize
        }
        directoryToScan.totalSize = totalSize
        directories += directoryToScan
        return totalSize
    }

    private fun init() {
        totalSizeOfDirectoriesWithAtMost100000 = 0L
        directories = ArrayList()
    }

    companion object {
        private const val CHANGE_DIRECTORY_COMMAND = "$ cd "
        private const val LIST_DIRECTORY_COMMAND = "$ ls"
        private var totalSizeOfDirectoriesWithAtMost100000 = 0L
        private var directories = ArrayList<Directory>()
    }
}

class Directory(val parentDirectory: Directory? = null) {
    val subDirectories = HashMap<String, Directory>()
    val files = HashMap<String, Long>()
    var totalSize = 0L

    fun directorySize(): Long {
        return files.map { files -> files.value }.sum()
    }
}
