package homeworks.hw4.task1

class QuickSortThread(private val sorter : ParallelQuickSort, var array: MutableList<Int>) : Thread() {
    override fun run() {
        array = sorter.quickSort(array)
    }
}