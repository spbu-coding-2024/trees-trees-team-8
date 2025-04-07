package team8.bstlib

class RBTreeNode<K : Comparable<K>, V>(var color: Color, key: K, value: V) :
    BinarySearchTreeNode<K, V, RBTreeNode<K, V>>(key, value) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is RBTreeNode<*, *>) return false
        return this.color == other.color &&
                this.key == other.key &&
                this.value == this.value &&
                this.parent == this.parent &&
                this.leftChild == this.leftChild &&
                this.rightChild == this.rightChild
    }
}
