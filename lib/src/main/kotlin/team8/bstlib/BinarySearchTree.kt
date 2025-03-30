package team8.bstlib

sealed class BinarySearchTree<K : Comparable<K>, V, N : BinarySearchTreeNode<K, V, N>>(rootKey: K, rootValue: V) {
    protected abstract var root: N?

    val keys: MutableList<K> = mutableListOf(rootKey)
    val values: MutableList<V> = mutableListOf(rootValue)

    protected fun rotateLeft(node: N?) {
        if (node == null) return
        val substitutionNode = node.rightChild ?: return
        substitutionNode.parent = node.parent
        if (node.parent?.leftChild == node)
            node.parent?.leftChild = substitutionNode
        else
            node.parent?.rightChild = substitutionNode

        node.rightChild = substitutionNode.leftChild
        substitutionNode.leftChild?.parent = node

        node.parent = substitutionNode
        substitutionNode.leftChild = node
    }

    protected fun rotateRight(node: N?) {
        if (node == null) return
        val substitutionNode = node.leftChild ?: return
        substitutionNode.parent = node.parent
        if (node.parent?.leftChild == node)
            node.parent?.leftChild = substitutionNode
        else
            node.parent?.rightChild = substitutionNode

        node.leftChild = substitutionNode.rightChild
        substitutionNode.rightChild?.parent = node

        node.parent = substitutionNode
        substitutionNode.rightChild = node
    }

    abstract fun insert(key: K, value: V): N?
    abstract fun remove(key: K): N?
    fun find(key: K): N? {
        fun recursiveFind(currentNode: N?): N? {
            if (currentNode == null || currentNode.key == key) return currentNode
            return if (currentNode.key < key)
                recursiveFind(currentNode.rightChild)
            else
                recursiveFind(currentNode.leftChild)
        }
        return recursiveFind(root)
    }

    protected fun getMinimum(node: N?): N? =
        if (node?.leftChild == null) node else getMinimum(node.leftChild)

    protected fun getMaximum(node: N?): N? =
        if (node?.rightChild == null) node else getMaximum(node.rightChild)
}