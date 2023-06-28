package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import java.util.Iterator;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * Test for MinimumSpanningTreeUtil.java
 * @author Ben Morris
 */
public class MinimumSpanningTreeUtilTest {
	/** Undirected Graph */
	private Graph<String, Highway> undirectedGraph;
	/** Vertex 1 */
	private Vertex<String> v1;
	/** Vertex 2 */
	private Vertex<String> v2;
	/** Vertex 3 */
	private Vertex<String> v3;
	/** Vertex 4 */
	private Vertex<String> v4;
	/** Vertex 5 */
	private Vertex<String> v5;
	/** Hwy 5 */
	private Highway us5;
	/** Hwy 10 */
	private Highway us10;
	/** Hwy 15 */
	private Highway us15;
	/** Hwy 20 */
	private Highway us20;
	/** Hwy 25 */
	private Highway us25;
	/** Hwy 30 */
	private Highway us30;
	/** Hwy 35 */
	private Highway us35;
	/** Hwy 40 */
	private Highway us40;
	/** Hwy 45 */
	private Highway us45;
	/** Hwy 50 */
	private Highway us50;
	
	/**
	 * Sets up Highways, Vertices, and undirectedGraph
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Highway>();
		
        us5 = new Highway("US5", 5);
        us10 = new Highway("US10", 10);
        us15 = new Highway("US15", 15);
        us20 = new Highway("US20", 20);
        us25 = new Highway("US25", 25);
        us30 = new Highway("US30", 30);
        us35 = new Highway("US35", 35);
        us40 = new Highway("US40", 40);
        us45 = new Highway("US45", 45);
        us50 = new Highway("US50", 50);
		
		v1 = undirectedGraph.insertVertex("Raleigh");
        v2 = undirectedGraph.insertVertex("Asheville");
        v3 = undirectedGraph.insertVertex("Wilmington");
        v4 = undirectedGraph.insertVertex("Durham");
        v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, us5);
        undirectedGraph.insertEdge(v1, v3, us10);
        undirectedGraph.insertEdge(v1, v4, us15);
        undirectedGraph.insertEdge(v1, v5, us20);
        undirectedGraph.insertEdge(v2, v3, us25);
        undirectedGraph.insertEdge(v2, v4, us30);
        undirectedGraph.insertEdge(v2, v5, us35);
        undirectedGraph.insertEdge(v3, v4, us40);
        undirectedGraph.insertEdge(v3, v5, us45);
        undirectedGraph.insertEdge(v4, v5, us50);
	}

	/**
	 * Tests Kruskal's algorithm
	 */
	@Test
	public void testKruskal() {
		setUp();
		
		PositionalList<Edge<Highway>> kruskalList = new PositionalLinkedList<>();
		
		assertTrue(kruskalList.isEmpty());
		assertEquals(0, kruskalList.size());
		kruskalList = MinimumSpanningTreeUtil.kruskal(undirectedGraph);
		assertEquals(4, kruskalList.size());
		
		Iterator<Edge<Highway>> it = kruskalList.iterator();
		assertTrue(it.hasNext());
		assertEquals(5, it.next().getElement().getWeight());
		assertEquals(10, it.next().getElement().getWeight());
		assertEquals(15, it.next().getElement().getWeight());
		assertEquals(20, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
	}

	/**
	 * Tests primJarnik's algorithm
	 */
	@Test
	public void testPrimJarnik() {
		setUp();

		PositionalList<Edge<Highway>> primList = new PositionalLinkedList<>();
		
		assertTrue(primList.isEmpty());
		assertEquals(0, primList.size());
		primList = MinimumSpanningTreeUtil.primJarnik(undirectedGraph);
		assertEquals(4, primList.size());
		
		Iterator<Edge<Highway>> it = primList.iterator();
		assertTrue(it.hasNext());
		assertEquals(5, it.next().getElement().getWeight());
		assertEquals(10, it.next().getElement().getWeight());
		assertEquals(15, it.next().getElement().getWeight());
		assertEquals(20, it.next().getElement().getWeight());
		assertFalse(it.hasNext());
	}
	
	/**
	 * Example class with that implements Weighted
	 * @author Dr. King
	 */
	public class Highway implements Weighted {
		
		/** Name of highway */
		private String name;
		/** Length of highway */
		private int length;
		
		/**
		 * Constructor
		 * @param n Name of Highway
		 * @param l Length of Highway
		 */
		public Highway(String n, int l) {
		    setName(n);
		    setLength(l);
		}
		
		/**
		 * Setter for name
		 * @param name name of Highway
		 */
		public void setName(String name) {
		    this.name = name;
		}
		
		/**
		 * Get length of Highway
		 * @return length
		 */
		public int getLength() {
		    return length;
		}
		
		/**
		 * Set length of Highway
		 * @param length length of Highway
		 */
		public void setLength(int length) {
		    this.length = length;
		}
		
		@Override
		public int getWeight() {
		    return getLength();
		}
	}
}
