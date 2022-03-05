package homeworks.hw1.task3

import homeworks.hw1.task3.actions.ChangePosition
import homeworks.hw1.task3.actions.Push
import homeworks.hw1.task3.actions.PushBack

fun execute(command: String, storage: PerformedCommandStorage) {
    val splittedInput = command.split(" ")
    if (splittedInput[0] == "add") {
        val args = splittedInput.subList(2, splittedInput.size).map { it.toInt() }.toIntArray()
        when (splittedInput[1]) {
            "push" -> storage.addCommand(Push(args))
            "pushBack" -> storage.addCommand(PushBack(args))
            "changePosition" -> storage.addCommand(ChangePosition(args))
        }
    } else println("It's not a command")
}

fun main() {
    println("commands: exit, add (name of command: push, pushBack or changePosition) (args), undo")
    println("Example: add push 100")
    println("Input an array:")
    var list: MutableList<Int> = mutableListOf()
    val str = readLine()
    if (str != null) list = str.split(" ").map { it.toInt() } as MutableList<Int>
    val storage = PerformedCommandStorage(list)
    var run = true
    while (run) {
        println("Input a command:")
        val command = readLine()
        if (command == "exit") run = false
        else if (command == null) continue
        else if (command == "undo") storage.undoLastCommand()
        else execute(command, storage)
        println("Current state: ${storage.list.joinToString(" ")}")
    }
}
