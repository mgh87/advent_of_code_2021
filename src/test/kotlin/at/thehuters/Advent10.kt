package at.thehuters

import org.junit.jupiter.api.Test
import java.lang.RuntimeException
import java.util.*

class Advent10 {

    companion object {
        const val DAY = "10"
    }

    private fun advent10(linesOfFile: List<String>) {
        var sum = 0
        linesOfFile.forEach {
            val myStack = Stack<Char>()

            it.forEach { c ->
                when (c) {
                    '(', '[', '{', '<' -> myStack.push(c)
                    else -> {
                        if (myStack.pop().counterpart() != c) {
                            println("$c has valie ${c.value()}")
                            sum += c.value()
                        }
                    }
                }
            }
        }
        println("sum $sum")
    }

    private fun advent10_2(linesOfFile: List<String>) {
        val summes = mutableListOf<Long>()
        linesOfFile.forEach {
            val myStack = Stack<Char>()
            for (c in it) {
                when (c) {
                    '(', '[', '{', '<' -> myStack.push(c)
                    else -> {
                        if (myStack.pop().counterpart() != c) {
                            //println("$it is corrupted")
                            myStack.clear()
                            break
                        }
                    }
                }
            }
            if(myStack.isNotEmpty()){
                var sum = 0L
                while (myStack.isNotEmpty()){
                    sum=sum*5+myStack.pop().counterpart().closingValue()
                }

                println("sum $sum")
                summes.add(sum)
            }
        }
        println("median ${summes.med()}")

    }

    private fun List<Long>.med() = this.sorted().let {
        if (it.size % 2 == 0)
            (it[it.size / 2] + it[(it.size - 1) / 2]) / 2
        else
            it[it.size / 2]
    }

    private fun Char.counterpart(): Char {
        if (this == '(') return ')'
        if (this == '<') return '>'
        if (this == '[') return ']'
        if (this == '{') return '}'
        throw RuntimeException("No Counterpart")
    }

    private fun Char.value(): Int {
        if (this == ')') return 3
        if (this == ']') return 57
        if (this == '}') return 1197
        if (this == '>') return 25137
        throw RuntimeException("No Counterpart")
    }

    private fun Char.closingValue(): Int {
        if (this == ')') return 1
        if (this == ']') return 2
        if (this == '}') return 3
        if (this == '>') return 4
        throw RuntimeException("No Counterpart")
    }

    @Test
    fun advent_1_small() {
        val linesOfFile = readInput(DAY, "1")
        advent10(linesOfFile)
    }

    @Test
    fun advent_1_large() {
        val linesOfFile = readInput(DAY, "2")
        advent10(linesOfFile)
    }

    @Test
    fun advent_2_small() {
        val linesOfFile = readInput(DAY, "1")
        advent10_2(linesOfFile)
    }

    @Test
    fun advent_2_large() {
        val linesOfFile = readInput(DAY, "2")
        advent10_2(linesOfFile)
    }

}






