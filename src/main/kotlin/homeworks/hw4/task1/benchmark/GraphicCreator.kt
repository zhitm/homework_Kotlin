package homeworks.hw4.task1.benchmark

import homeworks.hw4.task1.qsort.QSortWithCoroutines
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

    private fun getCoroutinesGraphic(coroutinesCount: Int): MutableMap<String, Any> {
        println("Coroutines graphic is started computing")
        val xs = List(SIZE) { it * STEP }
        val sorter = QSortWithCoroutines(coroutinesCount)
        val benchmark = Benchmark()
        val arrayGenerator = ArrayGenerator()
        val ys = xs.map {
            benchmark.measureAverageTimeMillis(
                { array -> sorter.sort(array) },
                arrayGenerator.getRandomArray(it), REPEATS
            )
        }
        return mutableMapOf(
            "coroutines" to List(SIZE) { "coroutines" },
            "x" to xs,
            "y" to ys
        )
    }

    fun getCoroutinePlot(coroutinesCount: Int): Plot {
        val graphicCreator = GraphicCreator()
        return letsPlot(graphicCreator.getCoroutinesGraphic(coroutinesCount)) + geomSmooth(
            method = "loess",
            se = false
        ) { x = "x"; y = "y" }
    }

    fun getPlot(threadCount: Int): Plot {
        val graphicCreator = GraphicCreator()
        return letsPlot(graphicCreator.createMapForGraphic(threadCount)) + geomSmooth(
            method = "loess",
            se = false
        ) { x = "x$threadCount"; y = "y$threadCount" }
    }

    companion object PARAMS {
        const val STEP = 5000
        const val SIZE = 20
        const val REPEATS = 8
    }
}
