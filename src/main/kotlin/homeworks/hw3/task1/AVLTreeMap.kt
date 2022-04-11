package homeworks.hw3.task1

import java.io.File
import java.io.IOException
import java.io.InputStreamReader
import java.io.Reader
import java.util.concurrent.TimeUnit

class AVLTreeMap<K : Comparable<K>, V> : Map<K, V> {
    private val tree: Tree<K, V> = Tree()

    override var size = 0
    private var _entries: Set<Map.Entry<K, V>> = emptySet()
    private var _keys: Set<K> = emptySet()
    private var _values: Collection<V> = emptyList()
    override val entries: Set<Map.Entry<K, V>>
        get() = _entries
    override val keys: Set<K>
        get() = _keys
    override val values: Collection<V>
        get() = _values

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
        _keys.plus(key)
        _values.plus(value)
        _entries.plus(key to value)
        size++
    }

    fun delete(key: K) {
        val value = get(key)
        tree.deleteNode(key)
        size--
        _keys.minus(key)
        _values.minus(value)
        _entries.minus(key to value)
    }

    fun isMapCorrect(): Boolean = tree.isTreeCorrect(tree.root) && _keys.all { tree.hasKey(it) }


    fun drawKeyTree(path: String) {
        tree.createDotFile(path)
    }
}

