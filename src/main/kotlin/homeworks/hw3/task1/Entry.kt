package homeworks.hw3.task1

data class Entry<K, V>(override val key: K, override var value: V) : MutableMap.MutableEntry<K, V> {
    override fun setValue(newValue: V): V {
        val previous = value
        value = newValue
        return previous
    }
}
