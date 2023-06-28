/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Tests ShortestPathUtil class
 * @author Ben Morris
 */
public class ShortestPathUtilTest {
	
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
	 * Creates tree to traverse
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
	 * Test method for dijkstra()
	 */
	@Test
	public void testDijkstra() {
		setUp();

		Map<Vertex<String>, Integer> dijkstraMap = new LinearProbingHashMap<>(); 
		
		assertTrue(dijkstraMap.isEmpty());
		assertEquals(0, dijkstraMap.size());
		dijkstraMap = ShortestPathUtil.dijkstra(undirectedGraph, v2);
		assertEquals(5, dijkstraMap.size());
		
		assertEquals((Integer) 5, (Integer) dijkstraMap.get(v1));
		assertEquals((Integer) 15, (Integer) dijkstraMap.get(v3));
		assertEquals((Integer) 20, (Integer) dijkstraMap.get(v4));
		assertEquals((Integer) 25, (Integer) dijkstraMap.get(v5));
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.ShortestPathUtil#shortestPathTree(edu.ncsu.csc316.dsa.graph.Graph, edu.ncsu.csc316.dsa.graph.Graph.Vertex, edu.ncsu.csc316.dsa.map.Map)}.
	 */
	@Test
	public void testShortestPathTree() {
		setUp();

		Map<Vertex<String>, Integer> dijkstraMap = new LinearProbingHashMap<>(); 
		
		assertTrue(dijkstraMap.isEmpty());
		assertEquals(0, dijkstraMap.size());
		dijkstraMap = ShortestPathUtil.dijkstra(undirectedGraph, v2);
		assertEquals(5, dijkstraMap.size());
		
		Map<Vertex<String>, Edge<Highway>> shortestPathTree = ShortestPathUtil.shortestPathTree(undirectedGraph, v2, dijkstraMap);
		assertEquals(4, shortestPathTree.size());
		
		assertEquals((Integer) 5, (Integer) shortestPathTree.get(v1).getElement().getWeight());
		assertEquals((Integer) 10, (Integer) shortestPathTree.get(v3).getElement().getWeight());
		assertEquals((Integer) 15, (Integer) shortestPathTree.get(v4).getElement().getWeight());
		assertEquals((Integer) 20, (Integer) shortestPathTree.get(v5).getElement().getWeight());
	}
	
	/**
	 * Example class with that implements Weighted
	 * @author Dr. King
	 *
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
