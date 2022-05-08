package homeworks.hw4.task1.qsort

interface Sort<T : Comparable<T>> {
    fun sort(array: Array<T>, leftIndex: Int = 0, rightIndex: Int = array.size - 1)
}
