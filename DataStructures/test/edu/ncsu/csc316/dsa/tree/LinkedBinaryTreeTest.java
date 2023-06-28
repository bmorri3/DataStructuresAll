package edu.ncsu.csc316.dsa.tree;

import static org.junit.Assert.*;

import java.util.Iterator;

//import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.tree.LinkedBinaryTree.BinaryTreeNode;

/**
 * Test class for LinkedBinaryTree
 * Checks the expected outputs of the BinaryTree abstract data type behaviors when using
 * a linked data structure to store elements
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class LinkedBinaryTreeTest {

	/** Tree field */
    private LinkedBinaryTree<String> tree;
    /** A position to set */
    private Position<String> one;
    /** A position to set */
    private Position<String> two;
    /** A position to set */
    private Position<String> three;
    /** A position to set */
    private Position<String> four;
    /** A position to set */
    private Position<String> five;
    /** A position to set */
    private Position<String> six;
    /** A position to set */
    private Position<String> seven;
    /** A position to set */
    private Position<String> eight;
    /** A position to set */
    private Position<String> nine;
    /** A position to set */
    private Position<String> ten;
    /** A position to set */
    private Position<String> eleven;
    
//    /**
//     * Helper class to create an invalid position to help test validate(p)
//     * @author Ben Morris
//     *
//     * @param <E> Element to create invalid position for
//     */
//    private class InvalidPosition<E> implements Position<E> {
//
//        @Override
//        public E getElement() {
//            return null;
//        }
//        
//    }

    /**
     * Create a new instance of a linked binary tree before each test case executes
     */       
    @Before
    public void setUp() {
        tree = new LinkedBinaryTree<String>(); 
    }
    
    /**
     * Sample tree to help with testing
     *
     * One
     * -> Two
     *   -> Six
     *   -> Ten
     *     -> Seven
     *     -> Five
     * -> Three
     *   -> Four
     *     -> Eight
     *     -> Nine
     * 
     * Or, visually:
     *                    one
     *                /        \
     *             two          three
     *            /   \            /
     *         six   ten          four
     *              /   \        /     \
     *            seven  five  eight nine    
     */  
    private void createTree() {
        one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        three = tree.addRight(one, "three");
        six = tree.addLeft(two, "six");
        ten = tree.addRight(two, "ten");
        four = tree.addLeft(three, "four");
        seven = tree.addLeft(ten, "seven");
        five = tree.addRight(ten, "five");
        eight = tree.addLeft(four, "eight");
        nine = tree.addRight(four, "nine");
    }
    
    /**
     * Test the output of the set(p,e) behavior
     */     
    @Test
    public void testSet() {
        createTree();
        assertEquals(tree.set(eight, "eightB"), "eight");
        assertEquals(tree.sibling(nine).getElement(), "eightB");
    }
    
    /**
     * Test the output of the size() behavior
     */     
    @Test
    public void testSize() {
    	// Test empty tree
        assertTrue(tree.isEmpty());
        assertEquals(tree.size(), 0);
        
        // Test tree after filling
        createTree();
        assertEquals(tree.size(), 10);
        
        // Test tree after removing
        assertEquals(tree.remove(nine), "nine");
        assertEquals(tree.size(), 9);
    }
    
    /**
     * Test the output of the numChildren(p) behavior
     */     
    @Test
    public void testNumChildren() {
        createTree();

        assertEquals(2, tree.numChildren(one));
        assertEquals(tree.remove(eight), "eight");
        assertEquals(1, tree.numChildren(four));
        assertEquals(0, tree.numChildren(nine));
    }

    /**
     * Test the output of the parent(p) behavior
     */   
    @Test
    public void testParent() {
      	createTree();
        BinaryTreeNode<String> node = tree.validate(nine);
        assertEquals(node.getParent(), four);
        node = tree.validate(four);
        assertEquals(node.getParent(), three);
        node = tree.validate(three);
        assertEquals(node.getParent(), one);
        node = tree.validate(one);
        assertEquals(node.getParent(), null);
        assertNull(node.getParent());
    }

    /**
     * Test the output of the sibling behavior
     */     
    @Test
    public void testSibling() {
        createTree();
        
        // Test root
        assertNull(tree.sibling(one));
        
        // Test siblings
        assertEquals(tree.sibling(nine), eight);
        assertEquals(tree.sibling(six), ten);
        assertEquals(tree.sibling(five), seven);
        assertEquals(tree.sibling(two), three);
        
        // Test non-sibling
        assertNotEquals(tree.sibling(ten), four);
        assertNotEquals(tree.sibling(four), two);        
        
        // Test nonexistent position
    	try{
            tree.sibling(eleven);
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
    }

    /**
     * Test the output of the isInternal behavior
     */     
    @Test
    public void testIsInternal() {
        createTree();
        assertTrue(tree.isInternal(four));
        assertFalse(tree.isInternal(eight));
    }

    /**
     * Test the output of the isLeaf behavior
     */     
    @Test
    public void testIsLeaf() {
        createTree();
        assertFalse(tree.isLeaf(four));
        assertTrue(tree.isLeaf(eight));
    }

    /**
     * Test the output of the isRoot(p)
     */     
    @Test
    public void testIsRoot() {
        createTree();
        assertFalse(tree.isRoot(four));
        assertTrue(tree.isRoot(one));
    }
    
    /**
     * Test the output of the preOrder traversal behavior
     */     
    @Test
    public void testPreOrder() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        createTree();

        Iterable<Position<String>> iterablePositions = tree.preOrder();      
        Iterator<Position<String>> it = iterablePositions.iterator();
    	
    	// Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    	
    	tree.toString();
    	
    	assertTrue(it.hasNext());
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("six", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        
        // Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * Test the output of the postOrder traversal behavior
     */     
    @Test
    public void testPostOrder() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        createTree();
       
        Iterable<Position<String>> iterablePositions = tree.postOrder();        
        Iterator<Position<String>> it = iterablePositions.iterator();
    	
    	// Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    	
    	assertTrue(it.hasNext());
        assertEquals("six", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("one", it.next().getElement());
        
        // Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Test the level-order traversal of a Binary Tree
     */
    @Test
    public void testLevelOrder() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        createTree();
       
        Iterable<Position<String>> iterablePositions = tree.levelOrder();        
        Iterator<Position<String>> it = iterablePositions.iterator();
    	
    	// Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    	
    	assertTrue(it.hasNext());
        assertEquals("one", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("three", it.next().getElement());
        assertEquals("six", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        
        // Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }
    
    /**
     * Test the output of the inOrder traversal behavior
     */     
    @Test
    public void testInOrder() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        createTree();
        Iterable<Position<String>> iterablePositions = tree.inOrder();
        
        Iterator<Position<String>> it = iterablePositions.iterator();
    	
    	// Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    	
    	assertTrue(it.hasNext());
        assertEquals("six", it.next().getElement());
        assertEquals("two", it.next().getElement());
        assertEquals("seven", it.next().getElement());
        assertEquals("ten", it.next().getElement());
        assertEquals("five", it.next().getElement());
        assertEquals("one", it.next().getElement());
        assertEquals("eight", it.next().getElement());
        assertEquals("four", it.next().getElement());
        assertEquals("nine", it.next().getElement());
        assertEquals("three", it.next().getElement());
        
        // Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * Test the output of the Binary Tree ADT behaviors on an empty tree
     */     
    @Test
    public void testEmptyTree() {
        assertTrue(tree.size() == 0);
        createTree();
        assertFalse(tree.size() == 0);
    }

    /**
     * Test the output of the addLeft(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddLeft() {
    	// Create a tree
    	one = tree.addRoot("one");
        two = tree.addLeft(one, "two");
        BinaryTreeNode<String> node = tree.validate(one);
        assertEquals(node.getLeft(), two);
        
        // Add another root
        try{
        	tree.addRoot("eleven");
            fail("An IllegalArtgumentException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        // Try to add another left node
        try{
            tree.addLeft(one, "four");
            fail("An IllegalArtgumentException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }
    
    /**
     * Test the output of the addRight(p,e) behavior, including expected exceptions
     */      
    @Test
    public void testAddRight() {
    	// Create a tree
    	one = tree.addRoot("one");
        three = tree.addRight(one, "three");
        BinaryTreeNode<String> node = tree.validate(one);
        assertEquals(node.getRight(), three);
        
        // Try to add another left node
        try{
            tree.addRoot("eleven");
            fail("An IllegalArtgumentException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
    }   
    
    /**
     * Test the output of the remove(p) behavior, including expected exceptions
     */         
    @Test
    public void testRemove() {
    	assertTrue(tree.isEmpty());
        
    	// Test removing an invalid position
        try{
            tree.remove(one);
            fail("An IllegalArtgumentException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
    	createTree();
        assertEquals(tree.size(), 10);
        
        // Test removing a position with two children
        try{
            tree.remove(one);
            fail("An IllegalArtgumentException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }
        
        // Remove all positions
        assertEquals(tree.remove(eight), "eight");
        assertEquals(tree.size(), 9);
        
        assertEquals(tree.remove(four), "four");
        assertEquals(tree.size(), 8);
                
        assertEquals(tree.remove(nine), "nine");
        assertEquals(tree.size(), 7);
        
        assertEquals(tree.remove(five), "five");
        assertEquals(tree.size(), 6);
        
        assertEquals(tree.remove(ten), "ten");
        assertEquals(tree.size(), 5);
        
        assertEquals(tree.remove(seven), "seven");
        assertEquals(tree.size(), 4);
        
        assertEquals(tree.remove(six), "six");
        assertEquals(tree.size(), 3);
        
        assertEquals(tree.remove(three), "three");
        assertEquals(tree.size(), 2);
        
        assertEquals(tree.remove(one), "one");
        assertEquals(tree.size(), 1);
                
        assertEquals(tree.remove(two), "two");
        assertEquals(tree.size(), 0);
        assertTrue(tree.isEmpty());
        
        // Test removing root with right child
        one = tree.addRoot("one");
        three = tree.addRight(one, "three");
        assertEquals(tree.remove(one), "one");
    }
}