package hw1.`1`

class Sieve(private val size: Int) {
    private val isPrime = Array(size) { true }
    private val primes: MutableList<Int> = mutableListOf()

    init {
        isPrime[0] = false
        sieve()
    }

    private fun sieve() {
        for (i in 1 until size)
            if (isPrime[i]) {
                primes.add(i + 1)
                var k = 2
                while ((i + 1) * k <= size) {
                    isPrime[(i + 1) * k - 1] = false
                    k++
                }
            }
    }

    fun getPrimes(): MutableList<Int> {
        return primes
    }

    fun printPrimes() {
        primes.forEach { i -> println(i) }
    }
}

fun main() {
    println("Input an integer number:")
    val n = readLine()?.toInt()
    val sieve = n?.let { Sieve(it) }
    println("Primes before your number: ")
    sieve?.printPrimes()
}
