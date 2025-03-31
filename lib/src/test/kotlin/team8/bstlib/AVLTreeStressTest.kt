package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertNull
import kotlin.test.assertNotNull

class AVLTreeStressTest {

    @Test
    fun `stress test with 1000 elements`() {
        val tree = AVLTree(500, "Center")

        (1..1000).filter { it != 500 }.forEach {
            tree.insert(it, "Val$it")
        }

        (250..750 step 2).forEach(tree::remove)

        (1..1000).forEach {
            if (it in 250..750 step 2) {
                assertNull(tree.find(it))
            } else {
                assertNotNull(tree.find(it))
            }
        }
    }
}