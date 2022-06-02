package homeworks.hw4.task1.qsort

interface Sort {
    fun sort(array: IntArray, leftIndex: Int = 0, rightIndex: Int = array.size - 1)
}
