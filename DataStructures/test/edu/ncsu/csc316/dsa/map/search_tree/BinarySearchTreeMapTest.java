package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for BinarySearchTreeMap
 * Checks the expected outputs of the Map and Tree abstract data type behaviors when using
 * an linked binary tree data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class BinarySearchTreeMapTest {
	/** Field for tree */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a binary search tree map before each test case executes
     */
    @Before
    public void setUp() {
        tree = new BinarySearchTreeMap<Integer, String>();
    }
      
    /**
     * Sample tree to help with testing
     *
     * Visually:
     *                    six
     *                /         \
     *             four         ten
     *            /   \         /
     *         one   five     eight
     *           \           /     \
     *            two     seven    nine
     *             \
     *             three    
     */  
    private void createTree() {
    	tree.put(6, "six");
        tree.put(10, "ten");
        tree.put(4, "four");
        tree.put(5, "five");
        tree.put(8, "eight");
        tree.put(9, "nine");
        tree.put(1, "one");
        tree.put(2, "two");
        tree.put(3, "three");
        tree.put(7, "seven");       
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        // Verify the tree is empty
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        // Add a node and check for it
        tree.put(1, "one");
        assertEquals("one", tree.get(1));
        assertEquals(1, tree.size());
        assertFalse(tree.isEmpty());
        assertEquals(1, (int)tree.root().getElement().getKey());
        
        // Remove the single node and createTree()
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        createTree();
        // Check that the tree has the appropriate number of nodes
        assertEquals(10, tree.size());
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        createTree();
        assertEquals(10, tree.size());
        // Check that all keys were added
        assertFalse(tree.isEmpty());
        assertEquals(6, (int)tree.root().getElement().getKey());
        assertEquals("one", tree.get(1));
        assertEquals("two", tree.get(2));
        assertEquals("three", tree.get(3));
        assertEquals("four", tree.get(4));
        assertEquals("five", tree.get(5));
        assertEquals("six", tree.get(6));
        assertEquals("seven", tree.get(7));
        assertEquals("eight", tree.get(8));
        assertEquals("nine", tree.get(9));
        assertEquals("ten", tree.get(10));
        // Check for a key that wasn't added
        assertNull(tree.get(11));
    }

    /**
     * Test the output of the remove(k) behavior
     */ 
    @Test
    public void testRemove() {
        // Add one node
    	tree.put(1,  "one");
        assertEquals(1, tree.size());        
        // Remove a nonexistent node
        assertNull(tree.remove(10));
        assertEquals(1, tree.size());
        // Remove the single node
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        
        // Create a tree
        createTree();
        assertEquals(10, tree.size());
        // Remove a node with two children
        assertEquals("eight", tree.remove(8));
        assertEquals(9, tree.size());
        // Remove a node with one left child
        assertEquals("nine", tree.remove(9));
        assertEquals(8, tree.size());
        // Remove a node with one right child
        assertEquals("two", tree.remove(2));
        assertEquals(7, tree.size());
        // Remove a leaf
        assertEquals("three", tree.remove(3));
        assertEquals(6, tree.size());
        // Remove the root
        assertEquals("six", tree.remove(6));
        assertEquals(5, tree.size());
    }
    
    /**
     * Test the output of the entryset(k) behavior
     */ 
    @Test
    public void testEntryset() {
    	assertNull(tree.put(3, "string3"));
        assertNull(tree.put(5, "string5"));
        assertNull(tree.put(2, "string2"));
        assertNull(tree.put(4, "string4"));
        assertNull(tree.put(1, "string1"));
    	
    	Iterator<Map.Entry<Integer, String>> it = tree.entrySet().iterator();
    	assertTrue(it.hasNext());    	
    }
}