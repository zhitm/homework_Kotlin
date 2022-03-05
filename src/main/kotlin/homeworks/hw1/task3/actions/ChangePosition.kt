package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class ChangePosition(override val args: IntArray) : Action {
    private fun changePositions(list: MutableList<Int>, fromIndex: Int, toIndex: Int): MutableList<Int> {
        val value = list[fromIndex]
        require(fromIndex in 0 until list.size && toIndex in 0 until list.size) {
            "Indices should be at least 0 and less than size of list"
        }
        list.removeAt(fromIndex)
        list.add(toIndex, value)
        return list
    }

    override fun doAction(list: MutableList<Int>) {
        changePositions(list, args[0], args[1])
    }

    override fun reverseAction(list: MutableList<Int>) {
        changePositions(list, args[1], args[0])
    }
}
