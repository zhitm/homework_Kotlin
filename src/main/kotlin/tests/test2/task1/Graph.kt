package tests.test2.task1

class Graph(private val urlToFind: String, startUrl: String, private val depth: Int) {
    private val root = Node(startUrl)
    var path: MutableList<String> = mutableListOf()

    init {
        require(depth > 0) { "Глубина поиска положительна" }
    }

    var leafs: MutableList<Node> = mutableListOf(root)
    fun dfs(): Boolean {
        for (currentDepth in 1..depth) {
            println("Search at depth $currentDepth")
            val newLeafs = mutableListOf<Node>()
            leafs.forEach {
                it.addChildren()
                newLeafs += it.children
            }
            leafs = newLeafs
            println("Leafs: ${leafs.size}")
            val node = leafs.find { it.url == urlToFind }
            if (node != null) {
                path = node.getPath()
                println(path.joinToString(" "))
                println("on depth $currentDepth")
                return true
            }
        }
        return false
    }
}