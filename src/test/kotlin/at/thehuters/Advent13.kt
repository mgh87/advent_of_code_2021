package at.thehuters

import org.junit.jupiter.api.Test
import java.lang.RuntimeException

class Advent13 {

    companion object {
        const val DAY = "13"
    }

    data class Point(val x: Int, val y: Int)

    private fun advent13(linesOfFile: List<String>) {
        var coordinates = mutableSetOf<Point>()
        var lastX = 0; var lastY = 0; var i = 0
        for (line in linesOfFile) {
            if (line.isEmpty()) continue
            else if (line.startsWith("fold", true)) {
                line.drop(11).split("=").let { i ->
                    if (i[0] == "x") {
                        lastX = Integer.valueOf(i[1])
                        coordinates = foldOnX(coordinates, lastX)
                    }
                    else if (i[0] == "y") {
                        lastY = Integer.valueOf(i[1])
                        coordinates = foldOnY(coordinates, lastY)
                    } else {
                        throw RuntimeException()
                    }
                }
                println("[${line.drop(11)}]; Number of dots after ${++i} folds: ${coordinates.count()}")

            } else {
                coordinates.add(line.split(",").map { s -> Integer.valueOf(s) }.let { i ->
                    Point(
                        i[0],
                        i[1]
                    )
                })
            }
        }
        for (i in 0 until lastY){
            for (j in 0 until lastX){
                if(coordinates.contains(Point(j,i))){
                    print("*")
                }
                else print(" ")
            }
            print("\n")
        }
        //coordinates.forEach { println("x ${it.x}, y ${it.y}") }

    }

    private fun foldOnY(coordinates: MutableSet<Point>, edge: Int) : MutableSet<Point> {
        return coordinates.filter { it.y != edge }.map {
            if(it.y >= edge) {
                Point(it.x, it.y - (it.y - edge)*2)
            }
            else {
                it
            }
        }.toMutableSet()
    }

    private fun foldOnX(coordinates: MutableSet<Point>, edge: Int) : MutableSet<Point> {
        return coordinates.filter { it.x != edge }.map {
            if(it.x >= edge) {
                Point(it.x - (it.x - edge)*2, it.y)
            }
            else {
                it
            }
        }.toMutableSet()
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY, "1")
        advent13(linesOfFile)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY, "2")
        advent13(linesOfFile)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY, "1")
        advent13(linesOfFile)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent13(linesOfFile)
    }

}



