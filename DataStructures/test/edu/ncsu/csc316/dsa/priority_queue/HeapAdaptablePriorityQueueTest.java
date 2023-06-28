package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;

/**
 * Test class for HeapAdaptablePriorityQueue
 * Checks the expected outputs of the Adaptable Priority Queue abstract
 * data type behaviors when using a min-heap data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class HeapAdaptablePriorityQueueTest {

	/** Heap to use */
    private HeapAdaptablePriorityQueue<Integer, String> heap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */   
    @Before
    public void setUp() {
        heap = new HeapAdaptablePriorityQueue<Integer, String>();
    }
    
    /**
     * Test the output of the replaceKey behavior
     */     
    @Test
    public void testReplaceKey() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.replaceKey(e8,  -5);
        assertEquals(-5, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(2, heap.size());

        // Try to replace a non-valid key
        heap.remove(e0);
        assertEquals(1, heap.size());
        try{
        	heap.replaceKey(e0, 5);
            fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
        	assertTrue(e instanceof IllegalArgumentException);
        }        
    }
    
    /**
     * Test the output of the replaceValue behavior
     */ 
    @Test
    public void testReplaceValue() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.replaceValue(e8,  "EIGHT");
        assertEquals(0, (int)heap.min().getKey());
        assertEquals("zero", heap.min().getValue());
        assertEquals(2, heap.size());
        assertEquals("EIGHT",  e8.getValue()); 
        
        assertEquals(e0, heap.deleteMin());
    }

    /**
     * Test the output of the remove behavior
     */ 
    @Test
    public void testRemove() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());
        
        Entry<Integer, String> e8 = heap.insert(8, "eight");
        Entry<Integer, String> e0 = heap.insert(0, "zero");
        assertEquals(2, heap.size());
        
        heap.remove(e0);
        assertEquals(8, (int)heap.min().getKey());
        assertEquals("eight", heap.min().getValue());
        assertEquals(1, heap.size());        
        
        heap.remove(e8);
        assertEquals(0, heap.size());
        
        try{
        	heap.remove(e0);
            fail("An IllegalArgumentException should have been thrown");
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException);
        }     
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */     
    @Test
    public void testStudentHeap() {
        AdaptablePriorityQueue<Student, String> sHeap = new HeapAdaptablePriorityQueue<Student, String>(new StudentIDComparator());
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size()); 
        
        sHeap.insert(s3, "three");
        sHeap.insert(s4, "four");
        sHeap.insert(s1, "one");
        sHeap.insert(s5, "five");
        sHeap.insert(s2, "two");
        
        assertEquals(5, sHeap.size());
        System.out.println(sHeap.min().getValue());
    }
}
