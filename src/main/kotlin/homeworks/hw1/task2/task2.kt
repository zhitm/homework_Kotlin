package homeworks.hw1.task2

fun main() {
    println("Input an integer number bigger than zero:")
    val n = readLine()?.toInt()
    val sieve = n?.let { SieveOfEratosthenes(it) }
    println("Primes before your number: ")
    sieve?.printPrimes()
}
