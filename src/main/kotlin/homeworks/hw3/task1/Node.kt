package homeworks.hw3.task1

import java.lang.Integer.max
import kotlin.math.abs

class Node<K, V>(var key: K, var value: V) {
    var leftNode: Node<K, V>? = null
    var rightNode: Node<K, V>? = null
    private var height = 0
    val balanceFactor
        get() = (rightNode?.height?.plus(1) ?: 0) - (leftNode?.height?.plus(1) ?: 0)

    fun replaceChild(oldChild: Node<K, V>, newChild: Node<K, V>?) {
        if (leftNode == oldChild) {
            leftNode = newChild
        } else {
            rightNode = newChild
        }
    }

    fun updateHeight() {
        val leftHeight = leftNode?.let { it.height + 1 } ?: 0
        val rightHeight = rightNode?.let { it.height + 1 } ?: 0
        height = max(leftHeight, rightHeight)
    }

    fun isNotBalanced(): Boolean = abs(balanceFactor) > 1
    fun isLeaf(): Boolean = (leftNode == null && rightNode == null)
}
