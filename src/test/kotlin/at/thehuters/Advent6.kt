package at.thehuters

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test


class Advent6 {

    companion object {
        const val DAY="6"
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile, 79)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile, 79)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent6(linesOfFile, 255)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent6(linesOfFile, 256)
    }


    private fun advent6(linesOfFile: List<String>, days: Int) {
        val startFish = linesOfFile[0].split(",").map { s -> Integer.valueOf(s) }
            var i = 0L
            startFish.forEach {
                val p = numberOfFish(days)
                print("$p,")
                i+=p
            }
        print(i)


    }
    val cache = mutableMapOf<Int, Long>()

    fun fish(days: Int): Long {
        return cache[days] ?: (1 + (1..(days / 7)).sumOf { fish(days - (7 * it) - 2) }).also { cache[days] = it }
    }

    private fun numberOfFish(day: Int) : Int {
        return if(day > 6){
            numberOfFish(day - 9)+numberOfFish(day - 7 )
        }  else {
            1
        }
    }

}



