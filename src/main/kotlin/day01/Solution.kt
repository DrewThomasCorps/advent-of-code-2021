package day01

import java.io.File

fun main() {
    val solution = Solution()
    println("Part 1: ${solution.partOne()}")
    println("Part 2: ${solution.partTwo()}")
}

class Solution {
    private val depths: List<Int>

    init {
        val file = File({}.javaClass.getResource("day01.txt")?.file ?: throw NullPointerException())
        val input = file.readLines()
        depths = input.map { Integer.valueOf(it) }
    }

    fun partOne(): Int {
        var previous = 0
        var increasedCount = -1 // Start at -1 so the first depth increments count to 0
        for (depth in depths) {
            if (depth > previous) {
                increasedCount++
            }
            previous = depth
        }
        return increasedCount
    }

    fun partTwo(): Int {
        var previous = 0
        var increasedCount = 0
        var amount = 0
        val queue = mutableListOf<Int>()
        for (depth in depths) {
            queue.add(depth)
            amount += depth
            // Get to size three then start tracking windows
            if (queue.size <= 3) {
                previous = amount
            } else {
                amount -= queue.removeFirst()
                if (amount > previous) {
                    increasedCount++
                }
                previous = amount
            }
        }
        return increasedCount
    }
}

