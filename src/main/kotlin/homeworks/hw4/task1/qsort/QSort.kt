package homeworks.hw4.task1.qsort

class QSort : Sort {
    override fun sort(array: IntArray, leftIndex: Int, rightIndex: Int) {
        if (rightIndex <= leftIndex) return
        val newPartition = homeworks.hw4.task1.partition(array, leftIndex, rightIndex)
        sort(array, leftIndex, newPartition.leftEnd)
        sort(array, newPartition.rightStart, rightIndex)
    }
}
