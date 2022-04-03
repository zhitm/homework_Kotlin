package homeworks.hw3.task1

class AVLTreeMap<K: Comparable<K>, V> : Map<K,V> {
    val tree: Tree<K, V> = Tree()

    override var size = 0
    private var _entries: Set<Map.Entry<K, V>> = emptySet()
    private var _keys : Set<K> = emptySet()
    private var _values: Collection<V> = emptyList()
    override val entries: Set<Map.Entry<K, V>>
        get() = _entries
    override val keys: Set<K>
        get() = _keys
    override val values: Collection<V>
        get() = _values

    override fun get(key: K): V? {
        return tree.get(key)
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
        _keys.plus(key)
        _values.plus(value)
        _entries.plus(key to value)
        size++
    }
}
