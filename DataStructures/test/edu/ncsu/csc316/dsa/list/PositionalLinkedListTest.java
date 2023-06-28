package edu.ncsu.csc316.dsa.list;

import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;

/**
 * Test class for PositionalLinkedList.
 * Checks the expected outputs of the Positional List abstract data type behaviors when using
 * an doubly-linked positional list data structure
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class PositionalLinkedListTest {
	
	/** list to test */
    private PositionalLinkedList<String> list;
    
    /**
     * Create a new instance of an positional linked list before each test case executes
     */ 
    @Before
    public void setUp() {
        list = new PositionalLinkedList<String>();
    }
    
    /**
     * Test the output of the first() behavior, including expected exceptions
     */
    @Test
    public void testFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        assertNull(list.first());
        
        // Add a first element
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
        
        // Add another first element. List is {"two", "one"} after this
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(first, list.last());
        
        // Add another first element. List is {"three", "two", "one"} after this
        Position<String> third = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(third, list.first());
        assertEquals(first, list.last());
        assertEquals(list.before(list.last()), second);
    }
    
    /**
     * Test the output of the last() behavior, including expected exceptions
     */
    @Test
    public void testLast() {
    	assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        assertNull(list.last());
        
        assertNull(list.first());
        
        // Add a last element
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        assertEquals(first, list.last());
        
        // Add another last element. List is {"one", "two"} after this
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        assertEquals(first, list.first());
                
        // Add another first element. List is {"one", "two", "three"} after this
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third, list.last());
        assertEquals(first, list.first());
        assertEquals(list.before(third), second);
    }
    
    /**
     * Test the output of the addFirst(element) behavior
     */ 
    @Test
    public void testAddFirst() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertFalse(list.isEmpty());
        
     // Add another first element. List is {"two", "one"} after this
        Position<String> second = list.addFirst("two");
        assertEquals(2, list.size());
        assertEquals(second, list.first());
        assertEquals(first, list.last());
        
        // Add another first element. List is {"three", "two", "one"} after this
        Position<String> third = list.addFirst("three");
        assertEquals(3, list.size());
        assertEquals(third, list.first());
        assertEquals(first, list.last());
        assertEquals(list.before(first), second);
    }
    
    /**
     * Test the output of the addLast(element) behavior
     */ 
    @Test
    public void testAddLast() {
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        Position<String> first = list.addLast("one");
        assertEquals(1, list.size());
        
        // Add another last element. List is {"one", "two"} after this
        Position<String> second = list.addLast("two");
        assertEquals(2, list.size());
        assertEquals(second, list.last());
        assertEquals(first, list.first());
                
        // Add another first element. List is {"one", "two", "three"} after this
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        assertEquals(third, list.last());
        assertEquals(first, list.first());
        assertEquals(list.before(third), second);
    }
    
    /**
     * Test the output of the before(position) behavior, including expected exceptions
     */ 
    @Test
    public void testBefore() {
    	Position<String> first = list.addFirst("one");
    	assertNull(list.before(first));
        Position<String> second = list.addLast("two");
        assertEquals(list.before(second), first);
        Position<String> third = list.addLast("three");
        assertEquals(list.before(third), second);
    }
    
    /**
     * Test the output of the after(position) behavior, including expected exceptions
     */     
    @Test
    public void testAfter() {
    	Position<String> first = list.addFirst("one");
    	assertNull(list.after(first));
        Position<String> second = list.addFirst("two");
        assertEquals(list.after(second), first);
        Position<String> third = list.addFirst("three");
        assertEquals(list.after(third), second);
    }
    
    /**
     * Test the output of the addBefore(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddBefore() {
    	Position<String> first = list.addFirst("one");
    	// list is {"one"}
    	assertNull(list.before(first));
        Position<String> second = list.addBefore(first, "two");
        // list is {"two", "one"}
        assertEquals(list.before(first), second);
        Position<String> third = list.addBefore(second, "three");
        // list is {"three", "two", "one"}
        assertEquals(list.before(second), third);
    }
    
    /**
     * Test the output of the addAfter(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testAddAfter() {
    	Position<String> first = list.addFirst("one");
    	// list is {"one"}
    	assertNull(list.before(first));
        Position<String> second = list.addAfter(first, "two");
        // list is {"one", "two"}
        assertEquals(list.before(second), first);
        Position<String> third = list.addAfter(second, "three");
        // list is {"one", "two", "three"}
        assertEquals(list.after(second), third);
    }
    
    /**
     * Test the output of the set(position, element) behavior, including expected exceptions
     */     
    @Test
    public void testSet() {
    	Position<String> first = list.addFirst("one");
    	Position<String> second = list.addAfter(first, "two");
        Position<String> third = list.addAfter(second, "three");
        // list is {"one", "two", "three"}
        assertEquals(list.after(second), third);
        assertEquals("two", list.set(second,  "second"));
        assertEquals(second.getElement(), "second");     
    }
    
    /**
     * Test the output of the remove(position) behavior, including expected exceptions
     */     
    @Test
    public void testRemove() {
    	Position<String> first = list.addFirst("one");
    	Position<String> second = list.addAfter(first, "two");
        Position<String> third = list.addAfter(second, "three");
        // list is {"one", "two", "three"}
        assertEquals(list.after(second), third);
        assertEquals("two", list.remove(second));
        assertEquals(list.before(third), first);
        assertEquals("one", list.remove(first));
        assertNull(list.before(third));
        assertEquals("three", list.remove(third));
        assertTrue(list.isEmpty());
    }
    
    /**
     * Test the output of the iterator behavior for elements in the list, 
     * including expected exceptions
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

        // Add a first element
        Position<String> first = list.addFirst("one");
        assertEquals(1, list.size());
        assertEquals(first, list.first());
            
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
        
        // Reset list iterator
        it = list.iterator();
        
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
        // List is {"one", "two", "three"} at this point with cursor at "one"
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
        
        // List is now {"two", "three"}, current at front
        assertTrue(it.hasNext());
        assertEquals("two", it.next());
        
        // Remove "two" and check
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
        
        // Remove "three" and check
        it.remove();
        // Reset iterator
        it = list.iterator();
     
        assertFalse(it.hasNext());
        
        // Check for empty list
        assertEquals(0, list.size());
        assertTrue(list.isEmpty());
        
        // Attempt remove without next()
        try{
            it.remove();
            fail("An IllegalStateException should have been thrown");           
        } catch(Exception e) {
            assertTrue(e instanceof IllegalStateException);
        }     
    }
    
    /**
     * Test the output of the positions() behavior to iterate through positions
     * in the list, including expected exceptions
     */     
    @Test
    public void testPositions() {
        assertEquals(0, list.size());
        Position<String> first = list.addFirst("one");
        Position<String> second = list.addLast("two");
        Position<String> third = list.addLast("three");
        assertEquals(3, list.size());
        
        Iterator<Position<String>> it = list.positions().iterator();
        assertTrue(it.hasNext());
        assertEquals(first, it.next());
        assertEquals(second, it.next());
        assertEquals(third, it.next());
    }

}