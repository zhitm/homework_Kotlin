package homeworks.hw3.task1

class Tree<K, V>(val comparator: (V, V) -> Int) {
    var root: Node<K, V>? = null
    fun balanceTreeFromNode(node: Node<K, V>) {
        if (node.isNotBalanced()) {
            balanceNode(node)
            node.parent?.let { balanceNode(it) }
        }

    }

    operator fun V.compareTo(value: V): Int = comparator(this, value)

    fun addNode(node: Node<K, V>) {
//        if (root == null) root = node
//        var currentNode = root
//        val value = node.value
//        while (currentNode != null) {
//            currentNode = if (value >= currentNode.value) {
//                currentNode.rightNode
//            } else {
//                currentNode.leftNode
//            }
//        }
    }

    fun deleteNode(node: Node<K, V>) {
        TODO()
    }

    private fun leftRotation() {
        TODO()
    }

    private fun rightRotation() {
        TODO()
    }

    private fun bigLeftRotation() {
        TODO()
    }

    private fun bigRightRotation() {
        TODO()
    }

    private fun balanceNode(node: Node<K, V>) {
        TODO()
    }


}


}

