package at.thehuters

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue
import kotlin.math.roundToInt


class Advent7 {

    companion object {
        const val DAY="6"
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile)
    }


    private fun advent6(linesOfFile: List<String>) {
        val startFish = linesOfFile[0]
            .split(",")
            .map { s -> Integer.valueOf(s) }
        startFish
            .distinct()
            .map { line -> Pair(line, startFish.fold(0){ acc, i -> acc + (i-line).absoluteValue}) }
            .minByOrNull { pair ->
            pair.second
        }?.let {
            println("$it")
        }
    }

}



