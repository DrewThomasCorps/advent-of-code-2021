package day05

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
        val seafloor = List(1000) { (List(1000) { 0 }).toMutableList() }
        input.forEach {
            run {
                val (x1s, y1s, x2s, y2s) = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex().matchEntire(it)!!.destructured
                val x1 = x1s.toInt()
                val y1 = y1s.toInt()
                val x2 = x2s.toInt()
                val y2 = y2s.toInt()
                if (x1 == x2) {
                    if (y1 <= y2) {
                        for (y in y1..y2) {
                            seafloor[y][x1]++
                        }
                    } else {
                        for (y in y2..y1) {
                            seafloor[y][x1]++
                        }
                    }
                }
                if (y1 == y2) {
                    if (x1 <= x2) {
                        for (x in x1..x2) {
                            seafloor[y1][x]++
                        }
                    } else {
                        for (x in x2..x1) {
                            seafloor[y1][x]++
                        }
                    }
                }
            }
        }
        return seafloor.flatten().count { it > 1 }
    }

    fun partTwo(): Int {
        val seafloor = List(1000) { (List(1000) { 0 }).toMutableList() }
        input.forEach {
            run {
                val (x1s, y1s, x2s, y2s) = "(\\d+),(\\d+) -> (\\d+),(\\d+)".toRegex().matchEntire(it)!!.destructured
                val x1 = x1s.toInt()
                val y1 = y1s.toInt()
                val x2 = x2s.toInt()
                val y2 = y2s.toInt()

                val xMin = minOf(x1, x2)
                val xMax = maxOf(x1, x2)
                val yMin = minOf(y1, y2)
                val yMax = maxOf(y1, y2)

                if (x1 == x2) {
                    for (y in yMin..yMax) {
                        seafloor[y][x1]++
                    }
                } else if (y1 == y2) {
                    for (x in xMin..xMax) {
                        seafloor[y1][x]++
                    }
                } else {
                    var x = if (y1 == yMin) x1 else x2
                    val increment = x < xMax
                    for (y in yMin..yMax) {
                        seafloor[y][x]++
                        if (increment) {
                            x++
                        } else {
                            x--
                        }
                    }
                }
            }
        }

        return seafloor.flatten().count { it > 1 }
    }
}

