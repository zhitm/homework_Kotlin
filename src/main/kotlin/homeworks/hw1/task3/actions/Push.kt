package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class Push(override val args: IntArray) : Action {
    override fun doAction(list: MutableList<Int>) {
        list.add(0, args[0])
    }

    override fun reverseAction(list: MutableList<Int>) {
        list.removeAt(0)
    }
}
