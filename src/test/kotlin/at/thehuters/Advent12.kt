package at.thehuters

import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import java.util.*


data class Edge(val v1: String, val v2: String)

class Advent12 {

    companion object {
        const val DAY = "12"
    }

    private fun advent11(linesOfFile: List<String>) {
        val edges = linesOfFile.map {
            it.split("-").let { i -> setOf(Edge(i[0], i[1]),Edge(i[1], i[0])) }
        }.flatten().toSet()
        val startingPaths = edges.filter { edge -> edge.v1 == "start" }.map {
            val path = mutableListOf<String>()
            path.add(it.v1)
            path.add(it.v2)
            val stack = Stack<List<String>>()
            stack.push(path)
            stack
        }
        val finalPaths = mutableListOf<List<String>>()
        startingPaths.forEach { stack ->
            while (stack.isNotEmpty()){
                val path = stack.pop()
                edges.filter { it.v1 == path.last()}.forEach { edge ->
                    if (edge.v2 == "end") {
                        val newPath = path.toMutableList()
                        newPath.add(edge.v2)
                        finalPaths.add(newPath)
                    }
                    else if (edge.v2.isUpperCase() || !path.contains(edge.v2)){
                        val newPath = path.toMutableList()
                        newPath.add(edge.v2)
                        stack.push(newPath)
                    }
                }
            }

        }
        println(finalPaths.count())
        finalPaths.forEach { println(it.reduce { acc, b -> acc.path(b) }) }
    }

    data class Path(val vertices: List<String>, val dualSmallFree: Boolean)

    private fun advent11_2(linesOfFile: List<String>) {
        val edges = linesOfFile.map {
            it.split("-").let { i -> setOf(Edge(i[0], i[1]),Edge(i[1], i[0])) }
        }.flatten().toSet()
        val startingPaths = edges.filter { edge -> edge.v1 == "start" }.map {
            val path = mutableListOf<String>()
            path.add(it.v1)
            path.add(it.v2)
            val stack = Stack<Path>()
            stack.push(Path(path,true))
            stack
        }
        val finalPaths = mutableListOf<List<String>>()
        startingPaths.forEach { stack ->
            while (stack.isNotEmpty()){
                val path = stack.pop()
                edges.filter { it.v1 == path.vertices.last()}.forEach { edge ->
                    if (edge.v2 == "end") {
                        val newPath = path.vertices.toMutableList()
                        newPath.add(edge.v2)
                        finalPaths.add(newPath)
                    }
                    else if (edge.v2 != "start" &&
                        (edge.v2.isUpperCase()
                                || path.vertices.count { s-> s == edge.v2 } < 2 && path.dualSmallFree
                                || !path.vertices.contains(edge.v2))){
                        val newPath = path.vertices.toMutableList()
                        val dualSmall = path.dualSmallFree && !(path.vertices.contains(edge.v2) && !edge.v2.isUpperCase())
                        newPath.add(edge.v2)
                        stack.push(Path(newPath, dualSmall))
                    }
                }
            }

        }
        println(finalPaths.count())
        //finalPaths.forEach { println(it.reduce { acc, b -> acc.path(b) }) }
    }

    private fun String.path(b: String): String {
        return "$this,$b"
    }


    private fun String.isUpperCase(): Boolean {
        return this.map { it.isUpperCase() }.reduce { acc, b -> acc && b }
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY, "1")
        advent11(linesOfFile)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY, "2")
        advent11(linesOfFile)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY, "1")
        advent11_2(linesOfFile)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent11_2(linesOfFile)
    }

}






