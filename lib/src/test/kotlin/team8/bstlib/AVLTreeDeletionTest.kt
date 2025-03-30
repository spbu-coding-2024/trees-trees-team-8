package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AVLTreeDeletionTest {
    @Test
    fun `test node deletion`() {
        val tree = AVLTree(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        tree.remove(30)

        assertNull(tree.find(30))
        assertTrue(tree.find(50) != null)
        assertTrue(tree.find(70) != null)
    }
}