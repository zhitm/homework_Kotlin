package tests.retest.task1

@Suppress("MagicNumber")
fun main() {
    val vector1 = Vector(listOf(ArithmeticInt(1), ArithmeticInt(4), ArithmeticInt(0)))
    val vector2 = Vector(listOf(ArithmeticInt(2), ArithmeticInt(3), ArithmeticInt(0)))
    println("Vectors:")
    println(vector1.toString())
    println(vector2.toString())
    println("Sum:")
    println((vector1 + vector2).toString())
    println("Diff:")
    println((vector1 - vector2).toString())
    println("Scalar")
    println((vector1 * vector2).toString())
}
