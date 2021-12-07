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
            .map { s -> s.toInt()}
        startFish
            .distinct()
            .map { position -> Pair(position, startFish.fold(0){ acc, i ->
                val n = (i-position).absoluteValue
                acc + n*(n+1)/2
            }) }
            .minByOrNull { pair ->
            pair.second
        }?.let {
            println("$it")
        }
    }

}



