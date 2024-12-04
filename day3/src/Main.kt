import java.io.File

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
fun main() {
    day3Function()
}

fun day3Function(){
    val initialLines = getLines("src/input/input.txt", isPart2 = false)
    val mathTotal = calculateMultiplication(initialLines)
    println("Day 3 Part 1 Answer: $mathTotal")
    val getPart2 = getLines("src/input/input.txt", isPart2 = true)
    val part2MathTotal = calculateMultiplication(getPart2)
    println("Day 3 Part 2 Answer: $part2MathTotal")
}
fun getLines(fileName: String, isPart2 : Boolean) : MutableList<String> {
    val regexCheck = """mul\(\d+,\d+\)""".toRegex()
    val regexArray: MutableList<String> = mutableListOf()
    val readLines = File(fileName).useLines {
        it.toList()
    }
    if(isPart2){
        val secondRegexCheck = """(mul\(\d{1,3},\d{1,3}\))|(do\(\))|(don't\(\))""".toRegex()
        var getCheck = true
        for(line in readLines){
            secondRegexCheck.findAll(line).toMutableList().forEach{
                when (it.value) {
                    "do()" -> {
                        getCheck = true
                    }
                    "don't()" -> {
                        getCheck = false
                    }
                    else -> {
                        if (getCheck) {
                            regexArray.add(it.value)
                        }
                    }
                }
            }
        }
        return regexArray
    }
    else {
        for (line in readLines) {
            regexCheck.findAll(line).toMutableList().forEach {
                regexArray.add(it.value)
            }
        }
        return regexArray
    }
    return mutableListOf()
}
fun calculateMultiplication(list: MutableList<String>) : Int{
    var mathTotal = 0
    val regex = "\\((\\d+(?:,\\d+)*)\\)".toRegex()
    for(mathProblem in list){
        val matchResult = regex.find(mathProblem)
        val numbersString = matchResult?.groupValues?.get(1)
        val nums = numbersString?.split(",")?.map { it.toInt() }
        mathTotal += (nums?.get(0) ?: 0) * (nums?.get(1) ?: 0)
    }
    return mathTotal
}