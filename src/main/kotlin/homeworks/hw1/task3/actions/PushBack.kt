package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class PushBack(private val value: Int) : Action {
    override fun doAction(list: MutableList<Int>) {
        list.add(value)
    }

    override fun reverseAction(list: MutableList<Int>) {
        list.removeLast()
    }
}
