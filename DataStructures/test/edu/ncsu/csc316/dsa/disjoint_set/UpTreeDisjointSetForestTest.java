package edu.ncsu.csc316.dsa.disjoint_set;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Position;

/**
 * Test class for UpTreeDisjointSetForest
 * Checks the expected outputs of the Disjoint Set abstract data type 
 * behaviors when using an up-tree data structure
 *
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class UpTreeDisjointSetForestTest {

	/** Set for storing Forest */
    private DisjointSetForest<String> set;

    /**
     * Create a new instance of a up-tree forest before each test case executes
     */     
    @Before
    public void setUp() {
        set = new UpTreeDisjointSetForest<>();
    }
    
    /**
     * Test the output of the makeSet behavior
     */ 
    @Test
    public void testMakeSet() {
        Position<String> one = set.makeSet("one");
        assertEquals("one", one.getElement());
        Position<String> two = set.makeSet("two");
        assertEquals("two", two.getElement()); 
    }

    /**
     * Test the output of the union-find behaviors
     */     
    @Test
    public void testUnionFind() {
        Position<String> one = set.makeSet("one");
        Position<String> two = set.makeSet("two");
        Position<String> three = set.makeSet("three");
        Position<String> four = set.makeSet("four");
        Position<String> five = set.makeSet("five");
        Position<String> six = set.makeSet("six");
        Position<String> seven = set.makeSet("seven");
        Position<String> eight = set.makeSet("eight");
        
        assertEquals(one, set.find("one"));
        assertEquals(three, set.find("three"));
        assertEquals(eight, set.find("eight"));
        
        set.union(one, two);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        
        set.union(three, two);
        assertEquals(two, set.find("one"));
        assertEquals(two, set.find("two"));
        assertEquals(two, set.find("three"));
        
        set.union(four, five);
        assertEquals(five, set.find("four"));        
        set.union(four, six);
        assertEquals(five, set.find("four"));
        set.union(four, seven);
        set.union(four, eight);
        assertEquals(five, set.find("four"));
        
        set.union(one, four);
        assertEquals(five, set.find("one"));
    }    
}