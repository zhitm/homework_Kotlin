package homeworks.hw3.task1

import java.lang.Integer.max

class Node<K, V>(var key: K, var value: V) {
    var leftNode: Node<K, V>? = null
    var rightNode: Node<K, V>? = null
    var height = 0
    fun replaceChild(oldChild: Node<K, V>, newChild: Node<K, V>?) {
        if (leftNode == oldChild) {
            leftNode = newChild
        } else {
            rightNode = newChild
        }
    }

    fun updateHeight() {
        val leftHeight = if (leftNode == null) 0 else leftNode!!.height + 1
        val rightHeight = if (rightNode == null) 0 else rightNode!!.height + 1
        height = max(leftHeight, rightHeight)
    }

    fun getHeightDifference(): Int = (rightNode?.height?.plus(1) ?: 0) - (leftNode?.height?.plus(1) ?: 0)
    fun isNotBalanced(): Boolean = kotlin.math.abs(getHeightDifference()) > 1
    fun isLeaf(): Boolean = (leftNode == null && rightNode == null)
}
