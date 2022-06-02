package tests.test2.task1

fun main() {
    println("Введите желаемую глубину поиска")
    val depth = readLine()?.toIntOrNull()
    println("Введите желаемое число корутин")
    val coroutinesCount = readLine()?.toIntOrNull() ?: 1
    if (coroutinesCount <= 0 || depth == null) {
        println("Некорректный ввод")
        return
    }
    println("Введите ссылку на статью, с которой начать, или ENTER, если со случайной")
    val randomUrl = "https://en.wikipedia.org/wiki/Special:Random"
    var startUrl = readLine() ?: randomUrl
    val parser = Parser()
    if (startUrl == "" || !parser.isUrlValid(startUrl)) {
        println("Выбрана случайная статья")
        startUrl = randomUrl
    }
    val graph = Graph("https://en.wikipedia.org/wiki/Adolf_Hitler", startUrl, depth, coroutinesCount)
    if (graph.dfs()) {
        println("Найден путь!")
    } else {
        println("Путь не найден")
    }
}
