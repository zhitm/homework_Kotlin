package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool

class GraphicCreator {
    fun createMapForGraphic(threadsCount: Int): MutableMap<String, Any> {
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
        return mutableMapOf(
            "$threadsCount" to List(SIZE) { "$threadsCount" },
            "x$threadsCount" to xs,
            "y$threadsCount" to ys
        )
    }

    companion object PARAMS {
        const val STEP = 10000
        const val SIZE = 50
        const val REPEATS = 15
    }
}
