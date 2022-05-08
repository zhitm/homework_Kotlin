package homeworks.hw4.task1

import homeworks.hw4.task1.benchmark.ArrayGenerator
import homeworks.hw4.task1.qsort.QSort
import homeworks.hw4.task1.qsort.QSortWithThreads
import java.util.concurrent.ForkJoinPool
import kotlin.system.measureTimeMillis

fun main() {
    val arrayGenerator = ArrayGenerator()
    val executor = ForkJoinPool.commonPool()
    val mySorter = QSortWithThreads<Int>(executor)
    val usualSorter = QSort<Int>()
    val n = 1000000
    val arr = arrayGenerator.getRandomArray(n)

    var arr1: Array<Int>
    var arr2: Array<Int>
    var summaryTimeParallel: Long = 0
    var summaryTimeUsual: Long = 0
    val repeats = 100
    for (i in 0 until repeats) {
        arr1 = arrayGenerator.arrayCopy(arr)
        arr2 = arrayGenerator.arrayCopy(arr)
        val time1 = measureTimeMillis { mySorter.sort(arr1) }
        println(time1)
        val time2 = measureTimeMillis { usualSorter.sort(arr2) }
        println(time2)
        summaryTimeParallel += time1
        summaryTimeUsual += time2
        println("------")
    }
    println("Medium:")
    println(summaryTimeParallel / repeats)
    println(summaryTimeUsual / repeats)
}
