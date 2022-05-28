package tests.test1.task1

object QueueStringComparator : Comparator<QueueElement<String, String>> {
    override fun compare(o1: QueueElement<String, String>?, o2: QueueElement<String, String>?): Int {
        if (o1 == null || o2 == null) return 0
        return o1.priority.compareTo(o2.priority)
    }
}
