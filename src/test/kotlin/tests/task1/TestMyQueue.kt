package tests.task1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import tests.test1.task1.MyQueue
import tests.test1.task1.QueueIntComparator

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
