package at.thehuters

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue


class Advent7 {

    companion object {
        const val DAY="6"
    }

    val identity = { n: Int -> n }
    val sum = { n: Int -> n*(n+1)/2 }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile, identity)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile, identity)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile, sum)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile, sum)
    }


    private fun advent6(linesOfFile: List<String>, fuelCost: (Int) -> Int) {
        val startFish = linesOfFile[0]
            .split(",")
            .map { s -> s.toInt()}
        startFish
            .distinct()
            .map { position -> Pair(position, startFish.fold(0){ acc, i ->
                val n = (i-position).absoluteValue
                acc + fuelCost(n)
            }) }
            .minByOrNull { pair ->
            pair.second
        }?.let {
            println("$it")
        }
    }

}



