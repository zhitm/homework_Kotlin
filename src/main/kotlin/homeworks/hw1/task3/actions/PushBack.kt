package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class PushBack(override val args: IntArray) : Action {
    override fun doAction(list: MutableList<Int>) {
        list.add(args[0])
    }

    override fun reverseAction(list: MutableList<Int>) {
        list.removeLast()
    }
}
