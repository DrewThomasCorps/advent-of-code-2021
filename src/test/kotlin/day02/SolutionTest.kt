package day02

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.io.File

internal class SolutionTest {

    private lateinit var solution: Solution

    @BeforeEach
    fun setUp() {
        val file = File({}.javaClass.getResource("test_input_1.txt")?.file ?: throw NullPointerException())
        val input = file.readLines()
        solution = Solution(input)
    }

    @Test
    fun partOne() {
        kotlin.test.assertEquals(1, solution.partOne())
    }

    @Test
    fun partTwo() {
        kotlin.test.assertEquals(1, solution.partTwo())
    }
}