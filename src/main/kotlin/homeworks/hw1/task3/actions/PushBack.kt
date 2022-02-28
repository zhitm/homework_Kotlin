package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

object PushBack : Action {
    override fun doAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        list.add(args[0])
        return list
    }

    override fun reverseAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        list.removeAt(list.size - 1)
        return list
    }
}
