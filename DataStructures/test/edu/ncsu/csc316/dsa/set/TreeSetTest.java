package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for TreeSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a balanced search tree data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class TreeSetTest {

	/** Set 1 */
    private Set<Integer> set;
    /** Set 2 */
    private Set<Integer> set2;

    /**
     * Create a new instance of a tree-based set before each test case executes
     */      
    @Before
    public void setUp() {
        set = new TreeSet<Integer>();
        set2 = new TreeSet<Integer>();
    }

    /**
     * Test the output of the add behavior
     */     
    @Test
    public void testAdd() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        // Add some entries
        set.add(5);
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());
        
        set.add(6);
        assertEquals(2, set.size());
        
        // Test through an iterator
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer) 5, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer) 6, it.next());
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the contains behavior
     */ 
    @Test
    public void testContains() {
    	assertTrue(set.isEmpty());
        assertEquals(0, set.size());
    	assertFalse(set.contains(5));
        assertFalse(set.contains(10));
        assertFalse(set.contains(15));
        assertFalse(set.contains(20));
        assertFalse(set.contains(25));    	
    	
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertNull(set.remove(5));
        
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        // Remove all entries
        assertEquals((Integer) 5, set.remove(5));
        assertEquals((Integer) 10, set.remove(10));
        assertEquals((Integer) 15, set.remove(15));
        assertEquals((Integer) 20, set.remove(20));
        assertEquals((Integer) 25, set.remove(25));

        // Test that it is empty
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        assertNull(set.remove(5));
        assertNull(set.remove(10));
        assertNull(set.remove(15));
        assertNull(set.remove(20));
        assertNull(set.remove(25));        
    }
    
    /**
     * Test the output of the retainAll behavior
     */ 
    @Test
    public void testRetainAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        
        // Create two sets
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
                
        set2.add(10);
        set2.add(20);
        set2.add(30);
        assertEquals(3, set2.size());
        
        // Intersection of set and set2 is {10, 20}
        set2.retainAll(set);
        assertEquals(2, set2.size());
        set2.contains(10);
        set2.contains(20);                
    }

    /**
     * Test the output of the removeAll behavior
     */     
    @Test
    public void testRemoveAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());
        
        assertTrue(set2.isEmpty());
        assertEquals(0, set2.size());
        set2.add(10);
        set2.add(20);
        set2.add(30);
        assertEquals(3, set2.size());
        
        // set - set2 = {5, 15, 25}
        set.removeAll(set2);
        assertEquals(3, set.size());
        assertEquals(3, set2.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(15));
        assertTrue(set.contains(25));        
    }

    /**
     * Test the output of the addAll behavior
     */     
    @Test
    public void testAddAll() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5);
        set.add(10);
        set.add(15);
        set.add(20);
        set.add(25);
        assertEquals(5, set.size());

        assertTrue(set2.isEmpty());
        assertEquals(0, set2.size());
        set2.add(30);
        set2.add(40);
        set2.add(50);
        assertEquals(3, set2.size());
        
        // set + set2 = {5, 10, 15, 20, 25, 30, 40, 50}
        set.addAll(set2);
        assertEquals(8, set.size());
        assertTrue(set.contains(5));
        assertTrue(set.contains(10));
        assertTrue(set.contains(15));
        assertTrue(set.contains(20));
        assertTrue(set.contains(25));
        assertTrue(set.contains(30));
        assertTrue(set.contains(40));
        assertTrue(set.contains(50));       
    }

    /**
     * Test the output of the iterator behavior
     */ 
    @Test
    public void testIterator() {
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());
        set.add(5); 
        set.add(10);
        set.add(15);        
        set.add(20);        
        set.add(25);
        assertEquals(5, set.size());
        
     // Test through an iterator
        Iterator<Integer> it = set.iterator();
        assertTrue(it.hasNext());
        assertEquals((Integer) 5, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer) 10, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer) 15, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer) 20, it.next());
        assertTrue(it.hasNext());
        assertEquals((Integer) 25, it.next());
        assertFalse(it.hasNext());
        assertEquals(5, set.size());
    }
}