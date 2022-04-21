package homeworks.hw3.task1

class AVLTreeMap<K : Comparable<K>, V> : MutableMap<K, V> {
    private val tree: Tree<K, V> = Tree()
    private val fileCreator: GraphvizFileCreator<K, V> = GraphvizFileCreator()
    override var size = 0
        private set
    override val entries = mutableSetOf<MutableMap.MutableEntry<K, V>>()
    override val keys = mutableSetOf<K>()
    override val values = mutableListOf<V>()
    override fun clear() {
        size = 0
        keys.clear()
        values.clear()
        entries.clear()
        tree.root = null
    }

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

    override fun remove(key: K): V? {
        val value = get(key)
        if (value != null) {
            tree.deleteNode(key)
            size--
            keys.remove(key)
            values.remove(value)
            entries.remove(Entry(key, value))
        }
        return value
    }

    override fun putAll(from: Map<out K, V>) {
        from.entries.forEach { put(it.key, it.value) }
    }

    override fun put(key: K, value: V): V? {
        val previousValue = get(key)
        tree.addNode(key, value)
        keys.add(key)
        values.add(value)
        entries.add(Entry(key, value))
        if (previousValue == null) {
            size++
        } else {
            values.remove(previousValue)
            entries.remove(Entry(key, previousValue))
        }
        return previousValue
    }

    fun isMapCorrect(): Boolean =
        tree.isTreeCorrect(tree.root) && keys.all { tree.hasKey(it) } && keys.size == size && values.size == size

    fun createDotFile(path: String) {
        fileCreator.createDotFile(path, tree.root)
    }
}
