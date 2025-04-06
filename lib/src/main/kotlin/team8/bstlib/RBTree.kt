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
            //if node is left child
            if (node == node.parent?.leftChild) {
                if (node.parent?.rightChild?.color == Color.RED) {
                    node.parent?.rightChild?.color = Color.BLACK
                    node.parent?.color = Color.RED
                    rotateLeft(node.parent)
                }
                //brother has black children or does not have children at all
                if ((node.parent?.rightChild?.leftChild?.color == Color.BLACK &&
                            node.parent?.rightChild?.rightChild?.color == Color.BLACK) ||
                    (node.parent?.rightChild?.leftChild == null &&
                            node.parent?.rightChild?.rightChild == null)
                ) {
                    node.parent?.rightChild?.color = Color.RED
                    node = node.parent
                }
                //brother has one black child
                else {
                    if (node.parent?.rightChild?.rightChild?.color == Color.BLACK) {
                        node.parent?.rightChild?.leftChild?.color = Color.RED
                        node.parent?.color = Color.RED
                        rotateRight(node.parent?.rightChild)
                        node = node.parent
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
            //if node is right child
            else {
                if (node.parent?.leftChild?.color == Color.RED) {
                    node.parent?.leftChild?.color = Color.BLACK
                    node.parent?.color = Color.RED
                    rotateRight(node.parent)
                }
                //brother has black children or does not have children at all
                if ((node.parent?.leftChild?.leftChild?.color == Color.BLACK &&
                            node.parent?.leftChild?.rightChild?.color == Color.BLACK) ||
                    (node.parent?.leftChild?.leftChild == null &&
                            node.parent?.leftChild?.rightChild == null)
                ) {
                    node.parent?.leftChild?.color = Color.RED
                    node = node.parent
                }
                //brother has one black child
                else {
                    if (node.parent?.leftChild?.leftChild?.color == Color.BLACK) {
                        node.parent?.leftChild?.rightChild?.color = Color.RED
                        node.parent?.color = Color.RED
                        rotateLeft(node.parent?.leftChild)
                        node = node.parent
                    } else {
                        val parentColor = node.parent?.color
                        if (parentColor != null) node.parent?.leftChild?.color = parentColor
                        node.parent?.color = Color.BLACK
                        node.parent?.leftChild?.leftChild?.color = Color.BLACK
                        rotateRight(node.parent)
                        node = root
                    }
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
                balanceRemoval(removableNode)
                if (removableNode == removableNode.parent?.leftChild)
                    removableNode.parent?.leftChild = null
                else
                    removableNode.parent?.rightChild = null
            }
            keys.remove(removableNode.key)
            values.remove(removableNode.value)
            return removableNode
        }
        //one child
        if (removableNode.rightChild != null && removableNode.leftChild == null) {
            if (removableNode == removableNode.parent?.leftChild) {
                removableNode.parent?.leftChild = removableNode.rightChild
                balanceRemoval(removableNode.rightChild)
            } else {
                removableNode.parent?.rightChild = removableNode.rightChild
                balanceRemoval(removableNode.rightChild)
            }

            keys.remove(removableNode.key)
            values.remove(removableNode.value)
            return removableNode
        }
        if (removableNode.rightChild == null) {
            if (removableNode == removableNode.parent?.leftChild) {
                removableNode.parent?.leftChild = removableNode.leftChild
                balanceRemoval(removableNode.leftChild)
            } else {
                removableNode.parent?.rightChild = removableNode.leftChild
                balanceRemoval(removableNode.leftChild)
            }

            keys.remove(removableNode.key)
            values.remove(removableNode.value)
            return removableNode
        }
        //both children
        val replaceNode = getMinimum(removableNode.rightChild) //get minimum from right subtree
        if (replaceNode?.rightChild != null) {
            replaceNode.rightChild?.parent = replaceNode.parent
            if (replaceNode == replaceNode.parent?.leftChild)
                replaceNode.parent?.leftChild = removableNode.rightChild
            else
                replaceNode.parent?.rightChild = removableNode.rightChild
        } else {
            if (replaceNode == replaceNode?.parent?.leftChild)
                replaceNode?.parent?.leftChild = null
            else
                replaceNode?.parent?.rightChild = null
        }
        val replaceNodeChild = replaceNode?.rightChild
        if (replaceNode == root)
            root = replaceNode?.rightChild

        //swap nodes (ugly because node key is immutable)
        if (removableNode != replaceNode) {
            replaceNode?.color = removableNode.color
            replaceNode?.rightChild = removableNode.rightChild
            removableNode.rightChild?.parent = replaceNode
            replaceNode?.leftChild = removableNode.leftChild
            removableNode.leftChild?.parent = replaceNode
            if (removableNode == removableNode.parent?.leftChild)
                removableNode.parent?.leftChild = replaceNode
            else
                removableNode.parent?.rightChild = replaceNode

            replaceNode?.parent = removableNode.parent
            if (replaceNode?.parent == null) root = replaceNode
        }
        if (replaceNode?.color == Color.BLACK) {
            balanceRemoval(replaceNodeChild)
        }
        keys.remove(removableNode.key)
        values.remove(removableNode.value)
        return removableNode
    }
}
