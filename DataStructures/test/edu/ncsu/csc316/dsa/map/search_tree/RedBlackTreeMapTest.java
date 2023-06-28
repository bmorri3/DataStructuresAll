package edu.ncsu.csc316.dsa.map.search_tree;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for RedBlackTreeMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a red-black tree data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 */
public class RedBlackTreeMapTest {
	/** Tree field */
    private BinarySearchTreeMap<Integer, String> tree;
    
    /**
     * Create a new instance of a red-black tree-based map before each test case executes
     */  
    @Before
    public void setUp() {
        tree = new RedBlackTreeMap<Integer, String>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
    	assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());
        
        assertNull(tree.put(5, "five"));
        assertEquals(1, tree.size());
        assertNull(tree.put(10, "ten"));
        assertEquals(2, tree.size());
        assertNull(tree.put(2, "two"));
        assertEquals(3, tree.size());
        assertNull(tree.put(12, "twelve"));
        assertEquals(4, tree.size());
        assertNull(tree.put(20, "twenty"));
        assertEquals(5, tree.size());
        assertNull(tree.put(15, "fifteen"));
        assertEquals(6, tree.size());
        assertNull(tree.put(23, "twenty-three"));
        assertEquals(7, tree.size());
        assertNull(tree.put(22, "twenty-two"));
        assertEquals(8, tree.size());
        assertNull(tree.put(25, "twenty-five"));
        assertEquals(9, tree.size());
        assertNull(tree.put(1, "one"));
        assertEquals(10, tree.size());
        assertNull(tree.put(0, "zero"));
        assertEquals(11, tree.size());    
    }
    
    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
    	assertTrue(tree.isEmpty());        
        // Create tree
        assertNull(tree.put(5, "five"));
        assertNull(tree.put(10, "ten"));
        assertNull(tree.put(2, "two"));
        assertNull(tree.put(12, "twelve"));
        assertNull(tree.put(20, "twenty"));
        assertNull(tree.put(15, "fifteen"));
        assertNull(tree.put(23, "twenty-three"));
        assertNull(tree.put(22, "twenty-two"));
        assertNull(tree.put(25, "twenty-five"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(0, "zero"));
        assertEquals(11, tree.size());
        
        // Test existing nodes
        assertEquals("five", tree.get(5));
        assertEquals("ten", tree.get(10));
        assertEquals("two", tree.get(2));
        assertEquals("twelve", tree.get(12));
        assertEquals("twenty", tree.get(20));
        assertEquals("fifteen", tree.get(15));
        assertEquals("twenty-three", tree.get(23));
        assertEquals("twenty-two", tree.get(22));
        assertEquals("twenty-five", tree.get(25));
        assertEquals("one", tree.get(1));
        assertEquals("zero", tree.get(0));
        
        // Test wrong value for key
        assertNotEquals("one", tree.get(0));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
    	assertTrue(tree.isEmpty());
        // Create tree
        assertNull(tree.put(5, "five"));       
        assertNull(tree.put(10, "ten"));
        assertEquals(10, (int)tree.right(tree.root()).getElement().getKey());
        assertNull(tree.left(tree.root()).getElement());
        assertNull(tree.put(2, "two"));
        assertEquals(2, (int)tree.left(tree.root()).getElement().getKey());
        assertNull(tree.put(12, "twelve"));
        assertEquals(12, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertNull(tree.put(20, "twenty"));       
        assertNull(tree.put(15, "fifteen"));
        assertNull(tree.put(23, "twenty-three"));
        assertNull(tree.put(22, "twenty-two"));
        assertNull(tree.put(25, "twenty-five"));
        assertNull(tree.put(1, "one"));
        assertNull(tree.put(0, "zero"));
        assertEquals(11, tree.size());
        // Verify tree
        assertEquals(12, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(0, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(2, (int)tree.right(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(20, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(15, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(23, (int)tree.right(tree.right(tree.root())).getElement().getKey());
        assertEquals(22, (int)tree.left(tree.right(tree.right(tree.root()))).getElement().getKey());
        assertEquals(25, (int)tree.right(tree.right(tree.right(tree.root()))).getElement().getKey());
        
        // Test remove nonexistent key
        assertNull(tree.remove(50));
        
        // Test remove leaf
        assertEquals("twenty-two", tree.remove(22));
        assertEquals(10, tree.size());
        
        // Test remove root
        assertEquals("twelve", tree.remove(12));
        assertEquals(9, tree.size());
        
        // Test remove other nodes
        assertEquals("two", tree.remove(2));
        assertEquals(8, tree.size());
        // Verify tree
        assertEquals(15, (int)tree.root().getElement().getKey());
        assertEquals(5, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(1, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(0, (int)tree.left(tree.left(tree.left(tree.root()))).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(23, (int)tree.right(tree.root()).getElement().getKey());
        assertEquals(20, (int)tree.left(tree.right(tree.root())).getElement().getKey());
        assertEquals(25, (int)tree.right(tree.right(tree.root())).getElement().getKey());
             
        assertEquals("twenty", tree.remove(20));
        assertEquals(7, tree.size());
        assertEquals("fifteen", tree.remove(15));
        assertEquals(6, tree.size());
        assertEquals("five", tree.remove(5));        
        assertEquals(5, tree.size());
        // Verify tree
        assertEquals(23, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(0, (int)tree.left(tree.left(tree.root())).getElement().getKey());
        assertEquals(10, (int)tree.right(tree.left(tree.root())).getElement().getKey());
        assertEquals(25, (int)tree.right(tree.root()).getElement().getKey());        
        assertEquals("twenty-five", tree.remove(25));
        assertEquals(4, tree.size());
        // Verify tree
        assertEquals(1, (int)tree.root().getElement().getKey());
        assertEquals(0, (int)tree.left(tree.root()).getElement().getKey());
        assertEquals(23, (int)tree.right(tree.root()).getElement().getKey());
         
        
        assertEquals("zero", tree.remove(0));
        assertEquals(3, tree.size());
        assertEquals("ten", tree.remove(10));
        assertEquals(2, tree.size());
        
        // Verify tree
        assertEquals(23, (int)tree.root().getElement().getKey());
        assertEquals(1, (int)tree.left(tree.root()).getElement().getKey());
        
        assertEquals("twenty-three", tree.remove(23));
        assertEquals(1, tree.size());
        assertEquals("one", tree.remove(1));
        assertEquals(0, tree.size());
        assertTrue(tree.isEmpty());        
    }
}