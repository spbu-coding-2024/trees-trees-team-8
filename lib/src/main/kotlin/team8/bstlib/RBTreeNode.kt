package team8.bstlib

class RBTreeNode<K : Comparable<K>, V>(var color: Color, key: K, value: V) :
    BinarySearchTreeNode<K, V, RBTreeNode<K, V>>(key, value)