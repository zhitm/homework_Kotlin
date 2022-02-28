package homeworks.hw1.task2

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals

class SieveOfEratosthenesTest {

    @ParameterizedTest(name = "test")
    @MethodSource("inputData")
    fun `test correct sieve`(expected: List<Int>, argument: Int) {
        val sieve = SieveOfEratosthenes(argument)
        assertEquals(expected, sieve.getPrimes())
    }

    companion object {
        @JvmStatic
        fun inputData() = listOf(
            Arguments.of(emptyList<Int>(), 1),
            Arguments.of(listOf(2), 2),
            Arguments.of(listOf(2, 3), 3),
            Arguments.of(listOf(2, 3, 5, 7, 11), 11),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13), 16),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13, 17, 19), 22),
        )
    }
}
