package homeworks.hw1.task3

class PerformedCommandStorage(private val list: MutableList<Int>) {
    val currentState: MutableList<Int>
        get() = list
    private val listOfCommands: MutableList<Action> = mutableListOf()
    fun addCommand(action: Action) {
        listOfCommands.add(action)
        action.doAction(list)
    }

    fun undoLastCommand() {
        require(listOfCommands.isNotEmpty()) { "No commands to undo" }
        val command = listOfCommands.removeLast()
        command.reverseAction(list)
    }
}
