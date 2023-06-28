package edu.ncsu.csc316.dsa.set;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for HashSet
 * Checks the expected outputs of the Set abstract data type behaviors when using
 * a hash table data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 */
public class HashSetTest {

	/** Production Set to test */
    private Set<Integer> set;
    /** Another production Set to test */
    private Set<Integer> set2;
    /** Test Set to test */
    private Set<Integer> testSet;
    
    /**
     * Create new instances of a hash-based set before each test case executes
     */ 
    @Before
    public void setUp() {
        // Will use a "production" hash map with random alpha, beta values
        set = new HashSet<Integer>();
        set2 = new HashSet<Integer>();
        
        // Will use our hash map for testing, which uses alpha=1, beta=1, prime=7
        testSet = new HashSet<Integer>(true);
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
        
        assertTrue(set.contains(5));
        assertTrue(set.contains(6));      
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
    	assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        assertNull(testSet.remove(5));
        
        testSet.add(5);
        testSet.add(10);
        testSet.add(15);
        testSet.add(20);
        testSet.add(25);
        assertEquals(5, testSet.size());
        
        // Remove all entries
        assertEquals((Integer) 5, testSet.remove(5));
        assertEquals((Integer) 10, testSet.remove(10));
        assertEquals((Integer) 15, testSet.remove(15));
        assertEquals((Integer) 20, testSet.remove(20));
        assertEquals((Integer) 25, testSet.remove(25));

        // Test that it is empty
        assertTrue(testSet.isEmpty());
        assertEquals(0, testSet.size());
        assertNull(testSet.remove(5));
        assertNull(testSet.remove(10));
        assertNull(testSet.remove(15));
        assertNull(testSet.remove(20));
        assertNull(testSet.remove(25));        
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
        // Since our hash map uses random numbers, it can
        // be difficult to test that our hash set iterator returns
        // values in a certain order.
        // We could approach this problem with a few different options:
        // (1) instead of checking the specific order of entries
        //      visited by the iterator, we could save each
        //      iterator.next() into an array, then check that the 
        //      array *contains* the entry, regardless of its exact location
        //
        // (2) implement an isTesting flag for HashSet, similar to testSet in the setUp method. 
        //     This is the more appropriate option since we can control the
        //     entire execution and know exactly which values should appear
        //     in the set and in the correct expected order from using the iterator 
        //
        // Revisit your test cases for HashMap iterator -- those tests can be adapted
        //     to work here, too, since you have already worked out the details
        //     of where entries with certain keys will be stored and in what order
        //     they will be stored
        assertTrue(testSet.isEmpty());
        testSet.add(3);
        testSet.add(5);
        testSet.add(2);
        testSet.add(4);
        testSet.add(1);
        testSet.add(6);
        assertEquals(6, testSet.size());
        assertFalse(testSet.isEmpty());
        
        Iterator<Integer> it = testSet.iterator();
        assertTrue(it.hasNext());
        assertEquals(6, (int)it.next()); // should be index 0
        assertEquals(1, (int)it.next()); // should be index 2
        assertEquals(2, (int)it.next()); // should be index 3
        assertEquals(3, (int)it.next()); // should be index 4
        assertEquals(4, (int)it.next()); // should be index 5
        assertEquals(5, (int)it.next()); // should be index 6   
        assertFalse(it.hasNext());
    }
}