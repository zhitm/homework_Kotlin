package homeworks.hw4.task1

fun main(){
    val list = List(100000){it}.shuffled()
    val sorter = ParallelQuickSort(list, 4)
    sorter.run()
//    println(sorter.list.toString())
    println(sorter.isSorted())
    println(sorter.timeElapsed)
    println("--------")
    val sorter2 = ParallelQuickSort(list, 1)
    sorter2.run()
//    println(sorter2.list.toString())
    println(sorter2.isSorted())
    println(sorter2.timeElapsed)
}