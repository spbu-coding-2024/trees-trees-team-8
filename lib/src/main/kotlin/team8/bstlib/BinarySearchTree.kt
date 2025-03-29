package team8.bstlib

sealed class BinarySearchTree<K : Comparable<K>, V, N : BinarySearchTreeNode<K, V, N>>(rootKey: K, rootValue: V) {
    protected abstract var root: N?

    val keys: MutableList<K> = mutableListOf(rootKey)
        private set

    val values: MutableList<V> = mutableListOf(rootValue)
        private set

    abstract fun insert(key: K, value: V): N?
    abstract fun remove(key: K): N?

    fun find(key: K): N? {
        fun recursiveFind(currentNode: N?): N? {
            if (currentNode == null || currentNode.key == key) return currentNode
            return if (key > currentNode.key) {
                recursiveFind(currentNode.rightChild)
            } else {
                recursiveFind(currentNode.leftChild)
            }
        }
        return recursiveFind(root)
    }
}