package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.*

class BSTTest {
    @Test
    fun `test basic insertion and search`() {
        val tree = BST<Int, String>()
        tree.insert(30, "A")
        tree.insert(20, "B")
        tree.insert(10, "C")

        assertEquals("A", tree.find(30)?.value)
        assertEquals("B", tree.find(20)?.value)
        assertEquals("C", tree.find(10)?.value)
    }

    @Test
    fun `test complex operations`() {
        val tree = BST<Int, String>(100, "A")

        // Insertions
        listOf(50, 150, 25, 75, 125, 175).forEach {
            tree.insert(it, "Value$it")
        }

        // Verify size and contains
        assertEquals(7, tree.keys.size)
        assertTrue(tree.keys.containsAll(listOf(25, 50, 75, 100, 125, 150, 175)))

        // Deletion
        tree.remove(100)
        assertEquals(6, tree.keys.size)
        assertNull(tree.find(100))
    }

    @Test
    fun `test node deletion`() {
        val tree = BST<Int, String>(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        tree.remove(30)

        assertNull(tree.find(30))
        assertNotNull(tree.find(50))
        assertNotNull(tree.find(70))
    }

    @Test
    fun `test duplicate insertion`() {
        val tree = BST<Int, String>(50, "Root")

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
    fun `test edge cases with single node`() {
        val tree = BST<Int, String>(100, "Single")

        assertEquals(1, tree.keys.size)
        tree.remove(100)
        assertNull(tree.find(100))
        assertEquals(0, tree.keys.size)
    }

    @Test
    fun `test multiple insertions and deletions`() {
        val tree = BST<Int, String>(100, "A")

        // Insert 50 elements
        repeat(50) { i ->
            tree.insert(i, "Value$i")
        }

        // Remove every 3rd element
        repeat(50) { i ->
            if (i % 3 == 0) tree.remove(i)
        }

        // Verify remaining elements
        repeat(50) { i ->
            if (i % 3 == 0) {
                assertNull(tree.find(i))
            } else {
                assertNotNull(tree.find(i))
            }
        }
    }

    @Test
    fun `test deletion of root node`() {
        val tree = BST<Int, String>(50, "Root")
        tree.insert(30, "A")
        tree.insert(70, "B")

        val deletedNode = tree.remove(50)

        assertNotNull(deletedNode)
        assertNull(tree.find(50))
        assertEquals(2, tree.keys.size)
        assertTrue(tree.keys.containsAll(listOf(30, 70)))
    }

    @Test
    fun `test large dataset`() {
        val tree = BST<Int, String>(500, "Center")

        // Insert 1000 elements
        (1..1000).filter { it != 500 }.forEach {
            tree.insert(it, "Val$it")
        }

        // Remove range 250-750
        (250..750).forEach(tree::remove)

        // Verify
        (1..1000).forEach {
            if (it in 250..750) {
                assertNull(tree.find(it))
            } else {
                assertNotNull(tree.find(it))
            }
        }
    }

    @Test
    fun `test keys are sorted`() {
        val tree = BST<Int, String>()
        val values = listOf(50, 30, 70, 20, 40, 60, 80)
        
        values.shuffled().forEach {
            tree.insert(it, "Value$it")
        }

        assertEquals(values.sorted(), tree.keys.sorted())
    }

    @Test
    fun `test empty tree`() {
        val tree = BST<Int, String>()
        
        assertNull(tree.find(10))
        assertEquals(0, tree.keys.size)
    }
}
