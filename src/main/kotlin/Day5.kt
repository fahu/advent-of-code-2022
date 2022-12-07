import java.io.File

fun main() {
    Day5.solvePartOne()
    Day5.solvePartTwo()
}


class Day5 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day5/input.txt").readLines()

            val storage: HashMap<Int, MutableList<Char>> = HashMap()

            input.forEach {
                if (it.startsWith("move")) {
                    val (quantity, _, from, _, to) = it.removePrefix("move ").split(' ')

                    for (i in 1 until quantity.toInt() + 1) {
                        val crateToMove = storage[from.toInt() - 1]!!.removeLast()
                        storage[to.toInt() - 1]!!.add(crateToMove)
                    }
                } else {
                    for (i in 0 until it.length / 4 + 1) {
                        val indexOfCrate = i * 4 + 1
                        if (it.length < indexOfCrate) {
                            continue
                        }

                        val crate = it[indexOfCrate]
                        if (!crate.isLetter()) {
                            continue
                        }

                        storage[i]?.add(0, crate) ?: storage.put(i, mutableListOf(crate))
                    }
                }
            }

            println("The solution of part 1 is: ")
            storage.forEach { (_, value) ->
                print("${value.last()}")
            }
            println()
        }

        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day5/input.txt").readLines()

            val storage: HashMap<Int, MutableList<Char>> = HashMap()

            input.forEach { it ->
                if (it.startsWith("move")) {
                    val (quantity, _, from, _, to) = it.removePrefix("move ").split(' ')

                    val cratesToMove = storage[from.toInt() - 1]!!.takeLast(quantity.toInt())

                    repeat(cratesToMove.size) { storage[from.toInt() - 1]!!.removeLast() }

                    storage[to.toInt() - 1]!!.addAll(cratesToMove)

                    println(storage.entries.sumOf { it.value.size })
                } else {
                    for (i in 0 until it.length / 4 + 1) {
                        val indexOfCrate = i * 4 + 1
                        if (it.length < indexOfCrate) {
                            continue
                        }

                        val crate = it[indexOfCrate]
                        if (!crate.isLetter()) {
                            continue
                        }

                        storage[i]?.add(0, crate) ?: storage.put(i, mutableListOf(crate))
                    }
                }
            }

            println("The solution of part 2 is: ")
            storage.forEach { (_, value) ->
                if (value.isNotEmpty()) {
                    print("${value.last()}")
                }
            }
            println()
        }
    }
}
