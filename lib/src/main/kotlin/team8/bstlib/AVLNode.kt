package team8.bstlib

class AVLNode<K : Comparable<K>, V>(key: K, value: V) : BinarySearchTreeNode<K, V, AVLNode<K, V>>(key, value) {

    var nodeHeight: Int = 1

    fun updateHeight() {
        val left = leftChild?.nodeHeight ?: 0
        val right = rightChild?.nodeHeight ?: 0
        nodeHeight = maxOf(left, right) + 1
    }

    fun balanceFactor() = (leftChild?.nodeHeight ?: 0) - (rightChild?.nodeHeight ?: 0)
}