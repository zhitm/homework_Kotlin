package tests.test1.task1

import java.util.Comparator

class MyQueue<T, K>(private val comparator: Comparator<QueueElement<T, K>>) {
    val set = sortedSetOf(comparator)
    var size = 0
    fun enqueue(element: T, priority: K) {
        set.add(QueueElement(element, priority))
        size += 1
    }

    fun peek(): T {
        require(size > 0) { "Queue should not be empty before peek" }
        return set.last().value
    }

    fun remove() {
        require(size > 0) { "Queue should not be empty before remove" }
        set.remove(set.last())
    }

    fun roll(): T {
        require(size > 0) { "Queue should not be empty before roll" }
        val element = peek()
        remove()
        return element
    }
}
