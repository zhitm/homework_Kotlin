package homeworks.hw3.task1

import java.lang.Integer.max


class Node<K, V>(val key: K, val value: V, val parent: Node<K,V>?) {
    var leftNode: Node<K, V>? = null
      
    var rightNode: Node<K, V>? = null
    private var height = 0
    fun updateHeight() {
        height = max(leftNode?.height?.plus(1) ?: 0, rightNode?.height?.plus(1) ?: 0)
    }

    fun getHeightDifference() : Int = (leftNode?.height ?: 0) - (leftNode?.height ?: 0)
    fun isNotBalanced(): Boolean = kotlin.math.abs((leftNode?.height ?: 0) - (leftNode?.height ?: 0)) > 1


}