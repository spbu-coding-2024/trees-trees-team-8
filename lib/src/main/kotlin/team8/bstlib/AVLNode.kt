package team8.bstlib

internal class AVLNode<K : Comparable<K>, V>(key: K, value: V) : BinarySearchTreeNode<K, V, AVLNode<K, V>>(key, value) {
    internal var nodeHeight = 1
    internal var nodeValue = value

    internal fun updateHeight() {
        nodeHeight = maxOf(leftChild?.nodeHeight ?: 0, rightChild?.nodeHeight ?: 0) + 1
    }

    internal fun balanceFactor() = (leftChild?.nodeHeight ?: 0) - (rightChild?.nodeHeight ?: 0)
}