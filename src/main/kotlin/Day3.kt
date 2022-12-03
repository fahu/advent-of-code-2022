import java.io.File

fun main() {
    Day3.solvePartOne()
    Day3.solvePartTwo()
}


class Day3 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day3/input.txt").readLines()

            var score = 0

            input.forEach {
                val firstPart = it.take(it.length / 2)
                val secondPart = it.takeLast(it.length / 2)
                val commonCharacter = findCommonCharacter(firstPart, secondPart)
                println("Common character: $commonCharacter (${calculateScoreOfCharacter(commonCharacter)})")
                score += calculateScoreOfCharacter(commonCharacter)
            }

            println("The score is: $score")
        }

        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day3/input.txt").readLines()

            var score = 0

            for (i in 0 until input.size / 3) {
                val firstPart = input[i * 3 + 0]
                val secondPart = input[i * 3 + 1]
                val thirdPart = input[i * 3 + 2]

                val commonCharacter = findCommonCharacter(firstPart, secondPart, thirdPart)
                println("Common character: $commonCharacter (${calculateScoreOfCharacter(commonCharacter)})")
                score += calculateScoreOfCharacter(commonCharacter)
            }

            println("The score is: $score")
        }

        private fun findCommonCharacter(firstPart: String, secondPart: String): Char {
            firstPart.forEach {
                if (secondPart.contains(it, ignoreCase = false)) {
                    return it
                }
            }

            throw Exception("Could not find common character.")
        }

        private fun findCommonCharacter(firstPart: String, secondPart: String, thirdPart: String): Char {
            firstPart.forEach {
                if (secondPart.contains(it, ignoreCase = false) && thirdPart.contains(it, ignoreCase = false)) {
                    return it
                }
            }

            throw Exception("Could not find common character.")
        }

        private fun calculateScoreOfCharacter(character: Char): Int {
            return if (character.isUpperCase()) {
                character.code - 'A'.code + 1 + 26
            } else {
                character.code - 'a'.code + 1
            }
        }
    }
}
