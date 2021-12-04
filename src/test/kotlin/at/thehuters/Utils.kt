package at.thehuters

import java.io.File

fun readInput(day: String, name: String) = File("src/test/resources/$day", "$name.txt").readLines()
