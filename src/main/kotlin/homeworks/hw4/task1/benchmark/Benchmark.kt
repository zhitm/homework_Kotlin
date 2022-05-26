package homeworks.hw4.task1.benchmark

import kotlin.system.measureTimeMillis

class Benchmark {
    fun measureAverageTimeMillis(sort: (IntArray) -> (Unit), array: IntArray, repeats: Int = 1): Double {
        var summaryTime: Long = 0
        val arrayGenerator = ArrayGenerator()
        repeat(repeats) {
            val arrayToSort = arrayGenerator.arrayCopy(array)
            summaryTime += measureTimeMillis { sort(arrayToSort) }
        }
        return summaryTime * 1.0 / repeats
    }
}
