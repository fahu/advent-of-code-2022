import java.io.File

fun main() {
    Day8Part1.solvePartOne()
}


class Day8Part1 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day8/input.txt").readLines()


            var visibleTrees = 0
            for (rowIndex in input.indices) {
                val row = input[rowIndex]
                for (columnIndex in row.indices) {
                    if (rowIndex == 0 || columnIndex == 0 || rowIndex == input.size - 1 || columnIndex == row.length - 1) { // is outer edge
                        visibleTrees++
                        println(
                            "The outer edge tree with height ${
                                row[columnIndex].toString().toInt()
                            } is visible, row: $rowIndex, column: $columnIndex"
                        )
                        continue
                    }

                    val currentTreeHeight = row[columnIndex].toString().toInt()

                    if (isTreeVisibleFromTheLeft(currentTreeHeight, input, rowIndex, columnIndex)) {
                        visibleTrees++
                    } else if (isTreeVisibleFromTheTop(currentTreeHeight, input, rowIndex, columnIndex)) {
                        visibleTrees++
                    } else if (isTreeVisibleFromTheRight(currentTreeHeight, input, rowIndex, columnIndex)) {
                        visibleTrees++
                    } else if (isTreeVisibleFromTheBottom(currentTreeHeight, input, rowIndex, columnIndex)) {
                        visibleTrees++
                    } else {
                        println("$currentTreeHeight is not visible, row: $rowIndex, column: $columnIndex")
                    }
                }
            }

            println("Number of visible trees: $visibleTrees")
        }

        private fun isTreeVisibleFromTheBottom(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Boolean {
            val isVisibleFromTheBottom = currentTreeHeight > input[rowIndex + 1][columnIndex].toString().toInt()
            if (!isVisibleFromTheBottom) {
                return false
            }

            return if (rowIndex + 1 == input.size - 1) {    // is bottom edge
                println(
                    "The inner edge tree with height ${
                        input[rowIndex][columnIndex].toString().toInt()
                    } is visible from the bottom, row: $rowIndex, column: $columnIndex"
                )
                true
            } else {
                isTreeVisibleFromTheBottom(currentTreeHeight, input, rowIndex + 1, columnIndex)
            }
        }

        private fun isTreeVisibleFromTheRight(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Boolean {
            val isVisibleFromTheRight = currentTreeHeight > input[rowIndex][columnIndex + 1].toString().toInt()
            if (!isVisibleFromTheRight) {
                return false
            }

            return if (columnIndex + 1 == input[rowIndex].length - 1) {
                println(
                    "The inner edge tree with height ${
                        input[rowIndex][columnIndex].toString().toInt()
                    } is visible from the right, row: $rowIndex, column: $columnIndex"
                )
                true
            } else {
                isTreeVisibleFromTheRight(currentTreeHeight, input, rowIndex, columnIndex + 1)
            }
        }

        private fun isTreeVisibleFromTheTop(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Boolean {
            val isVisibleFromTheTop = currentTreeHeight >input[rowIndex - 1][columnIndex].toString().toInt()
            if (!isVisibleFromTheTop) {
                return false
            }

            return if (rowIndex - 1 == 0) {
                println(
                    "The inner edge tree with height ${
                        input[rowIndex][columnIndex].toString().toInt()
                    } is visible from the top, row: $rowIndex, column: $columnIndex"
                )
                true
            } else {
                isTreeVisibleFromTheTop(currentTreeHeight, input, rowIndex - 1, columnIndex)
            }
        }

        private fun isTreeVisibleFromTheLeft(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Boolean {
            val isVisibleFromTheLeft = currentTreeHeight > input[rowIndex][columnIndex - 1].toString().toInt()
            if (!isVisibleFromTheLeft) {
                return false
            }

            return if (columnIndex - 1 == 0) {
                println(
                    "The inner edge tree with height ${
                        input[rowIndex][columnIndex].toString().toInt()
                    } is visible from the left, row: $rowIndex, column: $columnIndex"
                )
                true
            } else {
                isTreeVisibleFromTheLeft(currentTreeHeight, input, rowIndex, columnIndex - 1)
            }
        }
    }
}
