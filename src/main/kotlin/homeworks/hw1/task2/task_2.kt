package homeworks.hw1.task2

fun isDigit(string: String): Boolean = string.all { it.isDigit() }

fun main() {
    println("Input an integer number bigger than zero:")
    val input = readLine()
    if (input == null || !isDigit(input)) throw IllegalArgumentException("It's not a number")
    val n: Int = input.toInt()
    val sieve = SieveOfEratosthenes(n)
    println("Primes before your number: ")
    if (sieve.getPrimes().size == 0) println("No primes before your number")
    sieve.getPrimes().forEach { i ->
        println(i)
    }
}
