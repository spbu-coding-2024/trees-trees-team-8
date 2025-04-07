package team8.bstlib

class AVLTree<K : Comparable<K>, V> : Iterable<Pair<K, V>> {
    internal var root: AVLNode<K, V>? = null
    private var size = 0

    fun insert(key: K, nodeValue: V): Boolean {
        val (newRoot, inserted) = insertRecursive(root, key, nodeValue)
        root = newRoot
        if (inserted) size++
        return inserted
    }

    private fun insertRecursive(node: AVLNode<K, V>?, key: K, value: V): Pair<AVLNode<K, V>?, Boolean> {
        if (node == null) return AVLNode(key, value) to true
        return when {
            key < node.key -> {
                val (left, inserted) = insertRecursive(node.leftChild, key, value)
                node.leftChild = left
                updateAndBalance(node) to inserted
            }
            key > node.key -> {
                val (right, inserted) = insertRecursive(node.rightChild, key, value)
                node.rightChild = right
                updateAndBalance(node) to inserted
            }
            else -> {
                node.nodeValue = value
                node to false
            }
        }
    }

    private fun updateAndBalance(node: AVLNode<K, V>): AVLNode<K, V> {
        node.updateHeight()
        return balance(node)
    }

    fun remove(key: K): Boolean {
        val (newRoot, removed) = removeRecursive(root, key)
        root = newRoot
        if (removed) size--
        return removed
    }

    private fun removeRecursive(node: AVLNode<K, V>?, key: K): Pair<AVLNode<K, V>?, Boolean> {
        if (node == null) return null to false
        return when {
            key < node.key -> {
                val (left, removed) = removeRecursive(node.leftChild, key)
                node.leftChild = left
                updateAndBalance(node) to removed
            }
            key > node.key -> {
                val (right, removed) = removeRecursive(node.rightChild, key)
                node.rightChild = right
                updateAndBalance(node) to removed
            }
            else -> {
                val newNode = handleNodeDeletion(node)
                newNode?.updateHeight()
                newNode?.let { balance(it) } to true
            }
        }
    }

    private fun balance(node: AVLNode<K, V>): AVLNode<K, V> = when (node.balanceFactor()) {
        2 -> {
            node.leftChild?.let { leftChild ->
                if (leftChild.balanceFactor() < 0) {
                    node.leftChild = rotateLeft(leftChild)
                }
            }
            rotateRight(node)
        }
        -2 -> {
            node.rightChild?.let { rightChild ->
                if (rightChild.balanceFactor() > 0) {
                    node.rightChild = rotateRight(rightChild)
                }
            }
            rotateLeft(node)
        }
        else -> node
    }

    private fun rotateRight(node: AVLNode<K, V>): AVLNode<K, V> {
        val pivot = node.leftChild ?: return node
        node.leftChild = pivot.rightChild
        pivot.rightChild = node
        node.updateHeight()
        pivot.updateHeight()
        return pivot
    }

    private fun rotateLeft(node: AVLNode<K, V>): AVLNode<K, V> {
        val pivot = node.rightChild ?: return node
        node.rightChild = pivot.leftChild
        pivot.leftChild = node
        node.updateHeight()
        pivot.updateHeight()
        return pivot
    }

    private fun handleNodeDeletion(node: AVLNode<K, V>): AVLNode<K, V>? {
        return when {
            node.leftChild == null -> node.rightChild
            node.rightChild == null -> node.leftChild
            else -> {
                val rightChild = node.rightChild ?: return null
                val successor = findMin(rightChild)
                successor.rightChild = removeRecursive(rightChild, successor.key).first
                successor.leftChild = node.leftChild
                updateAndBalance(successor)
            }
        }
    }

    private fun findMin(node: AVLNode<K, V>): AVLNode<K, V> {
        var current = node
        while (current.leftChild != null) {
            current.leftChild?.let { current = it }
        }
        return current
    }

    fun contains(key: K): Boolean = find(key) != null

    fun find(key: K): V? {
        var current = root
        while (current != null) {
            current = when {
                key < current.key -> current.leftChild
                key > current.key -> current.rightChild
                else -> return current.nodeValue
            }
        }
        return null
    }

    override fun iterator(): Iterator<Pair<K, V>> = AVLTreeIterator(root)

    private inner class AVLTreeIterator(root: AVLNode<K, V>?) : Iterator<Pair<K, V>> {
        private val stack: ArrayDeque<AVLNode<K, V>> = ArrayDeque()

        init {
            pushLeft(root)
        }

        private fun pushLeft(node: AVLNode<K, V>?) {
            var cur = node
            while (cur != null) {
                stack.addLast(cur)
                cur = cur.leftChild
            }
        }

        override fun hasNext(): Boolean {
            return stack.isNotEmpty()
        }

        override fun next(): Pair<K, V> {
            if (!hasNext()) throw NoSuchElementException()
            val node = stack.removeLast()
            pushLeft(node.rightChild)
            return node.key to node.nodeValue
        }
    }

    fun size() = size
}