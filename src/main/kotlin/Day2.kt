import java.io.File

fun main() {
    Day2.solvePartOne()
}


class Day2 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day2/input.txt").readLines()

            val gameCalculatorPart1 = RockPaperScissorsGameCalculatorPart1(input)
            println("Total Score of Part 1: ${gameCalculatorPart1.calculate()}")

            val gameCalculatorPart2 = RockPaperScissorsGameCalculatorPart2(input)
            println("Total Score of Part 2: ${gameCalculatorPart2.calculate()}")
        }
    }
}


class RockPaperScissorsGameCalculatorPart1(private val gameData: List<String>) {
    enum class Shape(private val score: Int) {
        Rock(1), Paper(2), Scissors(3);

        fun getShapeScore(): Int = score
    }

    fun calculate(): Int {
        return this.gameData.sumOf {
            val (player1Shape, player2Shape) = it.parseRound()

            val player2Score = calculateRoundScoreOfPlayer2(player1Shape, player2Shape) + player2Shape.getShapeScore()
            player2Score
        }
    }

    private fun calculateRoundScoreOfPlayer2(
        player1Shape: Shape,
        player2Shape: Shape,
    ): Int {
        return if (player1Shape == player2Shape) {
            3
        } else if (player1Shape == Shape.Scissors && player2Shape == Shape.Paper) {
            0
        } else if (player1Shape == Shape.Paper && player2Shape == Shape.Rock) {
            0
        } else if (player1Shape == Shape.Rock && player2Shape == Shape.Scissors) {
            0
        } else {
            6
        }
    }

    private fun String.parseRound(): Pair<Shape, Shape> {
        val roundMoves = this.split(' ')

        val player1Shape = roundMoves.first().let {
            when (it) {
                "A" -> Shape.Rock
                "B" -> Shape.Paper
                "C" -> Shape.Scissors
                else -> throw Exception()
            }
        }

        val player2Shape = roundMoves.last().let {
            when (it) {
                "X" -> Shape.Rock
                "Y" -> Shape.Paper
                "Z" -> Shape.Scissors
                else -> throw Exception()
            }
        }

        return Pair(player1Shape, player2Shape)
    }
}

class RockPaperScissorsGameCalculatorPart2(private val gameData: List<String>) {
    enum class Shape(private val score: Int) {
        Rock(1), Paper(2), Scissors(3);

        fun getShapeScore(): Int = score
    }

    enum class Outcome(private val score: Int) {
        Lose(0), Draw(3), Win(6);

        fun getOutcomeScore(): Int = score
    }


    fun calculate(): Int {
        return this.gameData.sumOf {
            val (opponentShape, desiredOutcome) = it.parseRound()

            val shapeForDesiredOutcome = calculateShapeForDesiredOutcome(opponentShape, desiredOutcome)

            calculateRoundScoreOfPlayer2(opponentShape, shapeForDesiredOutcome) + shapeForDesiredOutcome.getShapeScore()
        }
    }

    private fun calculateShapeForDesiredOutcome(
        opponentShape: Shape,
        desiredOutcome: Outcome,
    ): Shape {
        return when (desiredOutcome) {
            Outcome.Draw -> {
                return opponentShape
            }

            Outcome.Win -> {
                opponentShape.findSuperiorShape()
            }

            Outcome.Lose -> {
                opponentShape.findInferiorShape()
            }
        }
    }

    private fun Shape.findSuperiorShape(): Shape {
        return when (this) {
            Shape.Paper -> Shape.Scissors
            Shape.Scissors -> Shape.Rock
            Shape.Rock -> Shape.Paper
        }
    }

    private fun Shape.findInferiorShape(): Shape {
        return when (this) {
            Shape.Paper -> Shape.Rock
            Shape.Scissors -> Shape.Paper
            Shape.Rock -> Shape.Scissors
        }
    }

    private fun calculateRoundScoreOfPlayer2(
        player1Shape: Shape,
        player2Shape: Shape,
    ): Int {
        return if (player1Shape == player2Shape) {
            Outcome.Draw.getOutcomeScore()
        } else if (player1Shape == Shape.Scissors && player2Shape == Shape.Paper) {
            Outcome.Lose.getOutcomeScore()
        } else if (player1Shape == Shape.Paper && player2Shape == Shape.Rock) {
            Outcome.Lose.getOutcomeScore()
        } else if (player1Shape == Shape.Rock && player2Shape == Shape.Scissors) {
            Outcome.Lose.getOutcomeScore()
        } else {
            Outcome.Win.getOutcomeScore()
        }
    }

    private fun String.parseRound(): Pair<Shape, Outcome> {
        val roundMoves = this.split(' ')

        val opponentShape = roundMoves.first().let {
            when (it) {
                "A" -> Shape.Rock
                "B" -> Shape.Paper
                "C" -> Shape.Scissors
                else -> throw Exception()
            }
        }

        val desiredOutcome = roundMoves.last().let {
            when (it) {
                "X" -> Outcome.Lose
                "Y" -> Outcome.Draw
                "Z" -> Outcome.Win
                else -> throw Exception()
            }
        }

        return Pair(opponentShape, desiredOutcome)
    }
}

