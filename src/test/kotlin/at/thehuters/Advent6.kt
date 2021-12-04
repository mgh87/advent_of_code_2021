package at.thehuters

import org.junit.jupiter.api.Test
import java.io.File




class Advent6 {

    companion object {
        const val DAY="6"
    }

    private fun listOfStrings(fileName: String): List<String> {
        return javaClass.classLoader.getResource(fileName)?.
        let { resource -> File(resource.file).useLines { it.toList() } }!!
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = listOfStrings("$DAY/1-0.txt")
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = listOfStrings("$DAY/1-1.txt")
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = listOfStrings("$DAY/2-1.txt")
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = listOfStrings("$DAY/2-2.txt")
    }
}


