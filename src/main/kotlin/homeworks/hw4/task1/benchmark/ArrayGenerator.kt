package homeworks.hw4.task1.benchmark
@Suppress("ArrayPrimitive")
class ArrayGenerator {
    fun getRandomArray(size: Int): Array<Int> {
        val list = MutableList(size) { it }.shuffled()
        return Array(size) { list[it] }
    }

    inline fun <reified T> arrayCopy(array: Array<T>): Array<T> {
        return Array(array.size) { array[it] }
    }
}
