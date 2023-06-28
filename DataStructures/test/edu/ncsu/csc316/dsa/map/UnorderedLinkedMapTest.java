package edu.ncsu.csc316.dsa.map;

import static org.junit.Assert.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;

/**
 * Test class for UnorderedLinkedMap
 * Checks the expected outputs of the Map abstract data type behaviors when using
 * an unordered link-based list data structure that uses the move-to-front heuristic for
 * self-organizing entries based on access frequency
 *
 * @author Dr. King
 *
 */
public class UnorderedLinkedMapTest {

	/** Field for Map */
    private Map<Integer, String> map;
    /** Field for StudentMap */
    private Map<Student, Integer> studentMap;
    
    /**
     * Create a new instance of an unordered link-based map before each test case executes
     */     
    @Before
    public void setUp() {
        map = new UnorderedLinkedMap<Integer, String>();
        studentMap = new UnorderedLinkedMap<Student, Integer>();
    }
    
    /**
     * Test the output of the put(k,v) behavior
     */     
    @Test
    public void testPut() {
        assertEquals(0, map.size());
        assertTrue(map.isEmpty());
        assertNull(map.put(3, "string3"));
        assertEquals("UnorderedLinkedMap[3]", map.toString());
        assertEquals(1, map.size());
        assertFalse(map.isEmpty());
        assertNull(map.put(4, "string4"));
        assertEquals("UnorderedLinkedMap[4, 3]", map.toString());
        assertEquals(2, map.size());
        assertNull(map.put(2, "string2"));
        assertEquals("UnorderedLinkedMap[2, 4, 3]", map.toString());
        assertEquals(3, map.size());
        assertNull(map.put(5, "string5"));
        assertEquals("UnorderedLinkedMap[5, 2, 4, 3]", map.toString());
        assertEquals(4, map.size());
        assertNull(map.put(1, "string1"));
        assertEquals("UnorderedLinkedMap[1, 5, 2, 4, 3]", map.toString());
        assertEquals(5, map.size());
        
        // Put an element with a previously existing key
        assertEquals("string1", map.put(1, "string11"));
        assertEquals("UnorderedLinkedMap[1, 5, 2, 4, 3]", map.toString());
        assertEquals(5, map.size());
        assertEquals("string11", map.get(1));
        
    }

    /**
     * Test the output of the get(k) behavior
     */     
    @Test
    public void testGet() {        
        // Test get from an empty list
    	assertTrue(map.isEmpty());
    	assertNull(map.get(1));
    	
    	// Fill the map
        assertNull(map.put(3, "string3"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(1, "string1"));
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        
        // Test the map after getting each entry
        assertEquals("string1", map.get(1));
        assertEquals("UnorderedLinkedMap[1, 4, 2, 5, 3]", map.toString());
        assertEquals("string2", map.get(2));
        assertEquals("UnorderedLinkedMap[2, 1, 4, 5, 3]", map.toString());
        assertEquals("string3", map.get(3));
        assertEquals("UnorderedLinkedMap[3, 2, 1, 4, 5]", map.toString());
        assertEquals("string4", map.get(4));
        assertEquals("UnorderedLinkedMap[4, 3, 2, 1, 5]", map.toString());
        assertEquals("string5", map.get(5));
        assertEquals("UnorderedLinkedMap[5, 4, 3, 2, 1]", map.toString());
        
        // Test get a nonexistent key
        assertNull(map.get(6));
    }
    
    /**
     * Test the output of the remove(k) behavior
     */     
    @Test
    public void testRemove() {
        assertTrue(map.isEmpty());
        assertNull(map.remove(5));
        assertNull(map.put(1, "string1"));
        assertNull(map.put(4, "string4"));
        assertNull(map.put(2, "string2"));
        assertNull(map.put(5, "string5"));
        assertNull(map.put(3, "string3"));
        
        assertFalse(map.isEmpty());
        assertEquals("UnorderedLinkedMap[3, 5, 2, 4, 1]", map.toString());
        
        assertEquals("string5", map.remove(5));
        assertEquals("UnorderedLinkedMap[3, 2, 4, 1]", map.toString());
        assertEquals(4, map.size());
        assertNull(map.get(5));
        
        assertEquals("string3", map.remove(3));
        assertEquals("UnorderedLinkedMap[2, 4, 1]", map.toString());
        assertEquals(3, map.size());
        assertNull(map.get(3));
        
        assertEquals("string2", map.remove(2));
        assertEquals("UnorderedLinkedMap[4, 1]", map.toString());
        assertEquals(2, map.size());
        assertNull(map.get(2));
        
        assertEquals("string1", map.remove(1));
        assertEquals("UnorderedLinkedMap[4]", map.toString());
        assertEquals(1, map.size());
        assertNull(map.get(1));
        
        assertEquals("string4", map.remove(4));
        assertEquals("UnorderedLinkedMap[]", map.toString());
        assertEquals(0, map.size());
        assertNull(map.get(4));
        assertTrue(map.isEmpty());
    }

    /**
     * Test the output of the iterator behavior, including expected exceptions
     */     
    @Test
    public void testIterator() {
    	assertNull(map.put(1, "string1"));
    	assertNull(map.put(4, "string4"));
    	assertNull(map.put(2, "string2"));
    	assertNull(map.put(5, "string5"));
    	assertNull(map.put(3, "string3"));

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
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(1, (int)(entry.getKey()));
        assertEquals("string1", (String)(entry.getValue()));
        
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
        assertEquals(4, (int)(entry.getKey()));
        assertEquals("string4", (String)(entry.getValue()));
        
        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(2, (int)(entry.getKey()));
        assertEquals("string2", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(5, (int)(entry.getKey()));
        assertEquals("string5", (String)(entry.getValue()));

        assertTrue(it.hasNext());
        entry = it.next();
        assertEquals(3, (int)(entry.getKey()));
        assertEquals("string3", (String)(entry.getValue()));
        
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
        assertEquals("string4", it.next());
        assertTrue(it.hasNext());
        assertEquals("string2", it.next());
        assertTrue(it.hasNext());
        assertEquals("string5", it.next());
        assertTrue(it.hasNext());
        assertEquals("string3", it.next());
        assertFalse(it.hasNext());

        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
    
    /**
     * Tests Map abstract data type behaviors to ensure the behaviors work
     * as expected when using arbitrary objects as keys
     */
    @Test
    public void testStudentMap() {
        // Create some Students to test with
    	Student s1 = new Student("J", "K", 1, 0, 0, "jk");
        Student s2 = new Student("J", "S", 2, 0, 0, "js");
        Student s3 = new Student("S", "H", 3, 0, 0, "sh");
        Student s4 = new Student("J", "J", 4, 0, 0, "jj");
        Student s5 = new Student("L", "B", 5, 0, 0, "lb");
        Student s6 = new Student("C", "B", 6, 0, 0, "bc");
        
        // Test methods when list is empty
    	assertTrue(studentMap.isEmpty());
    	assertNull(studentMap.get(s5));
    	assertNull(studentMap.remove(s4));
        
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
        assertNull(studentMap.get(s6));
        
        // Test map.remove()
        assertEquals((Integer)5, studentMap.remove(s5));
        assertEquals(4, studentMap.size());
        assertNull(studentMap.get(s5));
        assertEquals((Integer)3, studentMap.remove(s3));
        assertEquals(3, studentMap.size());
        assertNull(studentMap.get(s3));
        assertEquals((Integer)2, studentMap.remove(s2));
        assertEquals(2, studentMap.size());
        assertNull(studentMap.get(s2));
        assertEquals((Integer)1, studentMap.remove(s1));
        assertEquals(1, studentMap.size());
        assertNull(studentMap.get(s1));
        assertEquals((Integer)4, studentMap.remove(s4));
        assertEquals(0, studentMap.size());
        assertNull(studentMap.get(s4));
        assertTrue(studentMap.isEmpty());
        
        assertNull(studentMap.get(s5));
    }
}