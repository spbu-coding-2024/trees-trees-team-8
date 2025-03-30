package team8.bstlib

sealed class BinarySearchTreeNode<K : Comparable<K>, V, N : BinarySearchTreeNode<K, V, N>>(val key: K, val value: V) {
    var leftChild: N? = null
    var rightChild: N? = null
    var parent: N? = null

    fun getMinimum(): N {
        return if (leftChild == null) this as N else leftChild!!.getMinimum()
    }

    fun getMaximum(): N {
        return if (rightChild == null) this as N else rightChild!!.getMaximum()
    }
}