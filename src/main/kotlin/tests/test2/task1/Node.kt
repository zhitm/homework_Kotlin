package tests.test2.task1

class Node(val url: String, private val parent: Node? = null) {
    private val isRoot = parent == null
    val children: MutableList<Node> = mutableListOf()

    fun addChildren() {
        val parser = Parser()
        parser.getAllWikiUrls(url).forEach { children.add(Node(it, this)) }
    }

    fun getPath(): MutableList<String> = parent?.let {
        val path = parent.getPath()
        path.add(url)
        path
    } ?: mutableListOf(url)
}