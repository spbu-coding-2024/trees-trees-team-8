package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertEquals

class AVLTreeEdgeCasesTest {
    @Test
    fun `handle edge cases with single node`() {
        val tree = AVLTree(100, "Single")

        assertEquals(1, tree.getHeight(100))

        tree.remove(100)
        assertNull(tree.find(100))
    }
}