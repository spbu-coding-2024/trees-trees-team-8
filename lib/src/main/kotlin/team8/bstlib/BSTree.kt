package team8.bstlib

class BST<K : Comparable<K>, V> : BinarySearchTree<K, V, BST.BSTNode<K, V>> {

    class BSTNode<K : Comparable<K>, V>(
        key: K,
        value: V
    ) : BinarySearchTreeNode<K, V, BSTNode<K, V>>(key, value)

    constructor() : super(null as K, null as V) {
        keys.clear()
        values.clear()
        root = null
    }

    constructor(rootKey: K, rootValue: V) : super(rootKey, rootValue) {
        root = BSTNode(rootKey, rootValue)
    }

    companion object {
        fun <K : Comparable<K>, V> createEmpty(): BST<K, V> = BST()
        fun <K : Comparable<K>, V> createWithRoot(rootKey: K, rootValue: V): BST<K, V> = BST(rootKey, rootValue)
    }

    override var root: BSTNode<K, V>? = null

    override fun insert(key: K, value: V): BSTNode<K, V>? {
        if (root == null) {
            root = BSTNode(key, value)
            keys.add(key)
            values.add(value)
            return root
        }

        var current = root
        var parent: BSTNode<K, V>? = null
        var isLeftChild = false

        while (current != null) {
            parent = current
            when {
                key < current.key -> {
                    current = current.leftChild
                    isLeftChild = true
                }
                key > current.key -> {
                    current = current.rightChild
                    isLeftChild = false
                }
                else -> return current
            }
        }

        val newNode = BSTNode(key, value).apply {
            this.parent = parent
            if (isLeftChild) parent?.leftChild = this else parent?.rightChild = this
        }

        keys.add(key)
        values.add(value)
        return newNode
    }

    override fun remove(key: K): BSTNode<K, V>? {
        val nodeToRemove = find(key) ?: return null
        val removedNode = BSTNode(nodeToRemove.key, nodeToRemove.value)
        val indexToRemove = keys.indexOf(key)

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
                if (successor != nodeToRemove.rightChild) {
                    replaceNodeInParent(successor, successor.rightChild)
                    successor.rightChild = nodeToRemove.rightChild
                    successor.rightChild?.parent = successor
                }
                replaceNodeInParent(nodeToRemove, successor)
                successor.leftChild = nodeToRemove.leftChild
                successor.leftChild?.parent = successor
            }
        }

        if (indexToRemove >= 0) {
            keys.removeAt(indexToRemove)
            values.removeAt(indexToRemove)
        }
        return removedNode
    }

    private fun replaceNodeInParent(node: BSTNode<K, V>, newNode: BSTNode<K, V>?) {
        when {
            node.parent == null -> root = newNode
            node == node.parent!!.leftChild -> node.parent!!.leftChild = newNode
            else -> node.parent!!.rightChild = newNode
        }
        newNode?.parent = node.parent
    }

    fun getMin(): K? = getMinimum(root)?.key
    fun getMax(): K? = getMaximum(root)?.key
    fun getRootForTesting(): BSTNode<K, V>? = root
}
