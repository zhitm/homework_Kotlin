package homeworks.hw4.task1.benchmark

import kotlin.system.measureTimeMillis

class Benchmark {
    fun measureAverageTimeMillis(sort: (Array<Int>) -> (Unit), array: Array<Int>, repeats: Int = 1): Long {
        var summaryTime: Long = 0
        val arrayGenerator = ArrayGenerator()
        for (i in 0 until repeats) {
            val arrayToSort = arrayGenerator.arrayCopy(array)
            summaryTime += measureTimeMillis { sort(arrayToSort) }
        }
        return summaryTime / repeats
    }
}
