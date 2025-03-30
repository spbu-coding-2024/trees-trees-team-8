package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class AVLTreeComplexCasesTest {

    @Test
    fun `maintain balance during multiple insertions and deletions`() {
        val tree = AVLTree(100, "A")

        repeat(50) { i ->
            tree.insert(i, "Value$i")
        }

        repeat(50) { i ->
            if (i % 3 == 0) tree.remove(i)
        }

        tree.keys.forEach { key ->
            val node = tree.find(key)
            assertTrue(node?.balanceFactor() in -1..1)
        }
    }
}