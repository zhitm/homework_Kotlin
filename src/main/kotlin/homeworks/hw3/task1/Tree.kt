package homeworks.hw3.task1

class Tree<K : Comparable<K>, V> {
    var root: Node<K, V>? = null

    fun get(key: K): Node<K, V>? {
        var current = root
        while (current != null) {
            current = if (current.key == key) return current
            else if (current.key > key) current.leftNode
            else current.rightNode
        }
        return null
    }

    fun hasKey(key: K): Boolean = get(key) != null

    private fun isBSTRuleDone(node: Node<K, V>?): Boolean {
        if (node == null) return true
        val isLeftKeyGreater = (node.leftNode ?: node).key > node.key
        val isRightKeyGreater = (node.rightNode ?: node).key < node.key
        return isLeftKeyGreater || isRightKeyGreater || !isTreeCorrect(node.leftNode) || !isTreeCorrect(node.rightNode)
    }

    fun isTreeCorrect(root: Node<K, V>?): Boolean {
        if (root != null) {
            if (root.isNotBalanced() || !isBSTRuleDone(root)) return false
        }
        return true
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
            currentNode = if (key > currentNode.key) {
                currentNode.rightNode
            } else if (key == currentNode.key) {
                throw IllegalStateException("This map has already this key")
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
        root?.let { updateHeightAndBalance(it, null) }
    }

    private fun getParent(node: Node<K, V>): Node<K, V>? {
        val key = node.key
        var current = root
        while (current != null) {
            current = if (current.leftNode == node || current.rightNode == node) return current
            else if (current.key > key) current.leftNode
            else current.rightNode
        }
        return null
    }

    private fun getMinFromSubtree(root: Node<K, V>): Node<K, V> {
        var current = root
        while (current.leftNode != null) {
            current = current.leftNode!!
        }
        return current
    }

    fun deleteNode(key: K) {
        val nodeToDelete = get(key) ?: return
        val parent = getParent(nodeToDelete)
        if (nodeToDelete.isLeaf()) {

            parent?.replaceChild(nodeToDelete, null)
            Balancer.changeRootIfNecessary(this, nodeToDelete, null)
        } else if (nodeToDelete.leftNode != null && nodeToDelete.rightNode != null) {

            val nodeToReplace = getMinFromSubtree(nodeToDelete.rightNode!!)
            deleteNode(nodeToReplace.key)
            nodeToDelete.key = nodeToReplace.key
            nodeToDelete.value = nodeToReplace.value
        } else if (nodeToDelete.leftNode != null) {

            parent?.replaceChild(nodeToDelete, nodeToDelete.leftNode)
            Balancer.changeRootIfNecessary(this, nodeToDelete, nodeToDelete.leftNode)
        } else {

            parent?.replaceChild(nodeToDelete, nodeToDelete.rightNode)
            Balancer.changeRootIfNecessary(this, nodeToDelete, nodeToDelete.rightNode)
        }
        root?.let { updateHeightAndBalance(it, null) }
    }

    private fun updateHeightAndBalance(node: Node<K, V>, parent: Node<K, V>?) {
        node.leftNode?.let { updateHeightAndBalance(it, node) }
        node.rightNode?.let { updateHeightAndBalance(it, node) }
        node.updateHeight()
        if (node.isNotBalanced()) Balancer.balanceNode(this, node, parent)
    }

    private object Balancer {

        fun <K : Comparable<K>, V> changeRootIfNecessary(tree: Tree<K, V>, parent: Node<K, V>, child: Node<K, V>?) {
            if (parent == tree.root) tree.root = child
        }

        fun <K : Comparable<K>, V> leftRotate(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
            changeRootIfNecessary(tree, node, node.rightNode)
            val newSubtreeRoot = node.rightNode!!
            parent?.replaceChild(node, node.rightNode)
            node.rightNode = newSubtreeRoot.leftNode
            newSubtreeRoot.leftNode = node
            node.updateHeight()
            newSubtreeRoot.updateHeight()
            parent?.updateHeight()
            return newSubtreeRoot
        }

        fun <K : Comparable<K>, V> rightRotate(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
            changeRootIfNecessary(tree, node, node.leftNode)
            val newSubtreeRoot = node.leftNode!!
            parent?.replaceChild(node, node.leftNode)
            node.leftNode = newSubtreeRoot.rightNode
            newSubtreeRoot.rightNode = node
            node.updateHeight()
            newSubtreeRoot.updateHeight()
            parent?.updateHeight()
            return newSubtreeRoot
        }

        fun <K : Comparable<K>, V> leftRightRotate(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?) {
            leftRotate(tree, node.leftNode!!, node)
            rightRotate(tree, node, parent)
        }

        fun <K : Comparable<K>, V> rightLeftRotate(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?) {
            rightRotate(tree, node.rightNode!!, node)
            leftRotate(tree, node, parent)
        }

        fun <K : Comparable<K>, V> balanceNode(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?) {
            when (node.getHeightDifference()) {
                2 -> if (node.rightNode?.getHeightDifference() == -1) rightLeftRotate(
                    tree,
                    node,
                    parent
                ) else leftRotate(
                    tree,
                    node,
                    parent
                )
                @Suppress("MagicNumber")
                -2 -> if (node.leftNode?.getHeightDifference() == 1) leftRightRotate(
                    tree,
                    node,
                    parent
                ) else rightRotate(
                    tree,
                    node,
                    parent
                )
            }
        }
    }
}
