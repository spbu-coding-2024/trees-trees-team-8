package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class AVLTreeDuplicatesTest {

    @Test
    fun `duplicate handling`() {
        val tree = AVLTree(100, "Original")

        repeat(5) { i ->
            val result = tree.insert(100, "Duplicate$i")
            assertNull(result)
            assertEquals(1, tree.keys.size)
            assertEquals("Original", tree.find(100)?.value)
        }
    }
}