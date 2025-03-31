// AVLTreeBalanceTest.kt
package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertTrue
import kotlin.test.assertEquals
import kotlin.test.assertNull



class AVLTreeBalanceTest2 {
    @Test
    fun `test balance after complex operations`() {
        val tree = AVLTree(100, "A")

        // Вставка
        listOf(50, 150, 25, 75, 125, 175).forEach {
            tree.insert(it, "Value$it")
        }

        // Проверка свойств
        assertEquals(7, tree.keys.size)
        assertTrue(tree.keys.containsAll(listOf(25, 50, 75, 100, 125, 150, 175)))

        // Удаление
        tree.remove(100)
        assertEquals(6, tree.keys.size)
        assertNull(tree.find(100))
    }
}