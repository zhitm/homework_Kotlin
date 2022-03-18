package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class Push(private val value: Int) : Action {
    override fun doAction(list: MutableList<Int>) {
        list.add(0, value)
    }

    override fun reverseAction(list: MutableList<Int>) {
        list.removeFirst()
    }
}
