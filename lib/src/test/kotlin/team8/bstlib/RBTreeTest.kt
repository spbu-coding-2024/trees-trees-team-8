package team8.bstlib

import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

class RBTreeTest {
    //TODO: less tests, check keys/values
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

    //basic cases
    @Test
    @DisplayName("insert when parent is black")
    fun insertWhenParentIsBlack() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        testTree.find(2)?.color = Color.BLACK
        testTree.insert(0, 2)
        testTree.find(0)?.color = Color.BLACK
        val expectedResult = RBTreeNode(Color.RED, 3, 2)
        expectedResult.parent = testTree.find(1)
        val actualResult = testTree.insert(3, 2)

        assert(testTree.find(2)?.color == Color.BLACK)
        assert(testTree.find(0)?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when parent is red and node is right child")
    fun insertWhenParentIsRedAndNodeIsRightChild() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        testTree.insert(0, 2)

        val expectedResult = RBTreeNode(Color.RED, 3, 2)
        expectedResult.parent = testTree.find(1)
        val actualResult = testTree.insert(3, 2)

        assert(testTree.find(2)?.color == Color.BLACK)
        assert(testTree.find(0)?.color == Color.BLACK)
        assert(testTree.find(1)?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when parent is red node is and left child")
    fun insertWhenParentIsRedAndNodeIsLeftChild() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        testTree.insert(0, 2)

        val expectedResult = RBTreeNode(Color.RED, -1, 2)
        expectedResult.parent = testTree.find(1)
        val actualResult = testTree.insert(-1, 2)

        assert(testTree.find(2)?.color == Color.BLACK)
        assert(testTree.find(0)?.color == Color.BLACK)
        assert(testTree.find(1)?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when uncle is black node is and left child")
    fun insertWhenUncleIsBlackAndNodeIsLeftChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(1, 2)
        val expectedResult = RBTreeNode(Color.RED, 0, 2)
        val actualResult = testTree.insert(0, 2)

        assert(testTree.find(1)?.parent == testTree.find(10))
        assert(testTree.find(1)?.leftChild == testTree.find(0))
        assert(testTree.find(1)?.rightChild == testTree.find(5))
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when uncle is black and node is right child")
    fun insertWhenUncleIsBlackAndNodeIsRightChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(1, 2)
        val expectedResult = RBTreeNode(Color.BLACK, 2, 2)
        val actualResult = testTree.insert(2, 2)

        assert(testTree.find(2)?.parent == testTree.find(10))
        assert(testTree.find(2)?.leftChild == testTree.find(1))
        assert(testTree.find(2)?.rightChild == testTree.find(5))
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when uncle is black, parent - right, node - right")
    fun insertWhenUncleBlackParentRightNodeRight() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(30, 2)
        val expectedResult = RBTreeNode(Color.RED, 40, 2)
        val actualResult = testTree.insert(40, 2)

        assert(testTree.find(30)?.parent == testTree.find(10))
        assert(testTree.find(30)?.leftChild == testTree.find(20))
        assert(testTree.find(30)?.rightChild == testTree.find(40))
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("insert when uncle is black, parent - right, node - left")
    fun insertWhenUncleBlackParentRightNodeLeft() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(30, 2)
        val expectedResult = RBTreeNode(Color.BLACK, 25, 2)
        val actualResult = testTree.insert(25, 2)

        assert(testTree.find(25)?.parent == testTree.find(10))
        assert(testTree.find(25)?.leftChild == testTree.find(20))
        assert(testTree.find(25)?.rightChild == testTree.find(30))
        assert(expectedResult == actualResult)
    }


    //remove

    //trivial cases
    @Test
    @DisplayName("remove in tree without root")
    fun removeInTreeWithoutRoot() {
        val testTree = RBTree(1, 2)
        testTree.remove(1)
        val expectedResult = null
        val actualResult = testTree.remove(1)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove root")
    fun removeRoot() {
        val testTree = RBTree(1, 2)
        val expectedResult = testTree.find(1)
        val actualResult = testTree.remove(1)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove right red root child")
    fun removeRightRedRootChild() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        val expectedResult = testTree.find(2)
        val actualResult = testTree.remove(2)

        assert(testTree.find(1)?.rightChild == null)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove left red root child")
    fun removeLeftRedRootChild() {
        val testTree = RBTree(1, 2)
        testTree.insert(0, 2)
        val expectedResult = testTree.find(0)
        val actualResult = testTree.remove(0)

        assert(testTree.find(1)?.leftChild == null)
        assert(expectedResult == actualResult)
    }

    //basic cases
    @Test
    @DisplayName("remove left black root child without children")
    fun removeLeftBlackChildWithoutChildren() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        testTree.insert(0, 2)
        testTree.find(2)?.color = Color.BLACK
        testTree.find(0)?.color = Color.BLACK
        val expectedResult = testTree.find(0)
        val actualResult = testTree.remove(0)

        assert(testTree.find(1)?.leftChild == null)
        assert(testTree.find(1)?.rightChild?.color == Color.RED)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove right black root child without children")
    fun removeRightBlackChildWithoutChildren() {
        val testTree = RBTree(1, 2)
        testTree.insert(2, 2)
        testTree.insert(0, 2)
        testTree.find(2)?.color = Color.BLACK
        testTree.find(0)?.color = Color.BLACK
        val expectedResult = testTree.find(2)
        val actualResult = testTree.remove(2)

        assert(testTree.find(1)?.rightChild == null)
        assert(testTree.find(1)?.leftChild?.color == Color.RED)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove left black root child with left child")
    fun removeLeftBlackRootChildWithLeftChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(1, 2)
        val expectedResult = testTree.find(5)
        val actualResult = testTree.remove(5)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove left black root child with right child")
    fun removeLeftBlackRootChildWithRightChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(6, 2)
        val expectedResult = testTree.find(5)
        val actualResult = testTree.remove(5)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove right black root child with right child")
    fun removeRightBlackRootChildWithRightChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(30, 2)
        val expectedResult = testTree.find(20)
        val actualResult = testTree.remove(20)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove left black root child with left child")
    fun removeRightBlackRootChildWithLeftChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(20, 2)
        testTree.insert(5, 2)
        testTree.insert(15, 2)
        val expectedResult = testTree.find(20)
        val actualResult = testTree.remove(20)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove root with 2 red children")
    fun removeRootWith2RedChildren() {
        val testTree = RBTree(1, 2)
        testTree.insert(0, 2)
        testTree.insert(2, 2)
        val expectedResult = testTree.find(1)
        val actualResult = testTree.remove(1)

        assert(testTree.find(2)?.color == Color.BLACK)
        assert(testTree.find(2)?.rightChild == null)
        assert(testTree.find(2)?.leftChild == testTree.find(0))
        assert(testTree.find(0)?.color == Color.RED)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove right black root child with both children")
    fun removeRightBlackRootChildWithBothChildren() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(30, 2)
        testTree.insert(15, 2)
        val expectedResult = testTree.find(20)
        val actualResult = testTree.remove(20)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(testTree.find(30)?.leftChild?.color == Color.RED)
        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove left black root child with both children")
    fun removeLeftBlackRootChildWithBothChildren() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(6, 2)
        testTree.insert(4, 2)
        val expectedResult = testTree.find(5)
        val actualResult = testTree.remove(5)

        assert(testTree.find(10)?.leftChild?.color == Color.BLACK)
        assert(testTree.find(10)?.rightChild?.color == Color.BLACK)
        assert(testTree.find(6)?.leftChild?.color == Color.RED)
        assert(expectedResult == actualResult)
    }

    //advanced cases
    @Test
    @DisplayName("remove when replace node has right child")
    fun removeWhenReplaceNodeHasRightChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(6, 2)
        testTree.insert(4, 2)
        testTree.insert(15, 2)
        testTree.insert(30, 2)
        testTree.insert(40, 2)
        val expectedResult = testTree.find(20)
        val actualResult = testTree.remove(20)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("remove when replace node has left child")
    fun removeWhenReplaceNodeHasLeftChild() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(6, 2)
        testTree.insert(4, 2)
        testTree.insert(15, 2)
        testTree.insert(30, 2)
        testTree.insert(16, 2)
        val expectedResult = testTree.find(10)
        val actualResult = testTree.remove(10)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("left child brother red")
    fun leftChildBrotherRed() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(15, 2)
        testTree.insert(30, 2)
        testTree.find(20)?.color = Color.RED
        testTree.find(15)?.leftChild?.color = Color.BLACK
        testTree.find(30)?.rightChild?.color = Color.BLACK

        val expectedResult = testTree.find(5)
        val actualResult = testTree.remove(5)

        assert(expectedResult == actualResult)
    }

    @Test
    @DisplayName("right child brother red")
    fun rightChildBrotherRed() {
        val testTree = RBTree(10, 2)
        testTree.insert(5, 2)
        testTree.insert(20, 2)
        testTree.insert(4, 2)
        testTree.insert(6, 2)
        testTree.find(5)?.color = Color.RED
        testTree.find(4)?.leftChild?.color = Color.BLACK
        testTree.find(6)?.rightChild?.color = Color.BLACK

        val expectedResult = testTree.find(20)
        val actualResult = testTree.remove(20)

        assert(expectedResult == actualResult)
    }

    //cannot create cases (at the moment) when brother can be black and have 2 black / 1 red and 1 black children
}