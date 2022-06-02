package homeworks.hw4.task1.qsort

import kotlinx.coroutines.*

class QSortWithCoroutines(private val maxCoroutinesCount: Int) : Sort {
    private val jobs = mutableListOf<Deferred<Unit>>()
    override fun sort(array: IntArray, leftIndex: Int, rightIndex: Int) {
        runBlocking {
            coroutinesSort(array, leftIndex, rightIndex)
        }
    }

    private suspend fun coroutinesSort(array: IntArray, leftIndex: Int, rightIndex: Int) = coroutineScope {
        if (rightIndex <= leftIndex) return@coroutineScope
        val newPartition = homeworks.hw4.task1.partition(array, leftIndex, rightIndex)
        if (jobs.size < maxCoroutinesCount) {
            val sortLeft = async { sort(array, leftIndex, newPartition.leftEnd) }
            jobs.add(sortLeft)
        } else {
            sort(array, leftIndex, newPartition.leftEnd)
        }
        if (jobs.size < maxCoroutinesCount) {
            val sortRight = async { sort(array, newPartition.rightStart, rightIndex) }
            jobs.add(sortRight)
        } else {
            sort(array, newPartition.rightStart, rightIndex)
        }
    }
}
