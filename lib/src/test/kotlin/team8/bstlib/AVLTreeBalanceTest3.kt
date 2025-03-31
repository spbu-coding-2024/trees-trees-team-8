package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue

class AVLTreeBalanceTest3 {

    @Test
    fun `all nodes should have balance factor between -1 and 1`() {
        val tree = AVLTree(50, "Root")

        listOf(30, 70, 20, 40, 60, 80).forEach {
            tree.insert(it, "Value$it")
        }

        fun checkBalance(key: Int) {
            assertTrue(tree.getBalanceFactor(key) in -1..1)
        }

        tree.keys.forEach(::checkBalance)
    }
}