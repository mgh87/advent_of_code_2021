package at.thehuters

import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import kotlin.math.roundToInt
import kotlin.math.roundToLong

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
            //println("After ${i+1} steps: $input")
            println("After ${i+1} steps")
        }
        val groupedByLetter = input.groupBy { it }
        val counts = groupedByLetter.map { entry -> entry.value.count() }
        println(counts.maxOrNull()!!-counts.minOrNull()!!)
    }

    private fun advent14_2(linesOfFile: List<String>, steps: Int) {
        val muLinesOfFile = linesOfFile.toMutableList()
        var input = muLinesOfFile.removeFirst()
        muLinesOfFile.removeFirst()
        val reactions = muLinesOfFile.map { line -> line.split(" -> ") }.map { Reaction(it[0],it[1]) }.toSet()
        var inputPaarMap = input.windowed(2,1).groupBy { it }.entries.associate { it.key to it.value.count().toLong() }
        for (i in 0 until steps){
            val outputMap = mutableMapOf<String,Long>()
            inputPaarMap.forEach{
                val reaction = reactions.find { reaction -> reaction.input == it.key }!!
                outputMap[it.key[0]+reaction.output] = (outputMap[it.key[0]+reaction.output] ?: 0) + it.value
                outputMap[reaction.output+it.key[1]] = (outputMap[reaction.output+it.key[1]] ?: 0) +it.value
            }
            inputPaarMap = outputMap
            //println("After ${i+1} steps: $input")
            println("After ${i+1} steps")
        }
        val listPairs = inputPaarMap.map {
            val result = mutableListOf<Pair<Char,Long>>()
            result.add(Pair(it.key[0],it.value))
            result.add(Pair(it.key[1],it.value))
            result
        }.flatten()
        val resultList = mutableListOf<Pair<Char,Long>>()
        for (i in listPairs){
            val newPair = resultList.find { i.first == it.first }?.let {
                resultList.removeAt(resultList.indexOf(it))
                Pair(
                    i.first,
                    it.second + i.second
                )
            } ?: Pair(
                i.first,
                i.second
            )
            resultList.add(newPair)
        }
        resultList.forEach {
            (it.second.toDouble()/2).roundToInt()
            println("$it : ${it.second/2} : ${(it.second.toDouble()/2).roundToLong()}")
        }
        val counts = resultList.map { (it.second.toDouble()/2).roundToLong() }
        println(counts.maxOrNull()!!-counts.minOrNull()!!)
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
        advent14_2(linesOfFile,40)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent14_2(linesOfFile,40)
    }

}



