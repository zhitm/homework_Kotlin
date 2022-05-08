package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool

class GraphicCreator {
    fun createMapForGraphic(threadsCount: Int): Map<String, Any> {
        val xs = List(30) { it * 4000 }
        val sorter = QSortWithThreads<Int>(ForkJoinPool(threadsCount))
        val benchmark = Benchmark()
        val arrayGenerator = ArrayGenerator()
        val ys = xs.map {
            benchmark.measureAverageTimeMillis(
                { array -> sorter.sort(array) },
                arrayGenerator.getRandomArray(it), 15
            )
        }
        return mapOf<String, Any>("x" to xs, "y" to ys)
    }
}
