package tests.test1.task1

fun main() {
    val cmp = QueueStringComparator
    val queue = MyQueue<String, String>(cmp)
    println("Value - string, priority - string")
    while (true) {
        val enqueueArgsCount = 3
        val command = readLine() ?: return
        val splittedInput = command.split(" ")
        if (splittedInput.size == 1) {
            when (splittedInput[0]) {
                "roll" -> println(queue.roll())
                "peek" -> println(queue.peek())
                "remove" -> queue.remove()
                "exit" -> return
                else -> println("It's not a command")
            }
        } else if (splittedInput.size == enqueueArgsCount && splittedInput[0] == "enqueue") {
            queue.enqueue(splittedInput[1], splittedInput[2])
        } else println("It's not a command")
    }
}
