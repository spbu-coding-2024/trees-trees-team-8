package team8.bstlib

class RBTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) :
    BinarySearchTree<K, V, RBTreeNode<K, V>>(rootKey, rootValue) {
    override var root: RBTreeNode<K, V>? = RBTreeNode(Color.BLACK, rootKey, rootValue)

    private fun rotateLeft(node: RBTreeNode<K, V>?) {
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

        return
    }

    private fun rotateRight(node: RBTreeNode<K, V>?) {
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

        return
    }

    private fun balanceInsertion(insertedNode: RBTreeNode<K, V>?) {
        if (insertedNode == null) return
        var node = insertedNode
        if (node == root) {
            root?.color = Color.BLACK
            return
        }
        while (node?.parent?.color == Color.RED) {
            //parent is left child
            if (node.parent == node.parent?.parent?.leftChild) {
                //uncle is red
                if (node.parent?.parent?.rightChild?.color == Color.RED) {
                    node.parent?.color = Color.BLACK
                    node.parent?.parent?.rightChild?.color = Color.BLACK
                    node.parent?.parent?.color = Color.RED
                    node = node.parent?.parent
                }
                //uncle is black or does not exist
                else {
                    if (node == node.parent?.rightChild) {
                        node = node.parent
                        rotateLeft(node)
                    }
                    node?.parent?.color = Color.BLACK
                    node?.parent?.parent?.color = Color.RED
                    rotateRight(node?.parent?.parent)
                }
            }
            //parent is right child
            else {
                //uncle is red
                if (node.parent?.parent?.rightChild?.color == Color.RED) {
                    node.parent?.color = Color.BLACK
                    node.parent?.parent?.rightChild?.color = Color.BLACK
                    node.parent?.parent?.color = Color.RED
                    node = node.parent?.parent
                }
                //uncle is black or does not exist
                else {
                    if (node == node.parent?.leftChild) {
                        node = node.parent
                        rotateLeft(node)
                    }
                    node?.parent?.color = Color.BLACK
                    node?.parent?.parent?.color = Color.RED
                    rotateLeft(node?.parent?.parent)
                }
            }
        }
        root?.color = Color.BLACK
        return
    }

    override fun insert(key: K, value: V): RBTreeNode<K, V>? = TODO()
    override fun remove(key: K): RBTreeNode<K, V>? = TODO()
}
