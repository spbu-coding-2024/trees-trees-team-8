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

    //Удаление элемента по ключу
    override fun remove(key: K): AVLNode<K, V>? {
        val node = find(key) ?: return null
        root = removeRecursive(root, key)
        keys.remove(key)
        values.remove(node.value)
        return node
    }
}