package team8.bstlib

class AVLNode<K : Comparable<K>, V>(
    key: K,
    value: V
) : BinarySearchTreeNode<K, V, AVLNode<K, V>>(key, value) {
    private var height: Int = 1

    fun getHeight(): Int = height
    // Обновляет высоту узла на основе высот потомков
    fun updateHeight() {
        height = 1 + maxOf(
            leftChild?.height ?: 0,
            rightChild?.height ?: 0
        )
    }

    // Вычисляет баланс-фактор узла
    fun balanceFactor() =
        (leftChild?.height ?: 0) - (rightChild?.height ?: 0)
}