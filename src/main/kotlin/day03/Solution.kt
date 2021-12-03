package day03

import java.io.File
import kotlin.math.pow
import kotlin.math.roundToLong

fun main() {
    val file = File({}.javaClass.getResource("input.txt")?.file ?: throw NullPointerException())
    val input = file.readLines()
    val solution = Solution(input)
    println("Part 1: ${solution.partOne().roundToLong()}")
    println("Part 2: ${solution.partTwo()}")
}

class Solution(private val input: List<String>) {
    fun partOne(): Double {
        val zeroBitMap = mutableMapOf<Int, Int>()
        val oneBitMap = mutableMapOf<Int, Int>()
        input.forEach {
            it.split("").forEachIndexed { index, s ->
                run {
                    when (s) {
                        "0" -> zeroBitMap[index] = zeroBitMap.getOrDefault(index, 0) + 1
                        "1" -> oneBitMap[index] = oneBitMap.getOrDefault(index, 0) + 1
                    }
                }
            }
        }
        var gamma = 0.0
        var epsilon = 0.0
        val bits = input[0].length
        zeroBitMap.onEach { entry ->
            run {
                if (entry.value > oneBitMap[entry.key]!!) {
                    epsilon += 2.0.pow((bits - entry.key).toDouble())
                } else {
                    gamma += 2.0.pow((bits - entry.key).toDouble())
                }
            }
        }
        return gamma * epsilon
    }

    fun partTwo(): Int {
        var oxygenInputs = input.toCollection(mutableListOf())
        var index = 0
        while (oxygenInputs.size > 1) {
            oxygenInputs = oxygenInputs.filter {
                val (zeros, ones) = getBitCountsAtIndex(index, oxygenInputs)
                if (zeros > ones) {
                    it[index] == '0'
                } else {
                    it[index] == '1'
                }
            }.toMutableList()
            index++
        }
        var dioxideInputs = input.toCollection(mutableListOf())
        index = 0
        while (dioxideInputs.size > 1) {
            dioxideInputs = dioxideInputs.filter {
                val (zeros, ones) = getBitCountsAtIndex(index, dioxideInputs)
                if (ones < zeros) {
                    it[index] == '1'
                } else {
                    it[index] == '0'
                }
            }.toMutableList()
            index++
        }


        return Integer.parseInt(oxygenInputs[0], 2) * Integer.parseInt(dioxideInputs[0], 2)
    }

    private fun getBitCountsAtIndex(index: Int, values: List<String>): Pair<Int, Int> {
        var zeroCount = 0
        var oneCount = 0
        values.forEach {
            if (it[index] == '0') {
                zeroCount++
            } else {
                oneCount++
            }
        }
        return Pair(zeroCount, oneCount)
    }
}


