package at.thehuters

import org.junit.jupiter.api.Test
import java.util.function.BiPredicate
import kotlin.math.abs

class Advent5 {

    companion object {
        const val DAY="5"
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY,"1")
        advent_5(linesOfFile) { start, stop -> start.x == stop.x || start.y == stop.y }
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY,"2")
        advent_5(linesOfFile) { start, stop -> start.x == stop.x || start.y == stop.y }
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY,"1")
        advent_5(linesOfFile) { start, stop -> start.x == stop.x || start.y == stop.y }
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY,"2")
        advent_5(linesOfFile) { start, stop -> start.x == stop.x || start.y == stop.y || abs(start.x-stop.y) == abs(start.y-stop.x) || abs(start.x-stop.x) == abs(start.y-stop.y)  }
    }

    data class Point(val x: Int, val y: Int)

    private fun advent_5(linesOfFile: List<String>, fissureCondition: BiPredicate<Point,Point>) {
        val coordinates = linesOfFile.map { it.split(" -> ").map { it.split(",").map { s -> Integer.valueOf(s) } }.map { i -> Point(i[0],i[1]) } }
        val fisurePointsMap = mutableMapOf<Point,Int>()
        for ((left, right) in coordinates) {
            //println("x1 ${left.x}, y1 ${left.y}, x2 ${right.x}, y2 ${right.y}")
            if (fissureCondition.test(left,right)){
                fissure(left, right).forEach{
                    val currentSize = fisurePointsMap.getOrDefault(it, 0)
                    fisurePointsMap[it] = currentSize+1
                }
            }
        }
        println("lines " + fisurePointsMap.filter { e -> e.value > 1 }.count())
        return
    }

    private fun fissure(start: Point, stop: Point): Collection<Point> {
        val fissurePoints = mutableListOf<Point>()
        val xDir = compareValues(stop.x,start.x)
        val yDir = compareValues(stop.y,start.y)
        var iterPoint = start.copy();
        while (iterPoint != stop) {
            fissurePoints.add(iterPoint)
            iterPoint = iterPoint.copy(x = iterPoint.x+xDir, y = iterPoint.y+yDir)
        }
        fissurePoints.add(iterPoint)
        return fissurePoints
    }
}


