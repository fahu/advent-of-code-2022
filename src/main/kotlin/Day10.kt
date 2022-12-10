import java.io.File
import java.util.*

fun main() {
    Day10.solvePartOne()
    Day10.solvePartTwo()
}

data class Instruction(val operation: Operation, val argument: Int, var executionCycles: Int)


enum class Operation {
    NOOP, ADDX
}

class InstructionFactory {
    companion object {
        fun createInstruction(instructionLine: String): Instruction {
            val instructionArguments = instructionLine.split(' ')

            return when (instructionArguments.first()) {
                "noop" -> noopInstruction()
                "addx" -> addXInstruction(instructionArguments.last().toInt())
                else -> throw IllegalArgumentException("Unknown operation: $instructionArguments")
            }
        }

        private fun noopInstruction(): Instruction {
            return Instruction(Operation.NOOP, 0, 1)
        }

        private fun addXInstruction(value: Int): Instruction {
            return Instruction(Operation.ADDX, value, 2)
        }
    }
}

data class Cpu(val stack: Queue<Instruction>) {
    private var registerX: Int = 1

    fun nextCycle(): Int {
        val instruction = stack.peek()
        instruction.executionCycles--

        if (instruction.executionCycles > 0) {
            return this.registerX
        } else {
            stack.poll()
        }

        val initialRegisterX = registerX
        when (instruction.operation) {
            Operation.NOOP -> {
                // do nothing
            }

            Operation.ADDX -> {
                registerX += instruction.argument
            }
        }

        return initialRegisterX
    }

}

class Day10 {
    companion object {
        fun solvePartOne() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day10/input.txt").readLines()

            val instructions = input.map { line ->
                InstructionFactory.createInstruction(line)
            }

            val cpu = Cpu(LinkedList(instructions))

            val runtimeDuration = 220
            val peekCycles = listOf(20, 60, 100, 140, 180, 220)
            var signalStrength = 0

            for (i in 1..runtimeDuration) {
                val registerX = cpu.nextCycle()

                if (peekCycles.contains(i)) {
                    signalStrength += registerX * i
                    println("Cycle: $i, Register X: $registerX, Signal Strength: ${registerX * i}")
                }
            }

            println("The total signal strength is: $signalStrength")
        }

        fun solvePartTwo() {
            val input =
                File("/Users/fah/dev/advent-of-code-2022/advent-of-code-2022/src/main/resources/day10/input.txt").readLines()

            val instructions = input.map { line ->
                InstructionFactory.createInstruction(line)
            }

            val cpu = Cpu(LinkedList(instructions))

            for (i in 1..6) {
                for (j in 1..40) {
                    val registerX = cpu.nextCycle()

                    if (listOf(registerX, registerX + 1, registerX + 2).contains(j)) {
                        print("█")
                    } else {
                        print(" ")
                    }

                    if (j == 40) {
                        println()
                    }
                }
            }
        }
    }
}
