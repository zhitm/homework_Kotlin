package homeworks.hw1.task2

import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class SieveOfEratosthenesTest {

    @ParameterizedTest(name = "test")
    @MethodSource("inputDataForSieve")
    fun `test correct sieve`(expected: List<Int>, argument: Int) {
        val primes = sieve(argument)
        assertEquals(expected, primes)
    }

    @ParameterizedTest(name = "test fail")
    @MethodSource("inputDataForFail")
    fun `test fail with not positive number as argument`(msg: String, value: Int) {
        val exception = assertFailsWith<IllegalArgumentException> { sieve(value) }
        assertEquals(exception.message, msg)
    }

    companion object {
        @JvmStatic
        fun inputDataForSieve() = listOf(
            Arguments.of(emptyList<Int>(), 1),
            Arguments.of(listOf(2), 2),
            Arguments.of(listOf(2, 3), 3),
            Arguments.of(listOf(2, 3, 5, 7, 11), 11),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13), 16),
            Arguments.of(listOf(2, 3, 5, 7, 11, 13, 17, 19), 22),
        )

        @JvmStatic
        val msg: String = "Size of sieve should be more than 0"

        @JvmStatic
        fun inputDataForFail() =
            listOf(Arguments.of(msg, 0), Arguments.of(msg, -4), Arguments.of(msg, -10))
    }
}
