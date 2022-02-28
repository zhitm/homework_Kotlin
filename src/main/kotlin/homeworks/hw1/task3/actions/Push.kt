package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

object Push : Action {
    override fun doAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        list.add(0, args[0])
        return list
    }

    override fun reverseAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        list.removeAt(0)
        return list
    }
}
