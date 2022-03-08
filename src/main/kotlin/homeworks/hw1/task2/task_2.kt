package homeworks.hw1.task2

fun main() {
    println("Input an integer number bigger than zero:")
    val input = readLine()
    if (input.isNullOrEmpty()) {
        println("Empty string is not a number")
        return
    }
    val n = try {
        input.toInt()
    } catch (e: NumberFormatException) {
        println("It's not an integer number")
        return
    }
    val primes = sieve(n)
    if (primes.isEmpty()) {
        println("No primes before your number")
    } else print("Primes before your number: ")
    println(primes.joinToString(" "))
}
