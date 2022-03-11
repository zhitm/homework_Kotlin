package homeworks.hw1.task3.actions

import homeworks.hw1.task3.Action

class ChangePosition(private val fromIndex: Int, private val toIndex: Int) : Action {
    private fun changePositions(list: MutableList<Int>, fromIndex: Int, toIndex: Int): MutableList<Int> {
        require(fromIndex in list.indices && toIndex in list.indices) {
            "Indices should be at least 0 and less than size of list"
        }
        val value = list[fromIndex]
        list.removeAt(fromIndex)
        list.add(toIndex, value)
        return list
    }

    override fun doAction(list: MutableList<Int>) {
        changePositions(list, fromIndex, toIndex)
    }

    override fun reverseAction(list: MutableList<Int>) {
        changePositions(list, toIndex, fromIndex)
    }
}
