package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for SkipListMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * a sorted array-based data structure that uses binary search to locate entries
 * based on the key of the entry
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class SkipListMapTest {

	/** Field for Map */
    private SkipListMap<Integer, String> map;
    /** Field for StudentMap */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of a search table map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new SkipListMap<Integer, String>();
        studentMap = new SkipListMap<Student, Integer>();
    }

    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("SkipListMap[3]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());

        assertNull(map.put(5, "string5"));
        assertEquals("SkipListMap[3, 5]", map.toString());
        assertEquals(2, map.size());
        assertFalse(map.isEmpty());
        
        assertNull(map.put(2, "string2"));
        assertEquals("SkipListMap[2, 3, 5]", map.toString());
        assertEquals(3, map.size());
        
        assertNull(map.put(4, "string4"));
        assertEquals("SkipListMap[2, 3, 4, 5]", map.toString());
        assertEquals(4, map.size());
        
        assertNull(map.put(1, "string1"));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        assertEquals(5, map.size());
        
        // Put an entry with a previously existing key
        assertEquals("string1", map.put(1, "string11"));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        assertEquals(5, map.size());
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {
        assertTrue(map.isEmpty());
        
        // Getting a key from an empty map
        assertNull("string1", map.get(1));
        
        // Filling the map
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        // Getting each entry
        assertEquals("string1", map.get(1));
        assertEquals("string2", map.get(2));
        assertEquals("string3", map.get(3));
        assertEquals("string4", map.get(4));
        assertEquals("string5", map.get(5));
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        // Get a nonexistent key
        assertNull(map.get(6));
    }

    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("SkipListMap[1, 2, 3, 4, 5]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals("SkipListMap[1, 2, 3, 4]", map.toString());
        assertEquals(4, map.size());
        
        assertEquals("string3", map.remove(3));
        assertEquals("SkipListMap[1, 2, 4]", map.toString());
        assertEquals(3, map.size());
        
        assertEquals("string2", map.remove(2));
        assertEquals("SkipListMap[1, 4]", map.toString());
        assertEquals(2, map.size());
        
        assertEquals("string1", map.remove(1));
        assertEquals("SkipListMap[4]", map.toString());
        assertEquals(1, map.size());
        
        // Try to remove 1 again
        assertNull(map.remove(1));
        
        assertEquals("string4", map.remove(4));
        assertEquals("SkipListMap[]", map.toString());
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        
        // Test map.put()
        assertNull(studentMap.put(s3, 3));
        assertNull(studentMap.put(s5, 5));
        assertNull(studentMap.put(s2, 2));
        assertNull(studentMap.put(s4, 4));
        assertNull(studentMap.put(s1, 1));
        assertFalse(studentMap.isEmpty());
        assertEquals(5, studentMap.size());
        
        // Test map.get()
        assertEquals((Integer)5, studentMap.get(s5));
        assertEquals(5, studentMap.size());
        assertEquals((Integer)3, studentMap.get(s3));
        assertEquals(5, studentMap.size());
        assertEquals((Integer)2, studentMap.get(s2));
        assertEquals(5, studentMap.size());
        assertEquals((Integer)1, studentMap.get(s1));
        assertEquals(5, studentMap.size());
        assertEquals((Integer)4, studentMap.get(s4));
        assertEquals(5, studentMap.size());
        
        // Test map.remove()
        assertEquals((Integer)5, studentMap.remove(s5));
        assertEquals(4, studentMap.size());        
        assertEquals((Integer)3, studentMap.remove(s3));
        assertEquals(3, studentMap.size());
        assertEquals((Integer)2, studentMap.remove(s2));
        assertEquals(2, studentMap.size());
        assertEquals((Integer)1, studentMap.remove(s1));
        assertEquals(1, studentMap.size());
        assertEquals((Integer)4, studentMap.remove(s4));
        assertEquals(0, studentMap.size());
        assertTrue(studentMap.isEmpty());
    }
    
    /**
     * Test the output of the iterator behavior, including expected exceptions
     */ 
    @Test
    public void testIterator() {    	
    	// Map should be empty
    	assertEquals(0, map.size());
        assertTrue(map.isEmpty());

        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));

    	Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
    	// Remove isn't implemented for this iterator
    	try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        try{
            it.remove();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof UnsupportedOperationException);
        }
    }

    /**
     * Test the output of the entrySet() behavior, including expected exceptions
     */     
    @Test
    public void testEntrySet() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
        assertTrue(it.hasNext());
        Map.Entry<Integer, String> entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }

    /**
     * Test the output of the values() behavior, including expected exceptions
     */  
    @Test
    public void testValues() {
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        
        Iterator<String> it = map.values().iterator();
        
        assertTrue(it.hasNext());
        assertEquals("string1", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertTrue(it.hasNext());
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertFalse(it.hasNext());

        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
}