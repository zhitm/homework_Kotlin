
package homeworks.hw4.task1.qsort

import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class QSortWithCoroutines: Sort {
    override fun sort(array: IntArray, leftIndex: Int, rightIndex: Int) {
        runBlocking {
            coroutineSort(array, leftIndex, rightIndex)
        }
    }
    private suspend fun coroutineSort(array: IntArray, leftIndex: Int, rightIndex: Int){
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
