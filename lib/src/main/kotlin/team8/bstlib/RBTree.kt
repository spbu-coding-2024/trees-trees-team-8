package team8.bstlib

class RBTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) :
    BinarySearchTree<K, V, RBTreeNode<K, V>>(rootKey, rootValue) {
    override var root: RBTreeNode<K, V>? = RBTreeNode(Color.BLACK, rootKey, rootValue)

    private fun rotateLeft(node: RBTreeNode<K, V>) {
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

        return
    }

    private fun rotateRight(node: RBTreeNode<K, V>) {
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

        return
    }

    override fun insert(key: K, value: V): RBTreeNode<K, V>? = TODO()
    override fun remove(key: K): RBTreeNode<K, V>? = TODO()
}
