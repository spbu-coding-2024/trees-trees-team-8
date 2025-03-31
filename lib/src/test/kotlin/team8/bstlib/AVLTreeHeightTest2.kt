package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.math.log2
import kotlin.test.assertTrue

class AVLTreeHeightTest2 {

    @Test
    fun `tree height should be logarithmic`() {
        val tree = AVLTree(100, "Base")
        (1..1000).filter { it != 100 }
            .shuffled()
            .forEach { tree.insert(it, "v$it") }

        val maxHeight = 1.44 * log2(1001.toDouble())
        assertTrue(tree.getTreeHeight() <= maxHeight)
    }
}