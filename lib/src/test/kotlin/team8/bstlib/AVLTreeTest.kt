package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.math.log2
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull
import kotlin.test.assertTrue

class AVLTreeTest {
    @Test
    fun `test balance after left-heavy insertion`() {
        val tree = AVLTree(30, "A")
        tree.insert(20, "B")
        tree.insert(10, "C")


        assertEquals("B", tree.find(20)?.value)
        assertEquals("A", tree.find(30)?.value)
        assertEquals("C", tree.find(10)?.value)
    }

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

    @Test
    fun `test node deletion`() {
        val tree = AVLTree(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        tree.remove(30)

        assertNull(tree.find(30))
        assertTrue(tree.find(50) != null)
        assertTrue(tree.find(70) != null)
    }

    @Test
    fun `test duplicate insertion returns null and doesn't modify data`() {
        val tree = AVLTree(50, "Root")

        tree.insert(30, "A")
        assertEquals(2, tree.keys.size)
        assertEquals(2, tree.values.size)

        val duplicateInsertResult = tree.insert(30, "B")

        assertNull(duplicateInsertResult)
        assertEquals(2, tree.keys.size)
        assertEquals(2, tree.values.size)

        assertEquals("A", tree.find(30)?.value)
    }

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

    @Test
    fun `handle edge cases with single node`() {
        val tree = AVLTree(100, "Single")

        assertEquals(1, tree.getHeight(100))

        tree.remove(100)
        assertNull(tree.find(100))
    }

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

    @Test
    fun `tree height should be logarithmic`() {
        val tree = AVLTree(100, "Base")
        (1..1000).filter { it != 100 }
            .shuffled()
            .forEach { tree.insert(it, "v$it") }

        val maxHeight = 1.44 * log2(1001.toDouble())
        assertTrue(tree.getTreeHeight() <= maxHeight)
    }

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

    @Test
    fun `test balance after right-heavy insertion`() {
        val tree = AVLTree(10, "Root")
        tree.insert(20, "A")
        tree.insert(30, "B")

        assertEquals("A", tree.find(20)?.value)
        assertEquals("Root", tree.find(10)?.value)
        assertEquals("B", tree.find(30)?.value)
    }

    @Test
    fun `test balance after right-heavy insertion with sort`() {
        val tree = AVLTree(10, "Root")
        tree.insert(20, "A")
        tree.insert(30, "B")

        assertEquals(listOf(10, 20, 30), tree.keys.sorted())
        assertEquals("A", tree.find(20)?.value)
        assertEquals("Root", tree.find(10)?.value)
        assertEquals("B", tree.find(30)?.value)
    }

    @Test
    fun `test deletion of root node via public API`() {
        val tree = AVLTree(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        val deletedNode = tree.remove(50)

        assertNotNull(deletedNode)
        assertNull(tree.find(50))
        assertEquals(2, tree.keys.size)
        assertTrue(tree.keys.containsAll(listOf(30, 70)))
    }

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