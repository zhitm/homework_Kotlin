package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool

class GraphicCreator {
    @Suppress("MagicNumber")
    fun createMapForGraphic(threadsCount: Int): Map<String, Any> {
        val xs = List(50) { it * 10000 }
        val sorter = QSortWithThreads<Int>(ForkJoinPool(threadsCount))
        val benchmark = Benchmark()
        val arrayGenerator = ArrayGenerator()
        val ys = xs.map {
            benchmark.measureAverageTimeMillis(
                { array -> sorter.sort(array) },
                arrayGenerator.getRandomArray(it), 15
            )
        }
        return mapOf<String, Any>("threads" to List(xs.size) { "$threadsCount" }, "x" to xs, "y" to ys)
    }
}
