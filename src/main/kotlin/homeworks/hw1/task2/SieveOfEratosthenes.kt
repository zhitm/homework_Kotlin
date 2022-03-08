package homeworks.hw1.task2

fun sieve(sieveSize: Int): MutableList<Int> {
    require(sieveSize > 0) { "Size of sieve should be more than 0" }

    var isPrime = BooleanArray(sieveSize) { true }
    val primes: MutableList<Int> = mutableListOf()

    for (i in 1 until sieveSize) {
        if (isPrime[i]) {
            primes.add(i + 1)
            isPrime = isPrime.mapIndexed { index, value -> ((index + 1) % (i + 1) != 0 && value) }.toBooleanArray()
        }
    }
    return primes
}
