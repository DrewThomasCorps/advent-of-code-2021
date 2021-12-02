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
        Forward,
        Down,
        Up
    }

    fun partOne(): Int {
        var depth = 0;
        var horizontal = 0;
        input.forEach {
            val (direction, value) = it.split(" ").let { (x, y) -> Pair(x, y.toInt()) }
            when (direction) {
                Direction.Forward.name.lowercase() -> {
                    horizontal += value
                }
                Direction.Down.name.lowercase() -> depth += value
                Direction.Up.name.lowercase() -> depth -= value
            }
        }
        return depth * horizontal
    }


        fun partTwo(): Int {
            var depth = 0;
            var horizontal = 0;
            var aim = 0
            input.forEach {
                val (direction, value) = it.split(" ").let { (x, y) -> Pair(x, y.toInt()) }
                when (direction) {
                    Direction.Forward.name.lowercase() -> {
                        horizontal += value
                        depth += aim * value
                    }
                    Direction.Down.name.lowercase() -> aim += value
                    Direction.Up.name.lowercase() -> aim -= value
                }
            }
            return depth * horizontal
        }
    }