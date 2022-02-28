package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

object ChangePosition : Action {
    override fun doAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        val fromIndex = args[0]
        val toIndex = args[1]
        val value = list[fromIndex]
        list.removeAt(fromIndex)
        list.add(toIndex, value)
        return list
    }

    override fun reverseAction(list: MutableList<Int>, args: IntArray): MutableList<Int> =
        doAction(list, args.reversedArray())
}
