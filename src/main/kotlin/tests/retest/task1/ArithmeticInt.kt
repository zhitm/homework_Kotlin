package tests.retest.task1

class ArithmeticInt(val value: Int) : ArithmeticAvailable<ArithmeticInt> {
    override fun plus(element: ArithmeticInt): ArithmeticInt = ArithmeticInt(value + element.value)

    override fun minus(element: ArithmeticInt): ArithmeticInt = ArithmeticInt(value - element.value)

    override fun times(element: ArithmeticInt): ArithmeticInt = ArithmeticInt(value * element.value)

    override fun isNull(): Boolean = value == 0

    override fun toString(): String = value.toString()
}
