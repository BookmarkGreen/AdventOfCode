package Entity

data class IndividualSortedLineEntity(
    var isIncreaseDecreaseOrSame : IncreaseDecreaseCheck? = IncreaseDecreaseCheck.SAME,
    val line : MutableList<Int> = mutableListOf(),
)
