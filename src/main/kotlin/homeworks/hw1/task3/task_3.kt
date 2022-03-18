package homeworks.hw1.task3

import homeworks.hw1.task3.actions.ChangePosition
import homeworks.hw1.task3.actions.Push
import homeworks.hw1.task3.actions.PushBack

fun execute(command: String, storage: PerformedCommandStorage) {
    if (command == "undo") {
        storage.undoLastCommand()
        return
    }
    val splittedInput = command.split(" ")
    if (splittedInput.isNotEmpty() && splittedInput[0] == "add") {
        val args: IntArray = try {
            splittedInput.subList(2, splittedInput.size).map { it.toInt() }.toIntArray()
        } catch (e: NumberFormatException) {
            println("It's not an array of numbers so it can't be arguments")
            return
        }
        require(splittedInput.size >= 2) { "Missed name of command" }
        when (splittedInput[1]) {
            "push" -> if (args.size == 1) storage.addCommand(Push(args[0]))
            "pushBack" -> if (args.size == 1) storage.addCommand(PushBack(args[0]))
            "changePosition" -> if (args.size == 2) storage.addCommand(ChangePosition(args[0], args[1]))
            else -> println("No such command name")
        }
    } else {
        println("It's not a command")
    }
}

fun main() {
    println("commands: exit, add (name of command: push, pushBack or changePosition) (args), undo")
    println("Example: add push 100")
    println("Input an array:")
    var list: MutableList<Int> = mutableListOf()
    val str = readLine()
    if (str != null)
        try {
            list = str.split(" ").map { it.toInt() } as MutableList<Int>
        } catch (e: NumberFormatException) {
            println("It's not an array of numbers")
            return
        }

    val storage = PerformedCommandStorage(list)
    var run = true
    while (run) {
        println("Input a command:")
        val command = readLine()
        when (command) {
            "exit" -> run = false
            null -> continue
            else -> execute(command, storage)
        }
        println("Current state: ${storage.currentState.joinToString(" ")}")
    }
}
