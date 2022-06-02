package tests.test2.task1

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking

class Graph(private val urlToFind: String, startUrl: String, private val depth: Int, private val coroutinesCount: Int) {
    private val root = Node(startUrl)
    private var path: MutableList<String> = mutableListOf()

    init {
        require(depth > 0) { "Глубина поиска положительна" }
    }

    var leafs: MutableList<Node> = mutableListOf(root)

    @Suppress("MagicNumber")
    fun dfs(): Boolean = runBlocking {
        for (currentDepth in 1..depth) {
            val jobs = mutableListOf<Deferred<Unit>>()
            println("Поиск на глубине $currentDepth начат")
            val newLeafs = mutableListOf<Node>()
            leafs.forEachIndexed { index, node ->
                if (jobs.size < coroutinesCount)
                    jobs.add(async { node.addChildren() })
                else {
                    jobs.removeFirst().await()
                    jobs.add(async { node.addChildren() })
                }
                if (index % 25 == 0) {
                    println("$index/${leafs.size} просмотрено на глубине $currentDepth")
                }
            }
            jobs.forEach { it.await() }
            leafs.forEach { newLeafs += it.children }
            leafs = newLeafs
            println("Статей предстоит просмотреть: ${leafs.size}")
            val node = leafs.find { it.url == urlToFind }
            if (node != null) {
                path = node.getPath()
                printPath()
                return@runBlocking true
            }
        }
        return@runBlocking false
    }

    private fun printPath() {
        println("Путь:")
        println(path.joinToString(" -> "))
    }
}
