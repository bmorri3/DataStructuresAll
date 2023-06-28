package edu.ncsu.csc316.dsa.map.hashing;

import static org.junit.Assert.*;
import java.util.Iterator;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.map.Map;

/**
 * Test class for SeparateChainingHashMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a separate chaining hash map data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class SeparateChainingHashMapTest {
	/** Field for map */
    private Map<Integer, String> map;
    /** Field for map2 */
    private Map<Integer, String> map2;
    /** Field for map3 */
    private Map<Integer, String> map3;
    /** Field for map4 */
    private Map<Integer, String> map4;
    
    /**
     * Create a new instance of a separate chaining hash map before each test case executes
     */     
    @Before
    public void setUp() {
        // Use the "true" flag to indicate we are TESTING.
        // Remember that (when testing) alpha = 1, beta = 1, and prime = 7
        // based on our AbstractHashMap constructor.
        // That means you can draw the hash table by hand
        // if you use integer keys, since Integer.hashCode() = the integer value, itself
        // Finally, apply compression. For example:
        // for key = 1: h(1) = ( (1 * 1 + 1) % 7) % 7 = 2
        // for key = 2: h(2) = ( (1 * 2 + 1) % 7) % 7 = 3
        // for key = 3: h(3) = ( (1 * 3 + 1) % 7) % 7 = 4
        // for key = 4: h(4) = ( (1 * 4 + 1) % 7) % 7 = 5
        // for key = 5: h(5) = ( (1 * 5 + 1) % 7) % 7 = 6
        // for key = 6: h(6) = ( (1 * 6 + 1) % 7) % 7 = 0
        // etc.
        // Remember that our secondary map (an AVL tree) is a search
        // tree, which means the entries should be sorted in order within
        // that tree
        map = new SeparateChainingHashMap<Integer, String>(7, true);
        map2 = new SeparateChainingHashMap<Integer, String>();
        map3 = new SeparateChainingHashMap<Integer, String>(7);
        map4 = new SeparateChainingHashMap<Integer, String>(true);
    }
    
    /**
     * Creates a map to test
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */
    public void createMap() {
    	map.put(3, "string3");
    	map.put(4, "string4");
        map.put(18, "string4c");
        map.put(2, "string2");
        map.put(11, "string4b");
        map.put(30, "string2b");
        map.put(703, "string3b");
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));

        // Since our entrySet method returns the entries in the table
        // from left to right, we can use the entrySet to check
        // that our values are in the correct order in the hash table.
        // Alternatively, you could implement a toString() method if you
        // want to check that the exact index/map of each bucket is correct
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        
        
        assertNull(map.put(4, "string4"));
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        it = map.entrySet().iterator();
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        
        // Add some more keys in random order, including some collisions
        assertNull(map.put(18, "string4c"));
        assertEquals(3, map.size());
        assertNull(map.put(2, "string2"));
        assertEquals(4, map.size());
        assertNull(map.put(11, "string4b"));
        assertEquals(5, map.size());        
        assertNull(map.put(30, "string2b"));
        assertEquals(6, map.size());
        assertNull(map.put(703, "string3b"));
        assertEquals(7, map.size());
        
        it = map.entrySet().iterator();
        assertEquals(2, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(30, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(703, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(4, (int)it.next().getKey()); // should be in a map in index 5
        assertEquals(11, (int)it.next().getKey()); // should be in a map in index 5
        assertEquals(18, (int)it.next().getKey()); // should be in a map in index 5
        
        // replace existing key
        assertEquals(map.put(3, "stringThree"), "string3");
        it = map.entrySet().iterator();
        assertEquals(2, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(30, (int)it.next().getKey()); // should be in a map in index 4
        assertEquals(3, (int)it.next().getKey()); // should be in a map in index 4
    }
    
    /**
     * Test the output of the get(k) behavior
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */     
    @Test
    public void testGet() {
    	assertTrue(map.isEmpty());
        createMap();
        assertEquals(7, map.size());
        
        // Get all map entries
        assertEquals(map.get(703), "string3b");
        assertEquals(map.get(2), "string2");
        assertEquals(map.get(4), "string4");
        assertEquals(map.get(18), "string4c");
        assertEquals(map.get(11), "string4b");
        assertEquals(map.get(3), "string3");
        assertEquals(map.get(30), "string2b");
        assertEquals(7, map.size());
        
     // get a nonexistent key
        assertNull(map.get(500));
    }
    
    /**
     * Test the output of the remove(k) behavior
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        createMap();
        assertEquals(7, map.size());        

        // Remove a nonexistent key
        assertNull(map.remove(500));
        
        // Delete some collisions
        assertEquals(map.remove(703), "string3b");
        assertEquals(6, map.size());
        assertEquals(map.remove(2), "string2");
        assertEquals(5, map.size());
        assertEquals(map.remove(4), "string4");
        assertEquals(4, map.size());
        assertEquals(map.remove(18), "string4c");
        assertEquals(3, map.size());
        
        // Remove some keys that have already been removed.
        assertNull(map.remove(703));
        assertEquals(3, map.size());
        assertNull(map.remove(2));
        assertEquals(3, map.size());
        assertNull(map.remove(4));
        assertEquals(3, map.size());
        assertNull(map.remove(18));
        assertEquals(3, map.size());
        
        // Delete last entries, which deletes map at the index
        assertEquals(map.remove(11), "string4b");
        assertEquals(2, map.size());
        assertEquals(map.remove(3), "string3");
        assertEquals(1, map.size());
        assertEquals(map.remove(30), "string2b");
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Test the output of the iterator() behavior, including expected exceptions
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */   
    @Test
    public void testIterator() {
    	assertTrue(map.isEmpty());
        createMap();
        assertEquals(7, map.size());
        
        Iterator<Integer> it = map.iterator();
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 2);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 30);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 3);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 703);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 4);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 11);
        assertTrue(it.hasNext());
        assertEquals((int) it.next(), 18);
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the entrySet() behavior
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */   
    @Test
    public void testEntrySet() {
    	assertTrue(map.isEmpty());
        createMap();
        assertEquals(7, map.size());
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();        
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 2);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 30);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 3);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 703);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 4);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 11);
        assertTrue(it.hasNext());
        assertEquals((int) it.next().getKey(), 18);
        assertFalse(it.hasNext());
    }
    
    /**
     * Test the output of the values() behavior
     * Map should have the following keys at the following indices to begin.
     * Index 3: 2 ("String2"), 30 ("String2b")
     * Index 4: 3 ("String3"), 703 ("String3b")
     * Index 5: 4 ("String4"), 11 ("String 4b"), 18 ("String 4c")
     */   
    @Test
    public void testValues() {
    	assertTrue(map.isEmpty());
        createMap();
        assertEquals(7, map.size());
        
        Iterator<String> it = map.values().iterator();       
        assertTrue(it.hasNext());
        assertEquals((String) it.next(), "string2");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string2b");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string3");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string3b");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string4");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string4b");
        assertTrue(it.hasNext());
        assertEquals(it.next(), "string4c");
        assertFalse(it.hasNext());
    }
    
    /**
     * Tests all of the constructors
     */
    @Test
    public void testConstructors() {
    	assertEquals(0, map2.size());
        assertTrue(map2.isEmpty());
        assertEquals(0, map3.size());
        assertTrue(map3.isEmpty());
        assertEquals(0, map4.size());
        assertTrue(map4.isEmpty());
    }
}