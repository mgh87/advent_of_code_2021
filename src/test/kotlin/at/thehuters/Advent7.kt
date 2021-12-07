package at.thehuters

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue


class Advent7 {

    companion object {
        const val DAY="7"
    }

    private val identity = { n: Int -> n }
    private val sum = { n: Int -> n*(n+1)/2 }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent7(linesOfFile, identity)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent7(linesOfFile, identity)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent7(linesOfFile, sum)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent7(linesOfFile, sum)
    }


    private fun advent7(linesOfFile: List<String>, fuelCost: (Int) -> Int) {
        val splitCrabs = linesOfFile[0]
            .split(",")
            .map { s -> s.toInt()}
        val min = splitCrabs.minOrNull()!!
        val max = splitCrabs.maxOrNull()!!
        IntRange(min,max).map { position -> Pair(position, splitCrabs.fold(0){ acc, i ->
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



