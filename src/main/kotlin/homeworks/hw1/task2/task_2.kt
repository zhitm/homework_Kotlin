package homeworks.hw1.task2

fun getPositiveIntegerOrNull(input: String?): Int? {
    val n = try {
        input?.toIntOrNull()
    } catch (e: NumberFormatException) {
        null
    }
    if (n == null || n <= 0) {
        return null
    }
    return n
}

fun main() {
    println("Input an integer number bigger than zero:")
    val input = readLine()
    val n = getPositiveIntegerOrNull(input)
    if (n == null) {
        println("It's not an positive integer")
        return
    }

    val primes = sieve(n)
    if (primes.isEmpty()) {
        println("No primes before your number")
    } else {
        print("Primes before your number: ")
    }
    println(primes.joinToString(" "))
}
