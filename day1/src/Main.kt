import Entity.SortedLineEntity
import java.io.File

fun main() {
    day1Function()
}

fun day1Function(){
    val initialLines = getLines("src/input/input.txt")
    val distance = getDistance(initialLines)
    val similarityScore = getSimilarity(initialLines)
    println("Part 1 Answer: $distance")
    println("Part 2 Answer: $similarityScore")

}

fun getLines(fileName: String) : SortedLineEntity {
    val array1 : MutableList<Int> = mutableListOf()
    val array2 : MutableList<Int> = mutableListOf()
    val readLines = File(fileName).useLines {
        it.toList()
    }
    for (line in readLines){
        val splitLine = line.split("   ")
        array1.add(splitLine[0].toInt())
        array2.add(splitLine[1].toInt())
    }
    return SortedLineEntity(array1.sorted(), array2.sorted())
}

fun getDistance(sortedLines : SortedLineEntity) : Int {
    var distance = 0
    sortedLines.secondArray.forEachIndexed{ index, element ->
        val firstArrayValue = sortedLines.firstArray[index]
        distance += if(element > firstArrayValue)
            element - firstArrayValue
        else firstArrayValue - element
    }
    return distance
}

fun getSimilarity(sortedLines : SortedLineEntity) : Int {
    var similarityScore = 0

    sortedLines.firstArray.forEach{ element ->
        if(sortedLines.secondArray.count{ it == element} > 0){
            similarityScore += element * sortedLines.secondArray.count{ it == element }
        }
    }

    return similarityScore
}
