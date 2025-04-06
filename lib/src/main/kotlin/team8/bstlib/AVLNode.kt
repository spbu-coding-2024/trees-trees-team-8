package team8.bstlib

internal class AVLNode<K : Comparable<K>, V>(key: K, value: V) : BinarySearchTreeNode<K, V, AVLNode<K, V>>(key, value) {

    internal var nodeHeight: Int = 1

    internal fun updateHeight() {
        val left = leftChild?.nodeHeight ?: 0
        val right = rightChild?.nodeHeight ?: 0
        nodeHeight = maxOf(left, right) + 1
    }

    internal fun balanceFactor() = (leftChild?.nodeHeight ?: 0) - (rightChild?.nodeHeight ?: 0)
}