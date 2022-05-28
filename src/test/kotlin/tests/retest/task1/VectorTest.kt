package tests.retest.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertFailsWith

class VectorTest {
    @ParameterizedTest
    @MethodSource("dataForPlus")
    fun `test plus`(
        vector1: Vector<ArithmeticInt>,
        vector2: Vector<ArithmeticInt>,
        sum: Vector<ArithmeticInt>
    ) {
        assertEquals(sum.coordinates.map { it.value }, (vector1 + vector2).coordinates.map { it.value })
    }

    @ParameterizedTest
    @MethodSource("dataForMinus")
    fun `test minus`(
        listForVector1: List<ArithmeticInt>,
        listForVector2: List<ArithmeticInt>,
        listForDiff: List<ArithmeticInt>
    ) {
        val vector1 = Vector(listForVector1)
        val vector2 = Vector(listForVector2)
        assertEquals(listForDiff.map { it.value }, (vector1 - vector2).coordinates.map { it.value })
    }

    @ParameterizedTest
    @MethodSource("dataForScalar")
    fun `test times`(
        listForVector1: List<ArithmeticInt>,
        listForVector2: List<ArithmeticInt>,
        scalar: ArithmeticInt
    ) {
        val vector1 = Vector(listForVector1)
        val vector2 = Vector(listForVector2)
        assertEquals(scalar.value, (vector1 * vector2).value)
    }

    @Test
    fun `test null vector`() {
        val vector = Vector(listOf(ArithmeticInt(0), ArithmeticInt(0), ArithmeticInt(0)))
        assertEquals(true, vector.isNull())
    }

    @Test
    fun `test non-null vector`() {
        val vector = Vector(listOf(ArithmeticInt(2), ArithmeticInt(3), ArithmeticInt(0)))
        assertEquals(false, vector.isNull())
    }

    @Test
    fun `test different sizes`() {
        val vector1 = Vector(listOf(ArithmeticInt(1)))
        val vector2 = Vector(listOf(ArithmeticInt(2), ArithmeticInt(5)))
        val exception = assertFailsWith<IllegalArgumentException> { vector1 + vector2 }
        assertEquals("Vectors should be same size", exception.message)
    }

    companion object {
        @JvmStatic
        fun dataForPlus() = listOf(
            Arguments.of(
                Vector(listOf(ArithmeticInt(1), ArithmeticInt(4), ArithmeticInt(0))),
                Vector(listOf(ArithmeticInt(2), ArithmeticInt(3), ArithmeticInt(0))),
                Vector(listOf(ArithmeticInt(3), ArithmeticInt(7), ArithmeticInt(0)))
            )
        )

        @JvmStatic
        fun dataForMinus() = listOf(
            Arguments.of(
                listOf(ArithmeticInt(1), ArithmeticInt(4), ArithmeticInt(0)),
                listOf(ArithmeticInt(2), ArithmeticInt(3), ArithmeticInt(0)),
                listOf(ArithmeticInt(-1), ArithmeticInt(1), ArithmeticInt(0))
            )
        )

        @JvmStatic
        fun dataForScalar() = listOf(
            Arguments.of(
                listOf(ArithmeticInt(1), ArithmeticInt(4), ArithmeticInt(0)),
                listOf(ArithmeticInt(2), ArithmeticInt(3), ArithmeticInt(0)),
                ArithmeticInt(14)
            )
        )
    }
}
