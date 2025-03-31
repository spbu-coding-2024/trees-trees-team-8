package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class AVLTreeMixedOperationsTest {

    @Test
    fun `test multiple insertions and deletions maintain AVL balance`() {
        val tree = AVLTree(100, "A")

        (1..20).forEach { i ->
            tree.insert(100 + i, "Val$i")
        }

        (1..20).filter { it % 4 == 0 }.forEach { i ->
            tree.remove(100 + i)
        }

        tree.keys.forEach { key ->
            val node = tree.find(key)
            assertTrue(node?.balanceFactor() in -1..1, "Balance factor of node with key $key is ${node?.balanceFactor()}")
        }
    }
}
