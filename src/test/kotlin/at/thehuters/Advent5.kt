package at.thehuters

import org.junit.jupiter.api.Test
import java.io.File




class Advent5 {

    private fun listOfStrings(fileName: String): List<String> {
        return javaClass.classLoader.getResource(fileName)?.
        let { resource -> File(resource.file).useLines { it.toList() } }!!
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = listOfStrings("5/1-0.txt")
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = listOfStrings("5/1-1.txt")
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = listOfStrings("5/2-1.txt")
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = listOfStrings("5/2-2.txt")
    }
}