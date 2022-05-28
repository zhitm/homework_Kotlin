package tests.test1.task1

object QueueIntComparator : Comparator<QueueElement<String, Int>> {
    override fun compare(o1: QueueElement<String, Int>?, o2: QueueElement<String, Int>?): Int {
        if (o1 == null || o2 == null) return 0
        return o1.priority.compareTo(o2.priority)
    }
}
