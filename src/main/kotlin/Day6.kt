import java.io.File

fun main() {
    Day6.solvePartOne()
    Day6.solvePartTwo()
}


class Day6 {
    companion object {

        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day6/input.txt").readLines()
                    .first()

            var counter = 4
            input.windowed(4).map { window ->
                val isUniqueSequence = window.toSet().size == 4
                if (isUniqueSequence) {
                    println("The solution of part 1 is: $counter")
                    return
                }
                counter += 1
            }
        }

        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day6/input.txt").readLines()
                    .first()

            var counter = 14
            input.windowed(14).map { window ->
                val isUniqueSequence = window.toSet().size == 14
                if (isUniqueSequence) {
                    println("The solution of part 2 is: $counter")
                    return
                }
                counter += 1
            }
        }
    }
}
