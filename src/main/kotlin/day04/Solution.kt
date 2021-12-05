package day04

import java.io.File
import kotlin.math.roundToLong

fun main() {
    val file = File({}.javaClass.getResource("input.txt")?.file ?: throw NullPointerException())
    val input = file.readLines()
    val solution = Solution(input)
    println("Part 1: ${solution.partOne()}")
    println("Part 2: ${solution.partTwo()}")
}

class Solution(private val input: List<String>) {
    fun partOne(): Int {
        val drawNumbers = input[0].split(',').map { it.toInt() }
        val boardsStrings = input.subList(2, input.size).windowed(5, 6)
        val boards = boardsStrings.map { stringsList ->
            stringsList.map { string ->
                string.trim().split("\\s+".toRegex()).map { number -> Cell(number.toInt()) }
            }
        }.map { Board(it) }
        var drawIndex = -1
        while (boards.none { it.isWinner() }) {
            drawIndex++
            boards.forEach { it.visitForNumber(drawNumbers[drawIndex]) }
        }
        val winner = boards.first { it.isWinner() }
        return winner.unvisitedCellSum() * drawNumbers[drawIndex]
    }

    fun partTwo(): Int {
        val drawNumbers = input[0].split(',').map { it.toInt() }
        val boardsStrings = input.subList(2, input.size).windowed(5, 6)
        var boards = boardsStrings.map { stringsList ->
            stringsList.map { string ->
                string.trim().split("\\s+".toRegex()).map { number -> Cell(number.toInt()) }
            }
        }.map { Board(it) }.toMutableList()
        var drawIndex = -1
        while (boards.size > 1) {
            drawIndex++
            boards.forEach { it.visitForNumber(drawNumbers[drawIndex]) }
            boards.removeIf { it.isWinner() }
        }
        val finalBoard = boards[0]
        while (!finalBoard.isWinner()) {
            finalBoard.visitForNumber(drawNumbers[++drawIndex])
        }
        return finalBoard.unvisitedCellSum() * drawNumbers[drawIndex]
    }
}

class Board(private val grid: List<List<Cell>>) {
    fun unvisitedCellSum(): Int {
        return grid.flatten().filter { ! it.visited }.sumOf { it.number }
    }

    fun isWinner(): Boolean {
        return grid.indices.any { isVisitedInRow(it) } || grid[0].indices.any { isVisitedInColumn(it) }
    }

    fun isVisitedInRow(row: Int): Boolean {
        val cells = grid[row]
        return cells.all { it.visited }
    }

    fun isVisitedInColumn(column: Int): Boolean {
        return grid.all { it[column].visited }
    }

    fun visitForNumber(number: Int) {
        for (cells in grid) {
            for (cell in cells) {
                if (cell.number == number) {
                    cell.visit()
                }
            }
        }
    }
}

class Cell(public val number: Int) {
    var visited: Boolean = false

    fun visit() {
        visited = true
    }
}


