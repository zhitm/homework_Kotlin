package homeworks.hw3.task1

import java.io.File
import java.io.PrintWriter

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

    fun isTreeCorrect(root: Node<K, V>?): Boolean {
        if (root == null) return true
        if (root.isNotBalanced()) return false
        if (root.leftNode != null) {
            if (root.leftNode!!.key > root.key) return false
            if (!isTreeCorrect(root.leftNode!!)) return false
        }
        if (root.rightNode != null) {
            if (root.rightNode!!.key < root.key) return false
            if (!isTreeCorrect(root.rightNode!!)) return false
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
        if (root == node) return null
        val key = node.key
        var current = root
        while (current != null) {
            current = if (current.leftNode == node) return current
            else if (current.rightNode == node) return current
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
            changeRootIfNecessary(nodeToDelete, null)
        } else if (nodeToDelete.leftNode != null && nodeToDelete.rightNode != null) {

            val nodeToReplace = getMinFromSubtree(nodeToDelete.rightNode!!)
            deleteNode(nodeToReplace.key)
            nodeToDelete.key = nodeToReplace.key
            nodeToDelete.value = nodeToReplace.value
        } else if (nodeToDelete.leftNode != null) {

            parent?.replaceChild(nodeToDelete, nodeToDelete.leftNode)
            changeRootIfNecessary(nodeToDelete, nodeToDelete.leftNode)
        } else {

            parent?.replaceChild(nodeToDelete, nodeToDelete.rightNode)
            changeRootIfNecessary(nodeToDelete, nodeToDelete.rightNode)
        }
        root?.let { updateHeightAndBalance(it, null) }
    }

    private fun updateHeightAndBalance(node: Node<K, V>, parent: Node<K, V>?) {
        node.leftNode?.let { updateHeightAndBalance(it, node) }
        node.rightNode?.let { updateHeightAndBalance(it, node) }
        node.updateHeight()
        if (node.isNotBalanced()) balanceNode(node, parent)
    }

    private fun changeRootIfNecessary(parent: Node<K, V>, child: Node<K, V>?) {
        if (parent == root) root = child
    }

    private fun leftRotate(node: Node<K, V>, parent: Node<K, V>?): Node<K, V> {
        changeRootIfNecessary(node, node.rightNode)
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
        changeRootIfNecessary(node, node.leftNode)
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

    private fun writeEdges(root: Node<K, V>, writer: PrintWriter) {
        if (root.leftNode != null) {
            writer.println("${root.key} -> ${root.leftNode!!.key}")
            writeEdges(root.leftNode!!, writer)
        }
        if (root.rightNode != null) {
            writer.println("${root.key} -> ${root.rightNode!!.key}")
            writeEdges(root.rightNode!!, writer)
        }
    }

    fun createDotFile(path: String) {
        File(path).printWriter().use { out ->
            out.println("digraph G {")
            if (root?.isLeaf() == true) out.println(root?.key ?: "empty graph")
            root?.let { writeEdges(it, out) }
            out.println("}")
        }
    }
}


