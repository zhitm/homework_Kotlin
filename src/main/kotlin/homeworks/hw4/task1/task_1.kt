package homeworks.hw4.task1

import java.util.concurrent.ForkJoinPool

fun main(){
    val executor = ForkJoinPool()
    val myPool = QSortThreadPool<Int>(executor)
    val list = mutableListOf(2,1,4,3,7,2342,2)
    myPool.sort(list)
    println(list)
}
