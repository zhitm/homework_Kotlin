package homeworks.hw4.task1

import java.util.concurrent.ForkJoinPool
import java.util.concurrent.RecursiveAction

class QSortThreadPool<T : Comparable<T>>(private val executorService: ForkJoinPool) {
    fun sort(list: MutableList<T>) {
        executorService.invoke(QSort(list))
    }

    private class QSort<T : Comparable<T>>(
        private val array: MutableList<T>,
        private val leftIndex: Int = 0,
        private val rightIndex: Int = array.size - 1
    ) : RecursiveAction() {


        private fun partition(list: MutableList<T>, leftIndex: Int, rightIndex: Int): Partition {
            var newLeftPartEndIndex = leftIndex - 1
            val middleValue = list[rightIndex]
            for (i in leftIndex..rightIndex) {
                if (list[i] < middleValue) {
                    newLeftPartEndIndex++
                    list.swap(newLeftPartEndIndex, i)
                }
            }
            var newRightPartStartIndex = newLeftPartEndIndex + 1
            for (i in newRightPartStartIndex..rightIndex) {
                if (list[i] == middleValue) {
                    list.swap(newRightPartStartIndex, i)
                    newRightPartStartIndex++
                }
            }
            return Partition(newLeftPartEndIndex, newRightPartStartIndex)
        }

        override fun compute() {
            if (rightIndex <= leftIndex) return
            val newPartition = partition(array, leftIndex, rightIndex)
            invokeAll(QSort(array, leftIndex, newPartition.leftEnd), QSort(array, newPartition.rightStart, rightIndex))
        }
    }
}
