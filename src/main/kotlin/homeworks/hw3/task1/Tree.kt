package homeworks.hw3.task1

import java.util.LinkedList

class Tree<K : Comparable<K>, V> {
    var root: Node<K, V>? = null

    fun get(key: K): Node<K, V>? {
        var current = root
        while (current != null) {
            current = when {
                current.key == key -> return current
                current.key > key -> current.leftNode
                else -> current.rightNode
            }
        }
        return null
    }

    fun hasKey(key: K): Boolean = get(key) != null

    private fun isBSTRuleDone(node: Node<K, V>?): Boolean {
        node ?: return true
        val isLeftKeyGreater = node.leftNode?.let { it.key > node.key } ?: false
        val isRightKeyLess = node.rightNode?.let { it.key < node.key } ?: false
        return (!isLeftKeyGreater || !isRightKeyLess || isTreeCorrect(node.leftNode) && isTreeCorrect(node.rightNode))
    }

    fun isTreeCorrect(root: Node<K, V>?): Boolean = root?.let { (!root.isNotBalanced() && isBSTRuleDone(root)) } ?: true

    fun addNode(key: K, value: V) {
        if (root == null) {
            root = Node(key, value)
            return
        }
        val nodeWithThisKey = get(key)
        if (nodeWithThisKey != null) {
            nodeWithThisKey.value = value
            return
        }
        val newNode = Node(key, value)
        root?.let { insert(it, newNode) }
        root?.let { updateHeightAndBalance(it, null) }
    }

    private fun insert(root: Node<K, V>, node: Node<K, V>) {
        if (root.key > node.key) {
            if (root.leftNode != null) {
                root.leftNode?.let { insert(it, node) }
            } else {
                root.leftNode = node
            }
        } else {
            if (root.rightNode != null) {
                root.rightNode?.let { insert(it, node) }
            } else {
                root.rightNode = node
            }
        }
    }

    private fun getParent(node: Node<K, V>): Node<K, V>? {
        var current = root
        while (current != null) {
            current = when {
                (current.leftNode == node || current.rightNode == node) -> return current
                (current.key > node.key) -> current.leftNode
                else -> current.rightNode
            }
        }
        return null
    }

    private fun getMinFromSubtree(root: Node<K, V>): Node<K, V> = root.leftNode?.let { getMinFromSubtree(it) } ?: root

    fun deleteNode(key: K) {
        val nodeToDelete = get(key) ?: return
        val parent = getParent(nodeToDelete)
        when {
            (nodeToDelete.isLeaf()) -> {
                parent?.replaceChild(nodeToDelete, null)
                Balancer.changeRootIfNecessary(this, nodeToDelete, null)
            }
            (nodeToDelete.leftNode != null && nodeToDelete.rightNode != null) -> {
                val nodeToReplace = nodeToDelete.rightNode?.let { getMinFromSubtree(it) } ?: nodeToDelete
                deleteNode(nodeToReplace.key)
                nodeToDelete.key = nodeToReplace.key
                nodeToDelete.value = nodeToReplace.value
            }
            (nodeToDelete.leftNode != null) -> {
                parent?.replaceChild(nodeToDelete, nodeToDelete.leftNode)
                Balancer.changeRootIfNecessary(this, nodeToDelete, nodeToDelete.leftNode)
            }
            else -> {
                parent?.replaceChild(nodeToDelete, nodeToDelete.rightNode)
                Balancer.changeRootIfNecessary(this, nodeToDelete, nodeToDelete.rightNode)
            }
        }
        root?.let { updateHeightAndBalance(it, null) }
    }

    private fun updateHeightAndBalance(node: Node<K, V>, parent: Node<K, V>?) {
        node.leftNode?.let { updateHeightAndBalance(it, node) }
        node.rightNode?.let { updateHeightAndBalance(it, node) }
        node.updateHeight()
        if (node.isNotBalanced()) {
            Balancer.balanceNode(this, node, parent)
        }
    }

    private object Balancer {
        const val LEFT_SUBTREE_IS_MUCH_BIGGER = -2
        const val LEFT_SUBTREE_IS_BIGGER = -1
        const val RIGHT_SUBTREE_IS_MUCH_BIGGER = 2
        const val RIGHT_SUBTREE_IS_BIGGER = 1
        fun <K : Comparable<K>, V> changeRootIfNecessary(tree: Tree<K, V>, parent: Node<K, V>, child: Node<K, V>?) {
            if (parent == tree.root) {
                tree.root = child
            }
        }

        fun <K : Comparable<K>, V> leftRotate(root: Node<K, V>): Node<K, V> {
            val newSubtreeRoot = root.rightNode
            root.rightNode = newSubtreeRoot?.leftNode
            newSubtreeRoot?.let { it.leftNode = root }
            root.updateHeight()
            newSubtreeRoot?.updateHeight()
            return newSubtreeRoot ?: root
        }

        fun <K : Comparable<K>, V> rightRotate(root: Node<K, V>): Node<K, V> {
            val newSubtreeRoot = root.leftNode
            root.leftNode = newSubtreeRoot?.rightNode
            newSubtreeRoot?.let { it.rightNode = root }
            root.updateHeight()
            newSubtreeRoot?.updateHeight()
            return newSubtreeRoot ?: root
        }

        fun <K : Comparable<K>, V> leftRightRotate(node: Node<K, V>): Node<K, V> {
            node.leftNode = node.leftNode?.let { leftRotate(it) }
            return rightRotate(node)
        }

        fun <K : Comparable<K>, V> rightLeftRotate(node: Node<K, V>): Node<K, V> {
            node.rightNode = node.rightNode?.let { rightRotate(it) }
            return leftRotate(node)
        }

        fun <K : Comparable<K>, V> balanceNode(tree: Tree<K, V>, node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
            val newSubtreeRoot = when (node.balanceFactor) {
                RIGHT_SUBTREE_IS_MUCH_BIGGER ->
                    if (node.rightNode?.balanceFactor == LEFT_SUBTREE_IS_BIGGER) {
                        rightLeftRotate(node)
                    } else {
                        leftRotate(node)
                    }
                LEFT_SUBTREE_IS_MUCH_BIGGER ->
                    if (node.leftNode?.balanceFactor == RIGHT_SUBTREE_IS_BIGGER) {
                        leftRightRotate(node)
                    } else {
                        rightRotate(node)
                    }
                else -> node
            }
            parent?.replaceChild(node, newSubtreeRoot)
            parent?.updateHeight()
            if (node == tree.root) tree.root = newSubtreeRoot
            return newSubtreeRoot
        }
    }

    object Visitor {
        fun <K : Comparable<K>, V, T> visit(root: Node<K, V>?, value: (node: Node<K, V>) -> T): MutableSet<T> {
            val queue = LinkedList<Node<K, V>>()
            root?.let { queue.add(it) }
            val treeSet = mutableSetOf<T>()
            while (queue.isNotEmpty()) {
                val current = queue.pop()
                treeSet.add(value(current))
                current.leftNode?.let { queue.add(it) }
                current.rightNode?.let { queue.add(it) }
            }
            return treeSet
        }
    }
}
