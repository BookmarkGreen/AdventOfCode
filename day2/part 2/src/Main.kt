import Entity.IndividualSortedLineEntity
import Entity.SortedLineMapEntity
import java.io.File
import kotlin.math.abs


fun main() {
    day2Part2Function()
}

fun day2Part2Function() {
    val initialLines = getLines("src/input/input.txt")
    val part2Answer = initialLines.lines.count { filterForDuplicates(it.line) }
    println("Part 2 Answer: $part2Answer")
}

fun getLines(fileName: String): SortedLineMapEntity {
    val arrayOfLines = SortedLineMapEntity()
    val readLines = File(fileName).useLines {
        it.toList()
    }
    for (line in readLines) {
        val splitLine = line.split(" ").map {
            it.toInt()
        }
        val lineEntity = IndividualSortedLineEntity(
            line = splitLine.toMutableList()
        )
        arrayOfLines.lines.add(lineEntity)
    }
    return arrayOfLines
}

fun findDifferences(numbers: List<Int>): Boolean {
    val findDifferenceInNumbers = numbers.zipWithNext()
    return findDifferenceInNumbers.all { (a, b) -> a - b in 1..3 || findDifferenceInNumbers.all { (a, b) -> b - a in 1..3 } }
}

fun filterForDuplicates(numbers: List<Int>): Boolean {
    return numbers.indices
        .map { checkIfNumberExists -> numbers.filterIndexed { index, _ -> index != checkIfNumberExists } }
        .any { findDifferences(it) }
}
