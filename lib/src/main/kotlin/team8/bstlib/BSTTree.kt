package team8.bstlib

class BST<K : Comparable<K>, V> : BinarySearchTree<K, V, BSTNode<K, V>>(null as K, null as V) {
    override var root: BSTNode<K, V>? = null

    constructor(rootKey: K, rootValue: V) : this() {
        root = BSTNode(rootKey, rootValue)
        keys[0] = rootKey
        values[0] = rootValue
    }

    constructor() : super(null as K, null as V) {
        root = null
        keys.clear()
        values.clear()
    }

    override fun insert(key: K, value: V): BSTNode<K, V>? {
        val newNode = BSTNode(key, value)
        
        if (root == null) {
            root = newNode
            keys.add(key)
            values.add(value)
            return newNode
        }

        var current = root
        var parent: BSTNode<K, V>? = null

        while (current != null) {
            parent = current
            when {
                key < current.key -> current = current.leftChild
                key > current.key -> current = current.rightChild
                else -> {
                    current.value = value
                    return current
                }
            }
        }

        newNode.parent = parent
        when {
            key < parent!!.key -> parent.leftChild = newNode
            else -> parent.rightChild = newNode
        }

        keys.add(key)
        values.add(value)
        return newNode
    }

    override fun remove(key: K): BSTNode<K, V>? {
        val nodeToRemove = find(key) ?: return null
        
        when {
            nodeToRemove.leftChild == null && nodeToRemove.rightChild == null -> {
                replaceNodeInParent(nodeToRemove, null)
            }
            nodeToRemove.leftChild == null -> {
                replaceNodeInParent(nodeToRemove, nodeToRemove.rightChild)
                nodeToRemove.rightChild?.parent = nodeToRemove.parent
            }
            nodeToRemove.rightChild == null -> {
                replaceNodeInParent(nodeToRemove, nodeToRemove.leftChild)
                nodeToRemove.leftChild?.parent = nodeToRemove.parent
            }
            else -> {
                val successor = getMinimum(nodeToRemove.rightChild)!!
                remove(successor.key)
                
                nodeToRemove.key = successor.key
                nodeToRemove.value = successor.value
                
                keys.remove(key)
                values.removeAt(keys.indexOf(successor.key))
                keys.add(successor.key)
                values.add(successor.value)
                
                return nodeToRemove
            }
        }
        
        keys.remove(key)
        values.removeAt(keys.indexOf(key))
        return nodeToRemove
    }

    private fun replaceNodeInParent(node: BSTNode<K, V>, newNode: BSTNode<K, V>?) {
        when {
            node.parent == null -> root = newNode
            node == node.parent!!.leftChild -> node.parent!!.leftChild = newNode
            else -> node.parent!!.rightChild = newNode
        }
    }

    fun rotateLeft(key: K) {
        val node = find(key)
        super.rotateLeft(node)
        if (node?.parent == null) {
            root = node?.leftChild?.parent ?: node?.rightChild?.parent ?: node
        }
    }

    fun rotateRight(key: K) {
        val node = find(key)
        super.rotateRight(node)
        if (node?.parent == null) {
            root = node?.leftChild?.parent ?: node?.rightChild?.parent ?: node
        }
    }

    fun getMin(): K? = getMinimum(root)?.key
    fun getMax(): K? = getMaximum(root)?.key
}

class BSTNode<K : Comparable<K>, V>(key: K, value: V) : BinarySearchTreeNode<K, V, BSTNode<K, V>>(key, value)
