import java.io.File

fun main() {
    Day7.solvePartOneAndTwo()
}

data class DirectoryOrFile(
    val name: String,
    val size: Int,
    val children: MutableList<DirectoryOrFile> = mutableListOf(),
    val parent: DirectoryOrFile? = null,
) {
    fun calculateSize(): Int {
        return if (children.isEmpty()) {
            size
        } else {
            size + children.sumOf { it.calculateSize() }
        }
    }
}

class Day7 {
    companion object {
        fun solvePartOneAndTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day7/input.txt").readLines()

            val rootDirectory = DirectoryOrFile("/", 0)

            var currentWorkingDirectory = rootDirectory

            input.forEach {
                if (it.startsWith("$ cd")) {
                    currentWorkingDirectory = when (it) {
                        "$ cd /" -> {
                            rootDirectory
                        }

                        "$ cd .." -> {
                            currentWorkingDirectory.parent ?: rootDirectory
                        }

                        else -> {
                            currentWorkingDirectory.children.firstOrNull() { child ->
                                child.name == it.removePrefix("$ cd ")
                            }!!
                        }
                    }
                } else if (it == "$ ls") {
                    // nothing to be done
                } else if (it.startsWith("dir")) {
                    currentWorkingDirectory.children.add(
                        DirectoryOrFile(
                            it.removePrefix("dir "), 0, parent = currentWorkingDirectory
                        )
                    )
                } else {
                    val (size, name) = it.split(' ')
                    currentWorkingDirectory.children.add(
                        DirectoryOrFile(
                            name, size.toInt(), parent = currentWorkingDirectory
                        )
                    )
                }
            }

            println("Solution of part 1: ${calculateSizeOfDirectories(rootDirectory)}")

            val minFreeUpValue = 30000000 - (70000000 - rootDirectory.calculateSize())
            println("Solution of part 2: ${findDirectoryToDelete(rootDirectory, Int.MAX_VALUE, minFreeUpValue)}")

        }

        private fun calculateSizeOfDirectories(directory: DirectoryOrFile): Int {
            var finalSize = 0

            if (directory.children.isNotEmpty()) {
                val currentDirectorySize = directory.calculateSize()
                if (currentDirectorySize <= 100000) {
                    println("Counting directory ${directory.name} with total size $currentDirectorySize and own size ${directory.size}")
                    finalSize += currentDirectorySize
                }
            }

            directory.children.forEach {
                if (it.children.isNotEmpty()) {
                    val subDirectorySize = calculateSizeOfDirectories(it)
                    finalSize += subDirectorySize
                }
            }
            return finalSize
        }

        private fun findDirectoryToDelete(directory: DirectoryOrFile, minValue: Int, minFreeUpValue: Int): Int {
            var minDirectorySize = minValue

            if (directory.children.isNotEmpty()) {
                val currentDirectorySize = directory.calculateSize()
                if (currentDirectorySize in minFreeUpValue until minDirectorySize) {
                    minDirectorySize = currentDirectorySize
                    println("New smallest directoryFound: $minDirectorySize")
                }
            }

            directory.children.forEach {
                if (it.children.isNotEmpty()) {
                    val subDirectorySize = findDirectoryToDelete(it, minValue, minFreeUpValue)
                    if (subDirectorySize in minFreeUpValue until minDirectorySize) {
                        minDirectorySize = subDirectorySize
                        println("New smallest directory Found: $minDirectorySize")
                    }
                }
            }
            return minDirectorySize
        }
    }
}
