package edu.ncsu.csc316.dsa.queue;

import static org.junit.Assert.*;
import java.util.NoSuchElementException;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for ArrayBasedQueue.
 * Checks the expected outputs of the Queue abstract data type behaviors when using
 * a circular array-based data structure
 *
 * @author Dr. King
 *
 */
public class ArrayBasedQueueTest {

	/** Private field for the queue storing the data */
    private Queue<String> queue;
    
    /**
     * Create a new instance of a circular array-based queue before each test case executes
     */ 
    @Before
    public void setUp() {
        queue = new ArrayBasedQueue<String>();
    }

    /**
     * Test the output of the enqueue(e) behavior
     */     
    @Test
    public void testEnqueue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        queue.enqueue("one");
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("two");
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
        
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        assertEquals("one", queue.front());
    }
    
    /**
     * Test the output of the dequeue(e) behavior, including expected exceptions
     */     
    @Test
    public void testDequeue() {
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        try {
            queue.dequeue();
            fail("NoSuchElementException should have been thrown.");        
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }        
               
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals(3, queue.size());
        assertFalse(queue.isEmpty());
        
        // Dequeue and check
        assertEquals("one", queue.front());
        assertEquals("one", queue.dequeue());        
        assertEquals(2, queue.size());
        assertFalse(queue.isEmpty());
        
        assertEquals("two", queue.front());
        assertEquals("two", queue.dequeue());
        assertEquals(1, queue.size());
        assertFalse(queue.isEmpty());

        assertEquals("three", queue.front());
        assertEquals("three", queue.dequeue());
        assertEquals(0, queue.size());
        assertTrue(queue.isEmpty());
        
        // Add and dequeue to force resize and wrap of array
        queue.enqueue("one");
        queue.enqueue("two");
        queue.enqueue("three");
        assertEquals("one", queue.dequeue());
        assertEquals("two", queue.dequeue());
        queue.enqueue("four");
        queue.enqueue("five");
        queue.enqueue("six");
    }
    
    /**
     * Test the output of the front() behavior, including expected exceptions
     */     
    @Test
    public void testFront() {
    	 assertEquals(0, queue.size());
         assertTrue(queue.isEmpty());
         
         // Add and test front
         queue.enqueue("one");
         assertEquals(1, queue.size());
         assertFalse(queue.isEmpty());
         assertEquals("one", queue.front());
         
         queue.enqueue("two");
         assertEquals(2, queue.size());
         assertFalse(queue.isEmpty());
         assertEquals("one", queue.front());
         
         queue.enqueue("three");
         assertEquals(3, queue.size());
         assertFalse(queue.isEmpty());
         assertEquals("one", queue.front());
    }
}