package tests.retest.task1

interface ArithmeticAvailable<T : ArithmeticAvailable<T>> {
    operator fun minus(element: T): T
    operator fun plus(element: T): T
    operator fun times(element: T): T
    fun isNull(): Boolean
}
