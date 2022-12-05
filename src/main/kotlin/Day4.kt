import java.io.File

fun main() {
    Day4.solvePartOne()
    Day4.solvePartTwo()
}


class Day4 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day4/input.txt").readLines()

            var score = 0
            input.forEach {
                val (firstRange, secondRange) = it.split(',')

                if (isInRange(firstRange, secondRange) || isInRange(secondRange, firstRange)) {
                    score++
                }
            }

            println("The score for including is: $score")
        }

        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day4/input.txt").readLines()

            var score = 0
            input.forEach {
                val (firstRange, secondRange) = it.split(',')

                if (isOverlapping(firstRange, secondRange) || isOverlapping(secondRange, firstRange)) {
                    score++
                }
            }

            println("The score for overlapping is: $score")
        }

        private fun isInRange(firstRange: String, secondRange: String): Boolean {
            val (firstRangeStartValue, firstRangeEndValue) = firstRange.split('-')
            val (secondRangeStartValue, secondRangeEndValue) = secondRange.split('-')

            return firstRangeStartValue.toInt() <= secondRangeStartValue.toInt() && firstRangeEndValue.toInt() >= secondRangeEndValue.toInt()
        }

        private fun isOverlapping(firstRange: String, secondRange: String): Boolean {
            val (firstRangeStartValue, firstRangeEndValue) = firstRange.split('-')
            val (secondRangeStartValue, _) = secondRange.split('-')

            return firstRangeStartValue.toInt() <= secondRangeStartValue.toInt() && firstRangeEndValue.toInt() >= secondRangeStartValue.toInt()
        }
    }
}
