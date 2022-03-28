package homeworks.hw3.task1

class AVLTreeMap<K, V>(override val size: Int, comparator: (V, V) -> Int) : Map<K, V> {
    val tree: Tree<K,V> = Tree(comparator)
    override val entries: Set<Map.Entry<K, V>>
        get() = TODO("Not yet implemented")
    override val keys: Set<K>
        get() = TODO("Not yet implemented")
    override val values: Collection<V>
        get() = TODO("Not yet implemented")

    override fun get(key: K): V? {
        TODO("Not yet implemented")
    }

    override fun containsKey(key: K): Boolean {
        TODO("Not yet implemented")
    }

    override fun containsValue(value: V): Boolean {
        TODO("Not yet implemented")
    }

    override fun isEmpty(): Boolean {
        TODO("Not yet implemented")
    }
}
