
package homeworks.hw4.task1.qsort

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QSortCoroutines<T : Comparable<T>> : Sort<T> {
    override fun sort(array: Array<T>, leftIndex: Int, rightIndex: Int) {
        runBlocking {
            if (rightIndex <= leftIndex) return@runBlocking
            val newPartition = homeworks.hw4.task1.partition(array, leftIndex, rightIndex)
            val sortLeft = launch { sort(array, leftIndex, newPartition.leftEnd) }
            sortLeft.join()
            val sortRight = launch { sort(array, newPartition.rightStart, rightIndex) }
            sortRight.join()
        }
    }
}
