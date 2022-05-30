package homeworks.hw4

import homeworks.hw4.task1.benchmark.ArrayGenerator
import homeworks.hw4.task1.isSorted
import homeworks.hw4.task1.qsort.QSort
import homeworks.hw4.task1.qsort.QSortWithThreads
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.concurrent.ForkJoinPool

class TestQSort {
    private val usualSorter = QSort()

    @ParameterizedTest
    @MethodSource("dataForSorts")
    fun `test parallel sort on 1 thread`(array: IntArray) {
        val executor = ForkJoinPool(1)
        val parallelSorter = QSortWithThreads(executor)
        parallelSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    @ParameterizedTest
    @MethodSource("dataForSorts")
    fun `test parallel sort on 2 threads`(array: IntArray) {
        val executor = ForkJoinPool(2)
        val parallelSorter = QSortWithThreads(executor)
        parallelSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    @ParameterizedTest
    @MethodSource("dataForSorts")
    fun `test parallel sort on 239 threads`(array: IntArray) {
        val executor = ForkJoinPool(239)
        val parallelSorter = QSortWithThreads(executor)
        parallelSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    @ParameterizedTest
    @MethodSource("dataForSorts")
    fun `test usual sort`(array: IntArray) {
        usualSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    companion object {
        private val arrayGenerator = ArrayGenerator()

        @JvmStatic
        fun dataForSorts() = listOf(
            Arguments.of(intArrayOf(1)),
            Arguments.of(intArrayOf(1, 2)),
            Arguments.of(intArrayOf(1, 5, 2, 4, 3)),
            Arguments.of(arrayGenerator.getRandomArray(1000)),
            Arguments.of(arrayGenerator.getRandomArray(10000)),
            Arguments.of(arrayGenerator.getRandomArray(100000)),
            Arguments.of(arrayGenerator.getRandomArray(1000000))
        )
    }
}
