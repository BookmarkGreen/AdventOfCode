import Entity.IncreaseDecreaseCheck
import Entity.IndividualSortedLineEntity
import Entity.SortedLineMapEntity
import java.io.File


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    day2Part1Function()
}

fun day2Part1Function() {
    val initialLines = getLines("src/input/input.txt")
    val safeTotal = calculateSafeTotal(initialLines)
    println(initialLines)
    println("Part 1 Answer: $safeTotal")
}

fun getLines(fileName: String) : SortedLineMapEntity {
    val arrayOfLines = SortedLineMapEntity()
    val readLines = File(fileName).useLines {
        it.toList()
    }
    for (line in readLines){
        val splitLine = line.split(" ").map {
            it.toInt()
        }
        val lineEntity = IndividualSortedLineEntity(
            isIncreaseDecreaseOrSame = findIncreaseOrDecrease(splitLine),
            line = splitLine.toMutableList()
        )
        arrayOfLines.lines.add(lineEntity)
    }
    return arrayOfLines
}

fun findIncreaseOrDecrease(lineItems: List<Int>) : IncreaseDecreaseCheck{
    return if(findRepeatingNumber(lineItems)){
        IncreaseDecreaseCheck.SAME
    }
    else if(lineItems[0] > lineItems[1])
        IncreaseDecreaseCheck.DECREASE
    else
        IncreaseDecreaseCheck.INCREASE

}

fun findRepeatingNumber(lineItems: List<Int>) : Boolean{
    for(lineNumber in lineItems){
        if(lineItems.count { it == lineNumber} > 1){
            return true
        }
    }
    return false
}

fun calculateSafeTotal(sortedLines: SortedLineMapEntity) : Int{
    var totalSafeCount = 0
    var lineSafeCount = 0
    for(lines in sortedLines.lines){
            for(i in 0..< lines.line.size - 1){
                //Pass down first initial value, current element, next element, and the enum for same, increase or decrease
                    if (getDifferenceOf3(lines, i)) {
                        lineSafeCount += 1
                    }
                }
        if(lines.line.count() - 1 == lineSafeCount){
            totalSafeCount += 1
        }
        lineSafeCount = 0
    }
    return totalSafeCount
}

fun getDifferenceOf3(lines: IndividualSortedLineEntity, index: Int) : Boolean{
    val initialNumber = lines.line[0]
    val firstCompareNumber = lines.line[index]
    val secondCompareNumber = lines.line[index + 1]

    fun greaterThanThreeAndLessThanZero(a: Int, b: Int): Boolean{
        return (b - a in 1..3)
    }

    fun lessThanThreeAndGreaterThanZero(a: Int, b: Int): Boolean{
        return (a - b in 1..3)
    }

    if(lines.isIncreaseDecreaseOrSame == IncreaseDecreaseCheck.INCREASE){
        //Compare to first value since every other value will always need to be greater or less than it
        if(initialNumber < secondCompareNumber) {
            if (firstCompareNumber < secondCompareNumber && greaterThanThreeAndLessThanZero(firstCompareNumber, secondCompareNumber)) {
                return true
            }
        }
    }
    else if(lines.isIncreaseDecreaseOrSame == IncreaseDecreaseCheck.DECREASE){
        if(initialNumber > secondCompareNumber) {
            if (firstCompareNumber > secondCompareNumber && lessThanThreeAndGreaterThanZero(firstCompareNumber, secondCompareNumber)) {
                return true
            }
        }
    }
    return false
}

