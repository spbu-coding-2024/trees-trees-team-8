package team8.bstlib

sealed class BinarySearchTree<K : Comparable<K>, V, N : BinarySearchTreeNode<K, V, N>>(rootKey: K, rootValue: V) {
    protected abstract val root: N

    val keys: MutableList<K> = mutableListOf(rootKey)
    val values: MutableList<V> = mutableListOf(rootValue)

    abstract fun insert(key: K, value: V): N?
    abstract fun remove(key: K): N?
    abstract fun find(key: K): N?
}