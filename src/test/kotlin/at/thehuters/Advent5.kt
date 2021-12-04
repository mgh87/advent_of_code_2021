package at.thehuters

import org.junit.jupiter.api.Test
import java.io.File




class Advent5 {

    @Test
    fun advent_1() {
        val resourceName = "advent5-1.txt"
        val linesOfFile =javaClass.classLoader.getResource(resourceName)?.let { resource -> File(resource.file).useLines { it.toList() } }

        linesOfFile?.forEach {
            println(it)
        }
    }
}