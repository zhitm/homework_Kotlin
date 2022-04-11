package homeworks.hw3.task1

import homeworks.hw1.task3.execute

fun addShuffledKeysFromRange(map: AVLTreeMap<Int, Int>, range: IntRange) {
    range.shuffled().forEach {
        map.add(it, 1)
    }
}


fun execute(command: String, map: AVLTreeMap<Int, Int>) {
    val splittedInput = command.split(" ")
    if (splittedInput.size <= 1 || (splittedInput[0] != "add" && splittedInput[0] != "del")) {
        println("It's not a command")
        return
    }
    when (splittedInput.size) {
        2 -> {
            val arg = splittedInput[1].toIntOrNull()
            if (arg != null) map.delete(arg)
        }
        3 -> {
            val firstArg = splittedInput[1].toIntOrNull()
            val secondArg = splittedInput[2].toIntOrNull()
            if (firstArg != null && secondArg != null) map.add(firstArg, secondArg)
        }
        4 -> {
            if (splittedInput[1] == "range") {
                val firstArg = splittedInput[2].toIntOrNull()
                val secondArg = splittedInput[3].toIntOrNull()
                if (firstArg != null && secondArg != null) addShuffledKeysFromRange(map, firstArg..secondArg)
            } else println("It's not a command")
        }
    }
}

fun main() {
    val map = AVLTreeMap<Int, Int>()
    var run = true
    while (run) {
        when (val command = readLine()) {
            "exit" -> {
                run = false
                map.drawKeyTree("src/main/CLI_tree.dot")
            }
            null -> continue
            else -> execute(command, map)
        }
    }
    map.drawKeyTree("src/main/tree.dot")
}