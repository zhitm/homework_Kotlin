package tests.test1.task1

import java.util.Comparator

class MyQueue<T, K>(private val comparator: Comparator<QueueElement<T, K>>) {
    val set = sortedSetOf(comparator)

    fun enqueue(element: T, priority: K) {
        set.add(QueueElement(element, priority))
    }

    fun peek(): T {
        return set.last().value
    }

    fun remove() {
        set.remove(set.last())
    }

    fun roll(): T {
        val element = peek()
        remove()
        return element
    }
}
