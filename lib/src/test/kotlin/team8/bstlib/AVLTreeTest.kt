package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.math.log2
import kotlin.test.*

class AVLTreeTest {

    @Test
    fun `test insert and size`() {
        val tree = AVLTree<Int, String>()
        assertTrue(tree.insert(10, "A"))
        assertTrue(tree.insert(20, "B"))
        assertFalse(tree.insert(10, "C"))
        assertEquals(2, tree.size())
    }

    @Test
    fun `test remove`() {
        val tree = AVLTree<Int, String>().apply {
            insert(30, "A")
            insert(20, "B")
            insert(40, "C")
        }
        assertTrue(tree.remove(20))
        assertFalse(tree.remove(99))
        assertEquals(2, tree.size())
    }

    @Test
    fun `test balance after rotations`() {
        val tree = AVLTree<Int, String>()
        listOf(10, 20, 30).forEach { tree.insert(it, "v$it") }
        assertTrue(isBalanced(tree.root))
    }

    @Test
    fun `test sorted order`() {
        val tree = AVLTree<Int, String>()
        listOf(50, 30, 70, 20, 40, 60, 80).shuffled().forEach { tree.insert(it, "v$it") }
        assertEquals(listOf(20, 30, 40, 50, 60, 70, 80), tree.toList().map { it.first })
    }

    @Test
    fun `test height invariant`() {
        val tree = AVLTree<Int, String>()
        repeat(100) { tree.insert(it, "v$it") }
        val maxHeight = (1.44 * log2(100.0)).toInt() + 1
        assertTrue(treeHeight(tree.root) <= maxHeight)
    }

    private fun isBalanced(node: AVLNode<*, *>?): Boolean {
        if (node == null) return true
        val balance = node.balanceFactor()
        return balance in -1..1 && isBalanced(node.leftChild) && isBalanced(node.rightChild)
    }

    private fun treeHeight(node: AVLNode<*, *>?): Int {
        return node?.let { 1 + maxOf(treeHeight(it.leftChild), treeHeight(it.rightChild)) } ?: 0
    }
}