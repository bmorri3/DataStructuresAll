package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedList.
 * Checks the expected outputs of the List abstract data type behaviors when using
 * an array-based list data structure
 *
 * @author Dr. King
 * @author Ben Morris
 */
public class ArrayBasedListTest {

	/** List field */
    private List<String> list;

    /**
     * Create a new instance of an array-based list before each test case executes
     */
    @Before
    public void setUp() {
        list = new ArrayBasedList<String>();
    }

    /**
     * Test the output of the add(index, e) behavior, including expected exceptions
     */
    @Test
    public void testAddIndex() {
    	//Test empty list
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());

        //Test adding first element, which forces resize
        list.add(0, "one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        //Test adding 2nd element, which forces resize
        list.add(1, "two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        //Test adding 3rd element
        list.add(2, "three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
         
        try{
            list.add(15,  "fifteen");
            fail("An IndexOutOfBoundsException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IndexOutOfBoundsException);
        }
        
    }

    /**
     * Test the output of the addLast behavior
     */
    @Test
    public void testAddLast() {
    	//Test adding first element, which forces resize
        list.addLast("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        //Test adding 2nd element, which forces resize
        list.addLast("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(1));
        assertFalse(list.isEmpty());
        
        //Test adding 3rd element
        list.addLast("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(2));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	//Test last() after adding 1st element
        list.addLast("one");
        assertEquals("one", list.last());
        
        //Test last() after adding 2nd element
        list.addLast("two");
        assertEquals("two", list.last());
        
        //Test last() after adding 3rd element
        list.addLast("three");
        assertEquals("three", list.last());
    }

    /**
     * Test the output of the addFirst behavior
     */
    @Test
    public void testAddFirst() {
    	//Test adding first element, which forces resize
        list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        assertFalse(list.isEmpty());
        
        //Test adding another first element, which forces resize
        list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertFalse(list.isEmpty());
        
        //Test adding another first element
        list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals("three", list.get(0));
        assertFalse(list.isEmpty());
    }

    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        ////Test first() after adding 1st element
        list.addFirst("one");
        assertEquals("one", list.first());
        
        //Test first() after adding 2nd element
        list.addFirst("two");
        assertEquals("two", list.first());
        
        //Test first() after adding 3rd element
        list.addFirst("three");
        assertEquals("three", list.first());
    }

    /**
     * Test the iterator behaviors, including expected exceptions
     */
    @Test
    public void testIterator() {
        // Start with an empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Create an iterator for the empty list
        Iterator<String> it = list.iterator();
        
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());

        // Now add an element
        list.addLast("one");
        
        // Use accessor methods to check that the list is correct
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        assertEquals("one", list.get(0));
        
        // Create an iterator for the list that has 1 element
        it = list.iterator();
        
        // Try different iterator operations to make sure they work
        // as expected for a list that contains 1 element (at this point)
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertFalse(it.hasNext());
        try{
            it.next();
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        
        // Remove only element (at this point)
        it.remove();
        // Check that the list is empty
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        // Try different operations to make sure they work
        // as expected for an empty list (at this point)
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());
        
        // Add several elements
        list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        
        // Traverse list
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        assertTrue(it.hasNext());
        assertEquals("three", it.next());
        assertFalse(it.hasNext());
        
        // Remove last element and check end of list
        it.remove();
        assertFalse(it.hasNext());
        
        try{
        	assertEquals("three", it.next());
            fail("A NoSuchElementException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        // Add a third element
        list.addLast("three");
        
        // Iterate from the beginning, removing each element and checking different operations each time
        // List is {"one", "two", "three"} at this point
        it = list.iterator();
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        // Remove "one" and check
        assertTrue(it.hasNext());
        assertEquals("one", it.next());
        it.remove();
        assertTrue(it.hasNext());
        // Attempt remove without next()
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        
        // List is now {"two", "three"}
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        
        // Remove and check
        it.remove();
        assertTrue(it.hasNext());
        // Attempt remove without next()
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
       
        // List is now {"three"}
        assertTrue(it.hasNext());
        assertEquals("three", it.next());
        
        // Remove and check
        it.remove();
        assertFalse(it.hasNext());
        // Attempt remove without next()
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }
        assertFalse(it.hasNext());
    }

    /**
     * Test the output of the remove(index) behavior, including expected exceptions
     */
    @Test
    public void testRemoveIndex() {
    	 
    	//Create list
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        
        //Remove and check elements and size
        list.remove(1);
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("three", list.get(1));
        
        //Remove and check elements and size
        list.remove(0);
        assertEquals(1, list.size());
        assertEquals("three", list.get(0));
        
        //Remove and check elements and size
        list.remove(0);
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the removeFirst() behavior, including expected exceptions
     */
    @Test
    public void testRemoveFirst() {
    	//Create list
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        
        //Remove and check elements and size
        list.removeFirst();
        assertEquals(2, list.size());
        assertEquals("two", list.get(0));
        assertEquals("three", list.get(1));
        
        //Remove and check elements and size
        list.removeFirst();
        assertEquals(1, list.size());
        assertEquals("three", list.get(0));
        
        //Remove and check elements and size
        list.removeFirst();
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the removeLast() behavior, including expected exceptions
     */
    @Test
    public void testRemoveLast() {
    	//Create list
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        
        //Remove and check elements and size
        list.removeLast();
        assertEquals(2, list.size());
        assertEquals("one", list.get(0));
        assertEquals("two", list.get(1));
        
        //Remove and check elements and size
        list.removeLast();
        assertEquals(1, list.size());
        assertEquals("one", list.get(0));
        
        //Remove and check elements and size
        list.removeLast();
        assertEquals(0, list.size());
    }

    /**
     * Test the output of the set(index, e) behavior, including expected exceptions
     */
    @Test
    public void testSet() {
    	//Create list
    	list.addLast("one");
        list.addLast("two");
        list.addLast("three");
        
        //Set and check elements
        list.set(0, "four");
        assertEquals(3, list.size());
        assertEquals("four", list.get(0));
        assertEquals("two", list.get(1));
        assertEquals("three", list.get(2));
        
        list.set(1, "five");
        assertEquals(3, list.size());
        assertEquals("four", list.get(0));
        assertEquals("five", list.get(1));
        assertEquals("three", list.get(2));
        
        list.set(2, "six");
        assertEquals(3, list.size());
        assertEquals("four", list.get(0));
        assertEquals("five", list.get(1));
        assertEquals("six", list.get(2));
    }
}