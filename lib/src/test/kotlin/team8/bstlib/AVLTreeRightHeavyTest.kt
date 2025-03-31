package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AVLTreeRightHeavyTest {

    @Test
    fun `test balance after right-heavy insertion`() {
        val tree = AVLTree(10, "Root")
        tree.insert(20, "A")
        tree.insert(30, "B")

        assertEquals("A", tree.find(20)?.value)
        assertEquals("Root", tree.find(10)?.value)
        assertEquals("B", tree.find(30)?.value)
    }
}
