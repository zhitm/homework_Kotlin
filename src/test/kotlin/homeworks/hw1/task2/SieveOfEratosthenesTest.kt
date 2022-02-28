package homeworks.hw1.task2

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class SieveOfEratosthenesTest {

    @Test
    fun `primes less or equals than 1`() {
        val sieve = SieveOfEratosthenes(1)
        assertEquals(listOf(), sieve.getPrimes())
    }

    @Test
    fun `primes less or equals than 5`() {
        val sieve = SieveOfEratosthenes(5)
        assertEquals(listOf(2, 3, 5), sieve.getPrimes())
    }

    @Test
    fun `primes less or equals than 7`() {
        val sieve = SieveOfEratosthenes(7)
        assertEquals(listOf(2, 3, 5, 7), sieve.getPrimes())
    }

    @Test
    fun `primes less or equals than 14`() {
        val sieve = SieveOfEratosthenes(14)
        assertEquals(listOf(2, 3, 5, 7, 11, 13), sieve.getPrimes())
    }

    @Test
    fun `primes less or equals than 21`() {
        val sieve = SieveOfEratosthenes(21)
        assertEquals(listOf(2, 3, 5, 7, 11, 13, 17, 19), sieve.getPrimes())
    }
}