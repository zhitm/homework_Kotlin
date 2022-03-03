package homeworks.hw1.task3

class PerformedCommandStorage(private var list: MutableList<Int>) {
    fun getList(): MutableList<Int> = list
    var commandCounter = 0
    private val listOfCommands: MutableList<Command> = mutableListOf()
    fun addCommand(action: Action, args: IntArray) {
        listOfCommands.add(Command(action, args))
        list = action.doAction(list, args)
        commandCounter++
    }

    fun undoCommand() {
        if (commandCounter != 0) {
            val command = listOfCommands[commandCounter - 1]
            list = command.action.reverseAction(list, command.args)
            listOfCommands.removeAt(commandCounter - 1)
            commandCounter--
        }
    }
}
