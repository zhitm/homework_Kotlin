package homeworks.hw5.task2

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class MatrixTest {
    @ParameterizedTest
    @MethodSource("dataForTimesTest")
    fun `test times`(matrix1: Matrix, matrix2: Matrix, result: Matrix) {
        assertEquals((matrix1 * matrix2).toString(), result.toString())
    }

    companion object {
        @JvmStatic
        fun dataForTimesTest() = listOf(
            Arguments.of(
                Matrix(arrayOf(intArrayOf(1, 2, 3))),
                Matrix(arrayOf(intArrayOf(3), intArrayOf(2), intArrayOf(1))),
                Matrix(arrayOf(intArrayOf(10)))
            ),
            Arguments.of(
                Matrix(arrayOf(intArrayOf(0, 0, 3), intArrayOf(2, 4, 5))),
                Matrix(arrayOf(intArrayOf(3, 0), intArrayOf(2, 0), intArrayOf(1, 0))),
                Matrix(arrayOf(intArrayOf(3, 0), intArrayOf(19, 0)))
            ),
            Arguments.of(
                Matrix(arrayOf(intArrayOf(1, 1), intArrayOf(1, 1))),
                Matrix(arrayOf(intArrayOf(1, 1), intArrayOf(1, 1))),
                Matrix(arrayOf(intArrayOf(2, 2), intArrayOf(2, 2)))
            )
        )
    }
}
