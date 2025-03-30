package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class AVLTreeBalanceTest {

    @Test
    fun `test balance after left-heavy insertion`() {
        // Создаем дерево и добавляем элементы с левым перевесом
        val tree = AVLTree(30, "A")
        tree.insert(20, "B")
        tree.insert(10, "C")  // Должен выполнить правый поворот

        //    20
        //  /   \
        //10    30
        assertEquals("B", tree.find(20)?.value)
        assertEquals("A", tree.find(30)?.value)
        assertEquals("C", tree.find(10)?.value)
    }
}