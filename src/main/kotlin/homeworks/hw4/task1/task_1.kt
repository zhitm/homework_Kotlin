package homeworks.hw4.task1

import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool

fun main() {
    println("Input number of threads:")
    val threadsNumber = readLine()?.toIntOrNull()
    if (threadsNumber == null) {
        println("it's not a number")
    } else {
        val executor = ForkJoinPool(threadsNumber)
        val mySorter = QSortWithThreads<Int>(executor)
        println("Input the length of the array")
        val length = readLine()?.toIntOrNull()
        if (length == null) {
            println("it's not a number")
            return
        }
        val array = Array(length) { it }
        for (index in 0 until length) {
            println("Input the next element")
            val element = readLine()?.toIntOrNull()
            if (element == null) {
                println("It's not a number")
                return
            }
            array[index] = element
        }
        mySorter.sort(array)
        println("Sorted array:")
        var arrayAsString = ""
        array.forEach { arrayAsString += "$it " }
        println(arrayAsString)
    }
}
