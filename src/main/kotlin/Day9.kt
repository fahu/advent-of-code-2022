import java.io.File
import kotlin.math.sign

fun main() {
    Day9.solvePartOne()
}

data class Knot(val name: String, var x: Int, var y: Int) {

    private val movementHistory = mutableSetOf<String>()

    fun isNeighborOf(other: Knot): Boolean {
        return kotlin.math.abs(x - other.x) <= 1 && kotlin.math.abs(y - other.y) <= 1
    }

    fun move(direction: List<String>) {
        direction.forEach {
            when (it) {
                "U" -> y += 1
                "D" -> y -= 1
                "R" -> x += 1
                "L" -> x -= 1
            }
        }

        println("$name moved to  y: $y, x: $x,")

        movementHistory.add("$y,$x")
    }

    fun getNumberOfVisitedPositions(): Int {
        return movementHistory.size + 1
    }
}

class Day9 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day9/input.txt").readLines()

            val knots = listOf(
                Knot("Head", 0, 0),
                Knot("Knot 1", 0, 0),
                Knot("Knot 2", 0, 0),
                Knot("Knot 3", 0, 0),
                Knot("Knot 4", 0, 0),
                Knot("Knot 5", 0, 0),
                Knot("Knot 6", 0, 0),
                Knot("Knot 7", 0, 0),
                Knot("Knot 8", 0, 0),
                Knot("Knot 9", 0, 0),
            )

            input.forEach {
                println("------- New Round: $it -------")
                val (direction, distance) = it.split(' ')
                repeat(distance.toInt()) {
                    knots.first().move(listOf(direction))

                    for (i in 1 until knots.size) {
                        val head = knots[i - 1]
                        val tail = knots[i]
                        if (!tail.isNeighborOf(head)) {
                            val distanceX = head.x - tail.x
                            val distanceY = head.y - tail.y
                            val movements: MutableList<String> = mutableListOf()

                            if (distanceX.sign > 0) {
                                movements.add("R")
                            } else if (distanceX.sign < 0) {
                                movements.add("L")
                            }

                            if (distanceY.sign > 0) {
                                movements.add("U")
                            } else if (distanceY.sign < 0) {
                                movements.add("D")
                            }

                            tail.move(movements)
                        }
                    }
                }
            }

            println("${knots.last().name} visited ${knots.last().getNumberOfVisitedPositions()} positions")
        }
    }
}
