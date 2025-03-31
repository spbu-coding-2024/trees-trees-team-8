package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertNotNull
import kotlin.test.assertTrue
import kotlin.test.assertEquals

class AVLTreeRootDeletionTest {
    @Test
    fun `test deletion of root node via public API`() {
        val tree = AVLTree(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        val deletedNode = tree.remove(50)

        assertNotNull(deletedNode)
        assertNull(tree.find(50))
        assertEquals(2, tree.keys.size)
        assertTrue(tree.keys.containsAll(listOf(30, 70)))
    }
}
