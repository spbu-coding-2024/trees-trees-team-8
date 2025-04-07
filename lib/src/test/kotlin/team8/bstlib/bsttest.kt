package team8.bstlib

import org.junit.jupiter.api.Test
import kotlin.test.*

class BSTTest {
   
    @Test
    fun `create tree with root`() {
        val tree = BST.createWithRoot(50, "Root")
        assertEquals(50, tree.getRootForTesting()?.key)
        assertEquals("Root", tree.getRootForTesting()?.value)
        assertEquals(1, tree.keys.size)
    }


    @Test
    fun `duplicate insert returns existing node`() {
        val tree = BST.createWithRoot(50, "Original")
        val result = tree.insert(50, "New")
        
        assertEquals("Original", result?.value)
        assertEquals("Original", tree.find(50)?.value)
    }

    @Test
    fun `remove leaf node`() {
        val tree = BST.createWithRoot(50, "Root")
        tree.insert(30, "Left")
        val removed = tree.remove(30)
        
        assertEquals("Left", removed?.value)
        assertNull(tree.find(30))
        assertEquals(1, tree.keys.size)
    }

    @Test
    fun `remove node with one child`() {
        val tree = BST.createWithRoot(50, "Root")
        tree.insert(30, "Left")
        tree.insert(20, "LeftLeft")
        tree.remove(30)
        
        assertNull(tree.find(30))
        assertNotNull(tree.find(20))
        assertEquals(2, tree.keys.size)
    }

    @Test
    fun `remove node with two children`() {
        val tree = BST.createWithRoot(50, "Root")
        tree.insert(30, "Left")
        tree.insert(70, "Right")
        val removed = tree.remove(50)
        
        assertEquals("Root", removed?.value)
        assertNull(tree.find(50))
        assertNotNull(tree.find(30))
        assertNotNull(tree.find(70))
        assertEquals(2, tree.keys.size)
    }

    @Test
    fun `get min and max values`() {
        val tree = BST.createWithRoot(50, "Root")
        tree.insert(30, "Left")
        tree.insert(70, "Right")
        tree.insert(20, "LeftLeft")
        tree.insert(80, "RightRight")
        
        assertEquals(20, tree.getMin())
        assertEquals(80, tree.getMax())
    }
}
