package homeworks.hw4.task1

import java.util.Collections.max
import java.util.Collections.min
import java.util.concurrent.Executor
import java.util.concurrent.Executors
import kotlin.system.measureTimeMillis

class ParallelQuickSort(var list: List<Int>, var workingThreadsCount: Int = 4) {
    private val size = list.size
    private val threads: MutableList<Thread> = mutableListOf()
    private fun partition(partsCount: Int): Array<MutableList<Int>> {
        val partSize: Int = size / partsCount
        val parts: Array<MutableList<Int>> = Array(partsCount) { mutableListOf() }
        for (i in 0..partsCount - 2) parts[i] = list.slice(partSize * i until partSize * (i + 1)) as MutableList<Int>
        parts[partsCount - 1] = list.slice(partSize * (partsCount - 1) until list.size) as MutableList<Int>
        return parts
    }

    var timeElapsed = 0L
        private set

    private fun merge(lhs: MutableList<Int>, rhs: MutableList<Int>): List<Int> {
        val result = mutableListOf<Int>()
        while (lhs.isNotEmpty() && rhs.isNotEmpty()) {
            if (lhs.first() >= rhs.first()) {
                result.add(rhs.first())
                rhs.removeFirst()
            } else if (lhs.first() < rhs.first()) {
                result.add(lhs.first())
                lhs.removeFirst()
            }

        }
        result += lhs
        result += rhs
        return result
    }

    fun isSorted(): Boolean = (0 until list.lastIndex).all { list[it] <= list[it + 1] }


    fun quickSort(array: MutableList<Int>): MutableList<Int> {
        if (array.size == 1) return array
        val middleValue = (max(array) + min(array)) / 2
        var leftArray = array.filter { it <= middleValue }
        var rightArray = array.filter { it > middleValue }
        leftArray = quickSort(leftArray as MutableList<Int>)
        rightArray = quickSort(rightArray as MutableList<Int>)
        quickSort(rightArray)
        return (leftArray + rightArray).toMutableList()
    }

    fun run() {
        timeElapsed = measureTimeMillis {
            val partition = partition(workingThreadsCount)
            val executor = Executors.newFixedThreadPool(workingThreadsCount)
            for (threadNumber in 0 until workingThreadsCount) {
                executor.execute {
                    println("started")
                    partition[threadNumber] = quickSort(partition[threadNumber])
                }
            }


            list = mutableListOf()
            partition.forEach { list = merge(list as MutableList<Int>, it) }
        }
    }
}
