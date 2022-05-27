package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithThreads
import jetbrains.letsPlot.geom.geomSmooth
import jetbrains.letsPlot.intern.Plot
import jetbrains.letsPlot.letsPlot
import java.util.concurrent.ForkJoinPool

class GraphicCreator {
    fun createMapForGraphic(threadsCount: Int): MutableMap<String, Any> {
        println("$threadsCount graphic is started computing")
        val xs = List(SIZE) { it * STEP }
        val sorter = QSortWithThreads(ForkJoinPool(threadsCount))
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

    fun getPlot(threadCount: Int): Plot {
        val graphicCreator = GraphicCreator()
        return letsPlot(graphicCreator.createMapForGraphic(threadCount)) + geomSmooth(
            method = "loess",
            se = false
        ) { x = "x$threadCount"; y = "y$threadCount" }
    }

    companion object PARAMS {
        const val STEP = 10000
        const val SIZE = 30
        const val REPEATS = 8
    }
}
