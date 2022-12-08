import java.io.File

fun main() {
    Day8Part2.solvePartTwo()
}


class Day8Part2 {
    companion object {
        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day8/input.txt").readLines()

            val scenicScores: MutableList<Int> = mutableListOf()
            for (rowIndex in input.indices) {
                val row = input[rowIndex]
                for (columnIndex in row.indices) {
                    if (rowIndex == 0 || columnIndex == 0 || rowIndex == input.size - 1 || columnIndex == row.length - 1) { // is outer edge
                        // The edge trees counts as 0 and therefore does not need to be included in the calculation
                        continue
                    }

                    val currentTreeHeight = row[columnIndex].toString().toInt()
                    val scenicScore = calculateScenicScoreToTop(
                        currentTreeHeight,
                        input,
                        rowIndex,
                        columnIndex
                    ) * calculateScenicScoreToLeft(
                        currentTreeHeight,
                        input,
                        rowIndex,
                        columnIndex
                    ) * calculateScenicScoreToRight(
                        currentTreeHeight,
                        input,
                        rowIndex,
                        columnIndex
                    ) * calculateScenicScoreToBottom(currentTreeHeight, input, rowIndex, columnIndex)

                    scenicScores.add(scenicScore)
                }
            }

            println("Highest scenic score: ${scenicScores.maxOf { it }}")
        }

        private fun calculateScenicScoreToBottom(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Int {
            val isVisibleFromTheBottom = currentTreeHeight > input[rowIndex + 1][columnIndex].toString().toInt()
            return if (!isVisibleFromTheBottom || rowIndex + 1 == input.size - 1) {
                1
            } else {
                1 + calculateScenicScoreToBottom(currentTreeHeight, input, rowIndex + 1, columnIndex)
            }
        }

        private fun calculateScenicScoreToRight(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Int {
            val isVisibleFromTheRight = currentTreeHeight > input[rowIndex][columnIndex + 1].toString().toInt()
            return if (!isVisibleFromTheRight || columnIndex + 1 == input[rowIndex].length - 1) {
                1
            } else {
                1 + calculateScenicScoreToRight(currentTreeHeight, input, rowIndex, columnIndex + 1)
            }
        }

        private fun calculateScenicScoreToLeft(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Int {
            val isVisibleFromTheTop = currentTreeHeight > input[rowIndex - 1][columnIndex].toString().toInt()
            return if (!isVisibleFromTheTop || rowIndex - 1 == 0) {
                1
            } else {
                1 + calculateScenicScoreToLeft(currentTreeHeight, input, rowIndex - 1, columnIndex)
            }
        }

        private fun calculateScenicScoreToTop(
            currentTreeHeight: Int,
            input: List<String>,
            rowIndex: Int,
            columnIndex: Int,
        ): Int {
            val isVisibleFromTheLeft = currentTreeHeight > input[rowIndex][columnIndex - 1].toString().toInt()
            return if (!isVisibleFromTheLeft || columnIndex - 1 == 0) {
                1
            } else {
                1 + calculateScenicScoreToTop(currentTreeHeight, input, rowIndex, columnIndex - 1)
            }
        }
    }
}
