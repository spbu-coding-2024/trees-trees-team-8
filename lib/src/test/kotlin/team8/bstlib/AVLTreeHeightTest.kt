package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AVLTreeHeightTest {

    @Test
    fun `node heights after complex rotations`() {
        val tree = AVLTree(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")
        tree.insert(20, "C")
        tree.insert(40, "D")

        assertEquals(3, tree.getHeight(50))
        assertEquals(2, tree.getHeight(30))
        assertEquals(1, tree.getHeight(20))
    }
}