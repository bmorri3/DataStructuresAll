package edu.ncsu.csc316.dsa.priority_queue;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import edu.ncsu.csc316.dsa.data.Student;
import edu.ncsu.csc316.dsa.data.StudentGPAComparator;
import edu.ncsu.csc316.dsa.data.StudentIDComparator;

/**
 * Test class for HeapPriorityQueue
 * Checks the expected outputs of the Priorty Queue abstract data type behaviors when using
 * a min-heap data structure 
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class HeapPriorityQueueTest {
	/** Heap */
    private PriorityQueue<Integer, String> heap;
    /** Student heap */
    PriorityQueue<Student, String> sHeap;
    
    /**
     * Create a new instance of a heap before each test case executes
     */     
    @Before
    public void setUp() {
        heap = new HeapPriorityQueue<Integer, String>();
        sHeap = new HeapPriorityQueue<Student, String>();        
    }
    
    /**
     * Test the output of the insert(k,v) behavior
     */     
    @Test
    public void testInsert() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());
        
        heap.insert(10,  "ten");
        assertEquals(2, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(8, (int)heap.deleteMin().getKey());
        assertEquals(1, heap.size());

        // Also test validate() with an invalid key        
        
        assertEquals(10, (int)heap.min().getKey());
        assertEquals(10, (int)heap.deleteMin().getKey());
        
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        
    }
    
    /**
     * Test the output of the min behavior
     */ 
    @Test
    public void testMin() {
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());


        heap.insert(10,  "ten");
        assertEquals(2, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(8, (int)heap.deleteMin().getKey());
        assertEquals(1, heap.size());
        
        assertEquals(10, (int)heap.min().getKey());
        assertEquals(10, (int)heap.deleteMin().getKey());
        
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        assertNull(heap.min());
    }
    
    /**
     * Test the output of the deleteMin behavior
     */     
    @Test 
    public void testDeleteMin() {
    	assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        assertNull(heap.deleteMin());
        
        assertNull(heap.min());
        
        heap.insert(8, "eight");
        assertEquals(1, heap.size());
        assertFalse(heap.isEmpty());
        assertEquals(8, (int)heap.min().getKey());

        heap.insert(10,  "ten");
        assertEquals(2, heap.size());
        assertEquals(8, (int)heap.min().getKey());
        assertEquals(8, (int)heap.deleteMin().getKey());
        assertEquals(1, heap.size());
        
        assertEquals(10, (int)heap.min().getKey());
        assertEquals(10, (int)heap.deleteMin().getKey());
        
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        assertNull(heap.min());
    }
    
    /**
     * Test the output of the heap behavior when using arbitrary key objects to
     * represent priorities
     */ 
    @Test
    public void testStudentHeap() {        
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        // Add and check all students
        sHeap.insert(s1, "one");
        assertEquals(1, sHeap.size());
        assertFalse(sHeap.isEmpty());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s2, "two");
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s3, "three");
        assertEquals(3, sHeap.size());
        assertEquals(s3, sHeap.min().getKey());
        
        sHeap.insert(s5, "five");
        assertEquals(4, sHeap.size());
        assertEquals(s5, sHeap.min().getKey());        
        
        sHeap.insert(s4, "four");
        assertEquals(5, sHeap.size());
        assertEquals(s5, sHeap.min().getKey());

        // Remove the minimum one at a time until all are gone
        assertEquals(s5, sHeap.min().getKey());
        assertEquals(s5, sHeap.deleteMin().getKey());
        assertEquals(4, sHeap.size());
        
        assertEquals(s3, sHeap.min().getKey());
        assertEquals(s3, sHeap.deleteMin().getKey());
        assertEquals(3, sHeap.size());
        
        assertEquals(s4, sHeap.min().getKey());
        assertEquals(s4, sHeap.deleteMin().getKey());
        assertEquals(2, sHeap.size());
        
        assertEquals(s1, sHeap.min().getKey());
        assertEquals(s1, sHeap.deleteMin().getKey());
        assertEquals(1, sHeap.size());
        
        assertEquals(s2, sHeap.min().getKey());
        assertEquals(s2, sHeap.deleteMin().getKey());
        assertEquals(0, sHeap.size()); 
        
        // Test that it is empty
        assertTrue(heap.isEmpty());
        assertTrue(heap.size() == 0);
        assertNull(heap.min());        
    }
    
    /**
     * Testing to make sure it's compatible with custom comparators
     */
    @Test
    public void testComparator() {
    	sHeap = new HeapPriorityQueue<Student, String>(new StudentIDComparator()); 	
    	    	
        Student s1 = new Student("J", "K", 1, 1, 1, "jk1");
        Student s2 = new Student("J", "S", 2, 1, 2, "js2");
        Student s3 = new Student("S", "H", 3, 1, 3, "sh3");
        Student s4 = new Student("J", "J", 4, 1, 4, "jj4");
        Student s5 = new Student("L", "B", 5, 1, 5, "lb5");
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        // Add and check all students
        sHeap.insert(s1, "one");
        assertEquals(1, sHeap.size());
        assertFalse(sHeap.isEmpty());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s2, "two");
        assertEquals(2, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s3, "three");
        assertEquals(3, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s5, "five");
        assertEquals(4, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());        
        
        sHeap.insert(s4, "four");
        assertEquals(5, sHeap.size());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap = new HeapPriorityQueue<Student, String>(new StudentGPAComparator()); 	
        
        assertTrue(sHeap.isEmpty());
        assertEquals(0, sHeap.size());
        
        // Add and check all students
        sHeap.insert(s1, "one");
        assertEquals(1, sHeap.size());
        assertFalse(sHeap.isEmpty());
        assertEquals(s1, sHeap.min().getKey());
        
        sHeap.insert(s2, "two");
        assertEquals(2, sHeap.size());
        assertEquals(s2, sHeap.min().getKey());
        
        sHeap.insert(s3, "three");
        assertEquals(3, sHeap.size());
        assertEquals(s3, sHeap.min().getKey());
        
        sHeap.insert(s5, "five");
        assertEquals(4, sHeap.size());
        assertEquals(s5, sHeap.min().getKey());        
        
        sHeap.insert(s4, "four");
        assertEquals(5, sHeap.size());
        assertEquals(s5, sHeap.min().getKey());
    }
}