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
    fun partOne(): Int {
        var depth = 0;
        var horizontal = 0;
        input.forEach {
            if (it.startsWith("forward")) {
                horizontal += it.substringAfter(" ").toInt()
            } else if (it.startsWith("down")) {
                depth += it.substringAfter(" ").toInt()
            } else if (it.startsWith("up")) {
                depth -= it.substringAfter(" ").toInt()
            }
        }
        return depth * horizontal
    }


        fun partTwo(): Int {
            var depth = 0;
            var horizontal = 0;
            var aim = 0
            input.forEach {
                val value = it.substringAfter(" ").toInt()
                if (it.startsWith("forward")) {
                    horizontal += value
                    depth += aim * value
                } else if (it.startsWith("down")) {
                    aim += value
                } else if (it.startsWith("up")) {
                    aim -= value
                }
            }
            return depth * horizontal
        }
    }