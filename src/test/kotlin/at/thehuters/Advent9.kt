package at.thehuters

import org.junit.jupiter.api.Test

class Advent9 {

    companion object {
        const val DAY = "9"
    }

    private fun advent9(linesOfFile: List<String>) {
        val yMax = linesOfFile.size-1
        val xMax = linesOfFile[0].length-1
        val coordinatesYX = linesOfFile.map { it.map { c -> Character.getNumericValue(c) }}
        var sum = 0
        coordinatesYX.forEachIndexed { yIndex, xList ->
            xList.forEachIndexed { xIndex, value ->
                val xLow = when(xIndex){
                    0 -> value < coordinatesYX[yIndex][xIndex+1]
                    xMax -> value < coordinatesYX[yIndex][xIndex-1]
                    else -> value < coordinatesYX[yIndex][xIndex+1] && value < coordinatesYX[yIndex][xIndex-1]
                }
                val yLow = when(yIndex){
                    0 -> value < coordinatesYX[yIndex+1][yIndex]
                    yMax -> value < coordinatesYX[yIndex-1][yIndex]
                    else -> value < coordinatesYX[yIndex+1][xIndex] && value < coordinatesYX[yIndex-1][xIndex]
                }
                if (xLow && yLow) {
                    sum+=1+value
                }
            }
        }
        println("The sum is $sum")
    }

    private fun advent9_2(linesOfFile: List<String>) {
        val yMax = linesOfFile.size
        val xMax = linesOfFile[0].length
        val coordinatesYX = linesOfFile.map { it.map { c -> Character.getNumericValue(c) } }
        val basins = mutableListOf<Int>()
        val cooardiantes = (0 until yMax * xMax).toMutableSet()
        while (cooardiantes.isNotEmpty()) {
            val start = cooardiantes.last()
            val x = start % xMax
            val y = (start - x) / xMax
            if(coordinatesYX[y][x] != 9){
                val basinSize = removeElements(yMax, xMax, x, y, cooardiantes, coordinatesYX)
                if (basinSize > 0) {
                    basins.add(basinSize)
                    println("basin size: $basinSize")
                }
            }
            else {
                cooardiantes.remove(start)
            }
        }
        basins.sortDescending()
        println("${basins[0]*basins[1]*basins[2]}")
    }

    private fun removeElements(yMax: Int, xMax: Int,
        x: Int, y: Int,
        cooardiantes: MutableSet<Int>, coordinatesYX: List<List<Int>>
    ) : Int {
        if( x < 0 || x >= xMax || y < 0 || y >= yMax ) return 0
        if( coordinatesYX[y][x] == 9 ) {
            return 0
        }
        if(!cooardiantes.remove(x+y*xMax)) return 0
        return 1 +
                removeElements(yMax, xMax, x-1, y, cooardiantes, coordinatesYX) +
                removeElements(yMax, xMax, x, y-1, cooardiantes, coordinatesYX) +
                removeElements(yMax, xMax, x+1, y, cooardiantes, coordinatesYX) +
                removeElements(yMax, xMax, x, y+1, cooardiantes, coordinatesYX)
    }


    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY, "1")
        advent9(linesOfFile)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY, "2")
        advent9(linesOfFile)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY, "1")
        advent9_2(linesOfFile)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent9_2(linesOfFile)
    }

}






