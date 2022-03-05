package homeworks.hw1.task3

class PerformedCommandStorage(val list: MutableList<Int>) {
    private var commandCounter = 0
    private val listOfCommands: MutableList<Action> = mutableListOf()
    fun addCommand(action: Action) {
        listOfCommands.add(action)
        action.doAction(list)
        commandCounter++
    }

    fun undoLastCommand() {
        if (commandCounter != 0) {
            val command = listOfCommands.last()
            command.reverseAction(list)
            listOfCommands.removeLast()
            commandCounter--
        } else throw IllegalStateException("No commands to undo.")
    }
}
