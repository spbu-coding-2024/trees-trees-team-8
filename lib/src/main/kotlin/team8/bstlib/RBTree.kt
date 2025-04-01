package team8.bstlib

class RBTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) :
    BinarySearchTree<K, V, RBTreeNode<K, V>>(rootKey, rootValue) {
    override var root: RBTreeNode<K, V>? = RBTreeNode(Color.BLACK, rootKey, rootValue)

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
                if (node.parent?.parent?.leftChild?.color == Color.RED) {
                    node.parent?.color = Color.BLACK
                    node.parent?.parent?.leftChild?.color = Color.BLACK
                    node.parent?.parent?.color = Color.RED
                    node = node.parent?.parent
                }
                //uncle is black or does not exist
                else {
                    if (node == node.parent?.leftChild) {
                        node = node.parent
                        rotateRight(node)
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

    private fun balanceRemoval(removedNodeChild: RBTreeNode<K, V>?) {
        if (removedNodeChild == null) return
        var node = removedNodeChild
        while (node?.color == Color.BLACK && node != root) {
            if (node.parent?.rightChild?.color == Color.RED) {
                node.parent?.rightChild?.color = Color.BLACK
                node.parent?.color = Color.RED
                rotateLeft(node.parent)
            }
            //brother has black children
            if (node.parent?.rightChild?.leftChild?.color == Color.BLACK &&
                node.parent?.rightChild?.rightChild?.color == Color.BLACK
            ) {
                node.parent?.rightChild?.color = Color.RED
            }
            //brother has one black child
            else {
                if (node.parent?.rightChild?.rightChild?.color == Color.BLACK) {
                    node.parent?.rightChild?.leftChild?.color = Color.RED
                    node.parent?.color = Color.RED
                    rotateRight(node.parent?.rightChild)
                } else {
                    val parentColor = node.parent?.color
                    if (parentColor != null) node.parent?.rightChild?.color = parentColor
                    node.parent?.color = Color.BLACK
                    node.parent?.rightChild?.rightChild?.color = Color.BLACK
                    rotateLeft(node.parent)
                    node = root
                }
            }
        }
        node?.color = Color.BLACK
        root?.color = Color.BLACK
    }

    override fun insert(key: K, value: V): RBTreeNode<K, V>? {
        val insertableNode = RBTreeNode(Color.RED, key, value)
        var currentNode = root
        var previousNode: RBTreeNode<K, V>? = null

        while (currentNode != null) {
            previousNode = currentNode
            currentNode = if (currentNode.key < key)
                currentNode.rightChild
            else if (currentNode.key == key)
                return null //node already exists
            else
                currentNode.leftChild
        }

        //either root was modified or previousNode wasn't
        if (root == null || previousNode == null) {
            root = insertableNode
            balanceInsertion(root)
            root?.key?.let { keys.add(it) }
            root?.value?.let { values.add(it) }
            return root
        }

        insertableNode.parent = previousNode
        if (previousNode.key < key)
            previousNode.rightChild = insertableNode
        else
            previousNode.leftChild = insertableNode

        balanceInsertion(insertableNode)
        keys.add(insertableNode.key)
        values.add(insertableNode.value)
        return insertableNode
    }

    override fun remove(key: K): RBTreeNode<K, V>? {
        //TODO: refactor
        val removableNode = find(key) ?: return null
        //no children
        if (removableNode.rightChild == null && removableNode.leftChild == null) {
            if (removableNode == root)
                root = null
            else {
                if (removableNode == removableNode.parent?.leftChild)
                    removableNode.parent?.leftChild = null
                else
                    removableNode.parent?.rightChild = null
            }
            return removableNode
        }
        //one child
        if (removableNode.rightChild != null && removableNode.leftChild == null) {
            if (removableNode == removableNode.parent?.leftChild)
                removableNode.parent?.leftChild = removableNode.rightChild
            else
                removableNode.parent?.rightChild = removableNode.rightChild

            return removableNode
        }
        if (removableNode.rightChild == null) {
            if (removableNode == removableNode.parent?.leftChild)
                removableNode.parent?.leftChild = removableNode.leftChild
            else
                removableNode.parent?.rightChild = removableNode.leftChild

            return removableNode
        }
        //both children
        val replaceNode = getMinimum(removableNode.rightChild) //get minimum from right subtree
        if (replaceNode?.rightChild != null) {
            replaceNode.rightChild?.parent = replaceNode.parent
        }
        if (replaceNode == root)
            root = replaceNode?.rightChild
        else
            replaceNode?.parent?.leftChild = replaceNode?.rightChild

        if (removableNode != replaceNode) {
            replaceNode?.color = removableNode.color
            replaceNode?.rightChild = removableNode.rightChild
            replaceNode?.leftChild = removableNode.leftChild
            if (removableNode == removableNode.parent?.leftChild)
                removableNode.parent?.leftChild = replaceNode
            else
                removableNode.parent?.rightChild = replaceNode
        }
        if (replaceNode?.color == Color.BLACK) {
            balanceRemoval(replaceNode)
        }
        return removableNode
    }
}
