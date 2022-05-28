package homeworks.hw1.task3

interface Action {
    fun doAction(list: MutableList<Int>)
    fun reverseAction(list: MutableList<Int>)
}
