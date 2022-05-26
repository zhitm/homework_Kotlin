package homeworks.hw4.task1.benchmark
class ArrayGenerator {
    fun getRandomArray(size: Int): IntArray {
        val list = MutableList(size) { it }.shuffled()
        return Array(size) { list[it] }.toIntArray()
    }

    fun arrayCopy(array: IntArray): IntArray {
        return Array(array.size) { array[it] }.toIntArray()
    }
}
