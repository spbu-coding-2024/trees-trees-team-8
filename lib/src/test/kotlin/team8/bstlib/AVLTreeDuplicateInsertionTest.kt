package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertEquals

class AVLTreeDuplicateInsertionTest {

    @Test
    fun `test duplicate insertion returns null and doesn't modify data`() {
        val tree = AVLTree(50, "Root")

        tree.insert(30, "A")
        assertEquals(2, tree.keys.size)
        assertEquals(2, tree.values.size)

        val duplicateInsertResult = tree.insert(30, "B")

        assertNull(duplicateInsertResult)
        assertEquals(2, tree.keys.size)
        assertEquals(2, tree.values.size)

        assertEquals("A", tree.find(30)?.value)
    }
}