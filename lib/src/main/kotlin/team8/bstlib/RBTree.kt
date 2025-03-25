package team8.bstlib

class RBTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) :
    BinarySearchTree<K, V, RBTreeNode<K, V>>(rootKey, rootValue) {
    override val root: RBTreeNode<K, V> = RBTreeNode(Color.BLACK, rootKey, rootValue)

    override fun insert(key: K, value: V): RBTreeNode<K, V>? = TODO()
    override fun remove(key: K): RBTreeNode<K, V>? = TODO()
}
