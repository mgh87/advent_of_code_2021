package at.thehuters

import org.junit.jupiter.api.Test
import kotlin.math.absoluteValue

typealias Range = ( List<Int> ) -> IntRange

class Advent7 {

    companion object {
        const val DAY="7"
    }

    private val identity = { n: Int -> n }
    private val range1 : Range = { crabs: List<Int>  ->
        val min = crabs.minOrNull()!!
        val max = crabs.maxOrNull()!!
        IntRange(min,max)
    }
    private val sum = { n: Int -> n*(n+1)/2 }
    private val range2 : Range = { crabs: List<Int>  ->
        val avgFloor = crabs.average().toInt()
        IntRange(avgFloor,avgFloor+1)
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent7(linesOfFile, identity, range1)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent7(linesOfFile, identity, range1)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent7(linesOfFile, sum, range2)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent7(linesOfFile, sum, range2)
    }

    private fun advent7(linesOfFile: List<String>, fuelCost: (Int) -> Int, range: Range) {
        val splitCrabs = linesOfFile[0]
            .split(",")
            .map { s -> s.toInt()}
        range(splitCrabs).map { position -> Pair(position, splitCrabs.fold(0){ acc, i ->
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



