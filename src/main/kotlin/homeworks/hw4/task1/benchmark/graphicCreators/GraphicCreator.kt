package homeworks.hw4.task1.benchmark.graphicCreators

import homeworks.hw4.task1.benchmark.ArrayGenerator
import homeworks.hw4.task1.benchmark.Benchmark
import homeworks.hw4.task1.qsort.Sort

class GraphicCreator(private val sorter: Sort<Int>, private val name: String, private val index: Int) {
    fun createMapForGraphic(): MutableMap<String, Any> {
        val xs = List(SIZE) { it * STEP }
        val benchmark = Benchmark()
        val arrayGenerator = ArrayGenerator()
        val ys = xs.map {
            benchmark.measureAverageTimeMillis(
                { array -> sorter.sort(array) },
                arrayGenerator.getRandomArray(it), REPEATS
            )
        }
        return mutableMapOf(name to List(ThreadsGraphicCreator.SIZE) { name }, "x$index" to xs, "y$index" to ys)
    }

    companion object PARAMS {
        const val STEP = 1000
        const val SIZE = 50
        const val REPEATS = 15
    }
}