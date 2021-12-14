package at.thehuters

import org.junit.jupiter.api.Test
import java.lang.RuntimeException

class Advent14 {

    companion object {
        const val DAY = "14"
    }

    data class Reaction(val input: String, val output: String)

    private fun advent14(linesOfFile: List<String>, steps: Int) {
        val muLinesOfFile = linesOfFile.toMutableList()
        var input = muLinesOfFile.removeFirst()
        muLinesOfFile.removeFirst()
        val reactions = muLinesOfFile.map { line -> line.split(" -> ") }.map { Reaction(it[0],it[1]) }.toSet()
        for (i in 0 until steps){
            input = input.windowed(2,1).map {
                reactions.find{ r -> r.input == it }?.let { rFind ->
                    rFind.output+rFind.input[1]
                } ?: it
            }.fold(input.first().toString()) { acc, c -> acc.plus(c) }
            println("After ${i+1} steps: $input")
        }
     
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY, "1")
        advent14(linesOfFile,10)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY, "2")
        advent14(linesOfFile,10)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY, "1")
        advent14(linesOfFile,10)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent14(linesOfFile,10)
    }

}



