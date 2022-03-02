package homeworks.hw1.task2

class SieveOfEratosthenes(private val size: Int) {
    private var isPrime = BooleanArray(size) { true }
    private val primes: MutableList<Int> = mutableListOf()

    init {
        require(size > 0)
        isPrime[0] = false
        sieve()
    }

    private fun sieve() {
        for (i in 1 until size)
            if (isPrime[i]) {
                primes.add(i + 1)
                isPrime = isPrime.mapIndexed { index, value -> ((index + 1) % (i + 1) != 0 && value) }.toBooleanArray()
            }
    }

    fun getPrimes(): MutableList<Int> {
        return primes
    }
}
