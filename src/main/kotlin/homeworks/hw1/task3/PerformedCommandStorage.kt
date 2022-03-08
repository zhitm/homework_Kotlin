package homeworks.hw1.task3

class PerformedCommandStorage(val list: MutableList<Int>) {
    private val listOfCommands: MutableList<Action> = mutableListOf()
    fun addCommand(action: Action) {
        listOfCommands.add(action)
        action.doAction(list)
    }

    fun undoLastCommand() {
        require(listOfCommands.isNotEmpty()) { "No commands to undo" }
        val command = listOfCommands.last()
        command.reverseAction(list)
        listOfCommands.removeLast()
    }
}
