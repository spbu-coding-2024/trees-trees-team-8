package team8.bstlib

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RBTreeTest {
    //insert

    //trivial cases
    @Test
    @DisplayName("insert into tree without root")
    fun insertIntoTreeWithoutRoot() {
        val testTree = RBTree(1, 2)
        testTree.remove(1)
        val expectedResult = RBTreeNode(Color.BLACK, 1, 2)
        val actualResult = testTree.insert(1, 2)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert with lesser key")
    fun insertWithLesserKey() {
        val testTree = RBTree(1, 2)
        val expectedResult = RBTreeNode(Color.RED, 0, 2)
        expectedResult.parent = testTree.find(1)
        val actualResult = testTree.insert(0, 2)

        assert(expectedResult == actualResult)
        assert(expectedResult.parent?.leftChild == actualResult)
    }

    @Test
    @DisplayName("insert with greater key")
    fun insertWithGreaterKey() {
        val testTree = RBTree(1, 2)
        val expectedResult = RBTreeNode(Color.RED, 2, 2)
        expectedResult.parent = testTree.find(1)
        val actualResult = testTree.insert(2, 2)

        assert(expectedResult == actualResult)
        assert(expectedResult.parent?.rightChild == actualResult)
    }

    @Test
    @DisplayName("insert existing root")
    fun insertExistingRoot() {
        val testTree = RBTree(1, 2)
        val expectedResult = null
        val actualResult = testTree.insert(1, 2)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert existing right node")
    fun insertExistingRightNode() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        val expectedResult = null
        val actualResult = testTree.insert(2, 2)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert existing left node")
    fun insertExistingLeftNode() {
        val testTree = RBTree(1, 2)
        testTree.insert(0, 2)
        val expectedResult = null
        val actualResult = testTree.insert(0, 2)

        assert(expectedResult == actualResult)
    }

}