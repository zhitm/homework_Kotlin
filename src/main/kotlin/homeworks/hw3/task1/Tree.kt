package homeworks.hw3.task1

class Tree<K: Comparable<K>, V> {
    var root: Node<K, V>? = null

    fun get(key: K) : V?{
        var current = root
        while (current != null){
            if (current.key == key) return current.value
            else if (current.key > key) current.leftNode
            else current = current.rightNode
        }
        return null
    }

    fun addNode(key: K, value: V) {
        if (root == null) {
            root = Node(key, value)
            return
        }
        var last: Node<K, V> = root as Node<K, V>
        var currentNode = root
        while (currentNode != null) {
            last = currentNode
            currentNode = if (key >= currentNode.key) {
                currentNode.rightNode
            } else {
                currentNode.leftNode
            }
        }
        val newNode = Node(key, value)
        if (key >= last.key) {
            last.rightNode = newNode
        } else {
            last.leftNode = newNode
        }
        root?.let{updateHeightAndBalance(it, null)}
    }

    fun deleteNode(node: Node<K, V>) {
        TODO()
    }

    private fun updateHeightAndBalance(node: Node<K,V>, parent: Node<K, V>?) {
        node.leftNode?.let{updateHeightAndBalance(it, node)}
        node.rightNode?.let{updateHeightAndBalance(it, node)}
        node.updateHeight()
        if (node.isNotBalanced()) balanceNode(node, parent)

    }

    private fun leftRotate(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        if (node == root) root = node.rightNode
        val newSubtreeRoot = node.rightNode!!
        parent?.replaceChild(node, node.rightNode)
        node.rightNode = newSubtreeRoot.leftNode
        newSubtreeRoot.leftNode = node
        node.updateHeight()
        newSubtreeRoot.updateHeight()
        parent?.updateHeight()
        return newSubtreeRoot
    }

    private fun rightRotate(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        if (node == root) root = node.leftNode
        val newSubtreeRoot = node.leftNode!!
        parent?.replaceChild(node, node.leftNode)
        node.leftNode = newSubtreeRoot.rightNode
        newSubtreeRoot.rightNode = node
        node.updateHeight()
        newSubtreeRoot.updateHeight()
        parent?.updateHeight()
        return newSubtreeRoot
    }

    private fun leftRightRotate(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        leftRotate(node.leftNode!!, node)
        val newSubtreeRoot = node.leftNode
        rightRotate(node, parent)
        return newSubtreeRoot!!
    }

    private fun rightLeftRotate(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        rightRotate(node.rightNode!!, node)
        val newSubtreeRoot = node.rightNode
        leftRotate(node, parent)
        return newSubtreeRoot!!
    }

    private fun balanceNode(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        return when (node.getHeightDifference()) {
            2 -> if (node.rightNode?.getHeightDifference() == -1) rightLeftRotate(node, parent) else leftRotate(
                node,
                parent
            )
            -2 -> if (node.leftNode?.getHeightDifference() == 1) leftRightRotate(node, parent) else rightRotate(
                node,
                parent
            )
            else -> node
        }
    }
}


