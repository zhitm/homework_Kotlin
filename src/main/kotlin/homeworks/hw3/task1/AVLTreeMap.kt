package homeworks.hw3.task1

class AVLTreeMap<K : Comparable<K>, V> : Map<K, V> {
    private val tree: Tree<K, V> = Tree()
    private val fileCreator: GraphvizFileCreator<K, V> = GraphvizFileCreator()
    override var size = 0
    override val entries: MutableSet<Map.Entry<K, V>> = mutableSetOf()
    override val keys: MutableSet<K> = mutableSetOf()
    override val values: MutableList<V> = mutableListOf()

    override fun get(key: K): V? {
        return tree.get(key)?.value
    }

    override fun containsKey(key: K): Boolean {
        return keys.contains(key)
    }

    override fun containsValue(value: V): Boolean {
        return values.contains(value)
    }

    override fun isEmpty(): Boolean {
        return size == 0
    }

    fun add(key: K, value: V) {
        tree.addNode(key, value)
        keys.add(key)
        values.add(value)
//        entries.add(key to value)
        size++
    }

    fun delete(key: K) {
        val value = get(key)
        tree.deleteNode(key)
        size--
        keys.remove(key)
        values.remove(value)
//        entries.remove(key to value)
    }

    fun isMapCorrect(): Boolean =
        tree.isTreeCorrect(tree.root) && keys.all { tree.hasKey(it) } && keys.size == size && values.size == size

    fun createDotFile(path: String) {
        fileCreator.createDotFile(path, tree.root)
    }
}
