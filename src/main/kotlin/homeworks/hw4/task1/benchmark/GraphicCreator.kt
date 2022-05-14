package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool

class GraphicCreator {
    fun createMapForGraphic(threadsCount: Int): Map<String, Any> {
        val xs = List(SIZE) { it * STEP }
        val sorter = QSortWithThreads<Int>(ForkJoinPool(threadsCount))
        val benchmark = Benchmark()
        val arrayGenerator = ArrayGenerator()
        val ys = xs.map {
            benchmark.measureAverageTimeMillis(
                { array -> sorter.sort(array) },
                arrayGenerator.getRandomArray(it), REPEATS
            )
        }
        return mapOf<String, Any>("threads" to List(xs.size) { "$threadsCount" }, "x" to xs, "y" to ys)
    }

    companion object PARAMS {
        const val STEP = 10000
        const val SIZE = 50
        const val REPEATS = 15
    }
}
