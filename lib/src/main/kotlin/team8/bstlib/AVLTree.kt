package team8.bstlib


class AVLTree<K : Comparable<K>, V>(rootKey: K, rootValue: V) : BinarySearchTree<K, V, AVLNode<K, V>>(rootKey, rootValue) {

    // Корень дерева (переопределяем из родительского класса)
    override var root: AVLNode<K, V>? = AVLNode(rootKey, rootValue)

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
}