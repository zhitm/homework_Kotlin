package homeworks.hw4.task1.qsort

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

class QSortWithThreads(private val executorService: ForkJoinPool) : Sort {
    override fun sort(array: IntArray, leftIndex: Int, rightIndex: Int) {
        executorService.invoke(QuickSortAction(array))
    }

    private class QuickSortAction(
        private val array: IntArray,
        private val leftIndex: Int = 0,
        private val rightIndex: Int = array.size - 1
    ) : RecursiveAction() {

        override fun compute() {
            if (rightIndex <= leftIndex) return
            val newPartition = homeworks.hw4.task1.partition(array, leftIndex, rightIndex)
            val task1 = QuickSortAction(array, leftIndex, newPartition.leftEnd)
            val task2 = QuickSortAction(array, newPartition.rightStart, rightIndex)
            task1.fork()
            task2.compute()
            task1.join()
        }
    }
}
