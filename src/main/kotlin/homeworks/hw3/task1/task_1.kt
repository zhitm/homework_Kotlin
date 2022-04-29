package homeworks.hw3.task1

fun addShuffledKeysFromRange(map: AVLTreeMap<Int, Int>, range: IntRange) = range.shuffled().forEach {
    map[it] = 1
}

@Suppress("MagicNumber")
fun execute(command: String, map: AVLTreeMap<Int, Int>) {
    val splittedInput = command.split(" ")
    if (splittedInput.size <= 1 || (splittedInput[0] != "add" && splittedInput[0] != "del")) {
        println("It's not a command")
        return
    }
    when (splittedInput.size) {
        2 -> {
            val arg = splittedInput[1].toIntOrNull()
            if (arg != null) {
                map.remove(arg)
            }
        }
        3 -> {
            val firstArg = splittedInput[1].toIntOrNull()
            val secondArg = splittedInput[2].toIntOrNull()
            if (firstArg != null && secondArg != null) {
                map[firstArg] = secondArg
            }
        }
        4 -> {
            if (splittedInput[1] == "range") {
                val firstArg = splittedInput[2].toIntOrNull()
                val secondArg = splittedInput[3].toIntOrNull()
                if (firstArg != null && secondArg != null) {
                    addShuffledKeysFromRange(map, firstArg..secondArg)
                }
            } else {
                println("It's not a command")
            }
        }
    }
}

fun main() {
    val map = AVLTreeMap<Int, Int>()
    println(
        """commands:
        |exit
        |add key value
        |add range minkey maxkey
        |del key
    """.trimMargin()
    )
    while (true) {
        when (val command = readLine()) {
            "exit" -> {
                break
            }
            else -> command?.let { execute(it, map) }
        }
    }
    map.toDot("src/main/kotlin/homeworks/hw3/task1/tree.dot")
}
