package homeworks.hw4.task1

import homeworks.hw4.task1.qsort.Partition
import java.util.*

fun IntArray.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun IntArray.isSorted(): Boolean {
    return (0 until this.lastIndex).all { this[it] <= this[it + 1] }
}
@Suppress("LoopWithTooManyJumpStatements")
fun partition(arr: IntArray, start: Int, end: Int): Partition {
    var i = start
    var j = end
    val pivot = (Random().nextInt(j - i) + i)
    arr.swap(j, pivot)
    j--
    while (i <= j) {
        if (arr[i] <= arr[end]) {
            i++
            continue
        }
        if (arr[j] >= arr[end]) {
            j--
            continue
        }
        arr.swap(i, j)
        j--
        i++
    }
    arr.swap(j + 1, end)
    return Partition(j, j + 2)
}
