package tests.task1

import tests.test1.task1.MyQueue
import tests.test1.task1.QueueIntComparator
import kotlin.test.Test
import kotlin.test.assertEquals

class TestMyQueue {
    private val cmp = QueueIntComparator

    @Test
    fun testQueue() {
        val queue = MyQueue<String, Int>(cmp)
        queue.enqueue("a", 100)
        queue.enqueue("b", 2)
        queue.enqueue("c", 4)
        queue.enqueue("d", 1000)
        assertEquals("d", queue.peek())
        assertEquals("d", queue.roll())
        queue.remove()
        assertEquals("c", queue.roll())
        assertEquals("b", queue.roll())
    }
}
