package at.thehuters

import org.junit.jupiter.api.Test

class Advent6 {

    companion object {
        const val DAY="6"
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1-0")
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"1-1")
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"2-1")
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2-2")
    }
}


