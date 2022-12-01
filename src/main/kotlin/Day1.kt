import java.io.File

fun main(args: Array<String>) {
    solvePartOne()
    solvePartTwo()
}


fun solvePartOne() {
    val input =
        File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day1/input.txt").readLines()
    val totalCalories: MutableList<Number> = mutableListOf()

    var subtotal = 0
    input.forEach {
        if (it.isEmpty()) {
            totalCalories.add(subtotal)
            subtotal = 0;
        } else {
            subtotal += it.toInt()
        }
    }

    totalCalories.add(subtotal)

    println("Most calories: ${totalCalories.maxByOrNull { it.toInt() }!!}")
}

fun solvePartTwo() {
    val input =
        File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day1/input.txt").readLines()
    val totalCalories: MutableList<Number> = mutableListOf()

    var subtotal = 0
    input.forEach {
        if (it.isEmpty()) {
            totalCalories.add(subtotal)
            subtotal = 0;
        } else {
            subtotal += it.toInt()
        }
    }

    totalCalories.add(subtotal)

    val sumCaloriesOfTopThreeElves = totalCalories.sortedBy { it.toInt() }.takeLast(3).sumOf { it.toInt() }

    println("Sum of calories of top three elves: $sumCaloriesOfTopThreeElves")
}
