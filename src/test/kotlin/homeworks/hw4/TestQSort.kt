package homeworks.hw4

import homeworks.hw4.task1.benchmark.ArrayGenerator
import homeworks.hw4.task1.isSorted
import homeworks.hw4.task1.qsort.QSort
import homeworks.hw4.task1.qsort.QSortWithCoroutines
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource

class TestQSort {
    private val usualSorter = QSort()

    @ParameterizedTest
    @MethodSource("dataForSorts")
    fun `test coroutines sort`(array: IntArray, coroutinesCount: Int) {
        val coroutinesSorter = QSortWithCoroutines(coroutinesCount)
        coroutinesSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    @ParameterizedTest
    @MethodSource("dataForUsualSort")
    fun `test usual sort`(array: IntArray) {
        usualSorter.sort(array)
        assertEquals(true, array.isSorted())
    }

    companion object {
        private val arrayGenerator = ArrayGenerator()

        @JvmStatic
        fun dataForSorts() = listOf(
            Arguments.of(intArrayOf(1), 1),
            Arguments.of(intArrayOf(1, 2), 2),
            Arguments.of(intArrayOf(1, 5, 2, 4, 3), 52),
            Arguments.of(arrayGenerator.getRandomArray(1000), 23),
            Arguments.of(arrayGenerator.getRandomArray(10000), 123),
            Arguments.of(arrayGenerator.getRandomArray(100000), 4),
            Arguments.of(arrayGenerator.getRandomArray(1000000), 30)
        )

        @JvmStatic
        fun dataForUsualSort() = listOf(
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
