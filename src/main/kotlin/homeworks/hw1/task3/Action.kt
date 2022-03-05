package homeworks.hw1.task3

interface Action {
    val args: IntArray
    fun doAction(list: MutableList<Int>)
    fun reverseAction(list: MutableList<Int>)
}
