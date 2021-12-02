package day02

import java.io.File

fun main() {
    val file = File({}.javaClass.getResource("input.txt")?.file ?: throw NullPointerException())
    val input = file.readLines()
    val solution = Solution(input)
    println("Part 1: ${solution.partOne()}")
    println("Part 2: ${solution.partTwo()}")
}

class Solution(private val input: List<String>) {
    enum class Direction {
        forward,
        down,
        up
    }

    fun partOne(): Int {

        var depth = 0;
        var horizontal = 0;
        iterateOverInput { direction: Direction, value: Int ->
            run {
                when (direction) {
                    Direction.forward -> horizontal += value
                    Direction.down -> depth += value
                    Direction.up -> depth -= value
                }
            }
        }

        return depth * horizontal
    }

    fun partTwo(): Int {
        var depth = 0;
        var horizontal = 0;
        var aim = 0
        iterateOverInput { direction: Direction, value: Int ->
            run {
                when (direction) {
                    Direction.forward -> {
                        horizontal += value
                        depth += aim * value
                    }
                    Direction.down -> aim += value
                    Direction.up -> aim -= value
                }
            }
        }
        return depth * horizontal
    }

    private fun iterateOverInput(function: (Direction, Int) -> Unit) {
        input.forEach {
            val (direction, value) = it.split(" ").let { (x, y) -> Pair(Direction.valueOf(x), y.toInt()) }
            function.invoke(direction, value)
        }
    }
}


