package homeworks.hw1.task2

fun sieve(sieveSize: Int): List<Int> {
    require(sieveSize > 0) { "Size of sieve should be more than 0" }

    val isPrime = BooleanArray(sieveSize + 1) { true }
    isPrime.indices.forEach {
        if (it <= 1) {
            isPrime[it] = false
            return@forEach
        }
        for (compositeNumberIndex in it * it..sieveSize step it) {
            isPrime[compositeNumberIndex] = false
        }
    }
    return IntArray(sieveSize) { it + 1 }.filter { isPrime[it] }
}
