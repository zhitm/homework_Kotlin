package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

object ChangePosition : Action {
    override fun doAction(list: MutableList<Int>, args: IntArray): MutableList<Int> {
        val fromIndex = args[0]
        val toIndex = args[1]
        val value = list[fromIndex]
        if (fromIndex !in 0 until list.size || toIndex !in 0 until list.size)
            throw IllegalArgumentException("It's ArrayIndexOutOfBoundsException")
        list.removeAt(fromIndex)
        list.add(toIndex, value)
        return list
    }

    override fun reverseAction(list: MutableList<Int>, args: IntArray): MutableList<Int> =
        doAction(list, args.reversedArray())
}
