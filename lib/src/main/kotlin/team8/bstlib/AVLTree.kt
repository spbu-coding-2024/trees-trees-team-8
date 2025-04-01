package team8.bstlib

class AVLTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) : BinarySearchTree<K, V, AVLNode<K, V>>(rootKey, rootValue) {

    // Корень дерева (переопределяем из родительского класса)
    override var root: AVLNode<K, V>? = AVLNode(rootKey, rootValue)

    fun getHeight(key: K): Int? {
        return find(key)?.nodeHeight
    }

    fun getBalanceFactor(key: K): Int =
        find(key)?.balanceFactor() ?: throw NoSuchElementException()

    fun getTreeHeight(): Int =
        root?.nodeHeight ?: 0

    // Добавление элемента в дерево
    override fun insert(key: K, value: V): AVLNode<K, V>? {
        if (find(key) != null) return null // Проверка на дубликат
        root = insertRecursive(root, key, value)
        keys.add(key)
        values.add(value)
        return root
    }

    // Удаление элемента по ключу
    override fun remove(key: K): AVLNode<K, V>? {
        val node = find(key) ?: return null
        root = removeRecursive(root, key)
        keys.remove(key)
        values.remove(node.value)
        return node
    }

    // Рекурсивное добавление
    private fun insertRecursive(
        node: AVLNode<K, V>?, key: K, value: V): AVLNode<K, V> {
        // Создаём новый узел, если дошли до места вставки
        if (node == null) return AVLNode(key, value)

        // Выбираем левое или правое поддерево для вставки
        if (key < node.key) {
            node.leftChild = insertRecursive(node.leftChild, key, value)
            node.leftChild?.parent = node
        } else {
            node.rightChild = insertRecursive(node.rightChild, key, value)
            node.rightChild?.parent = node
        }

        // Обновляем высоту и балансируем
        node.updateHeight()
        return balance(node)
    }

    // Рекурсивное удаление
    private fun removeRecursive(node: AVLNode<K, V>?, key: K): AVLNode<K, V>? {
        if (node == null) return null

        when {
            key < node.key -> {
                node.leftChild = removeRecursive(node.leftChild, key)
                node.leftChild?.parent = node
            }
            key > node.key -> {
                node.rightChild = removeRecursive(node.rightChild, key)
                node.rightChild?.parent = node
            }
            else -> return handleNodeDeletion(node) // Нашли удаляемый узел
        }

        // Обновляем высоту и балансируем
        node.updateHeight()
        return balance(node)
    }

    // Проверка баланса узла
    private fun balance(node: AVLNode<K, V>): AVLNode<K, V> {
        return when (node.balanceFactor()) {
            2 -> handleLeftHeavy(node)  // Левое поддерево выше
            -2 -> handleRightHeavy(node) // Правое поддерево выше
            else -> node // Баланс в норме
        }
    }

    // Балансировка для левого перевеса
    private fun handleLeftHeavy(node: AVLNode<K, V>): AVLNode<K, V> {
        // Если левый ребёнок имеет правый перевес
        if (node.leftChild!!.balanceFactor() < 0) {
            node.leftChild = rotateLeft(node.leftChild!!)
        }
        return rotateRight(node)
    }

    // Балансировка для правого перевеса
    private fun handleRightHeavy(node: AVLNode<K, V>): AVLNode<K, V> {
        // Если правый ребёнок имеет левый перевес
        if (node.rightChild!!.balanceFactor() > 0) {
            node.rightChild = rotateRight(node.rightChild!!)
        }
        return rotateLeft(node)
    }

    // Левый поворот
    private fun rotateLeft(node: AVLNode<K, V>): AVLNode<K, V> {
        super.rotateLeft(node) // Используем реализацию из BST
        node.updateHeight()
        return node.parent!!.apply { updateHeight() }
    }

    // Правый поворот
    private fun rotateRight(node: AVLNode<K, V>): AVLNode<K, V> {
        super.rotateRight(node) // Используем реализацию из BST
        node.updateHeight()
        return node.parent!!.apply { updateHeight() }
    }

    private fun handleNodeDeletion(node: AVLNode<K, V>): AVLNode<K, V>? {
        return when {
            // У узла нет левого ребёнка
            node.leftChild == null -> node.rightChild?.apply {
                parent = node.parent
            }
            // У узла нет правого ребёнка
            node.rightChild == null -> node.leftChild?.apply {
                parent = node.parent
            }
            // У узла есть оба ребёнка
            else -> {
                // Ищем минимальный узел в правом поддереве
                val successor = getMinimum(node.rightChild)!!
                // Удаляем преемника из правого поддерева
                node.rightChild = removeRecursive(node.rightChild, successor.key)
                // Заменяем удаляемый узел на преемника
                successor.parent = node.parent
                successor.leftChild = node.leftChild
                successor.rightChild = node.rightChild
                successor.updateHeight()
                successor
            }
        }
    }
}
