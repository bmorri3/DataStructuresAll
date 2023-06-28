/**
 * 
 */
package edu.ncsu.csc316.dsa.graph;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * Tests GraphTraversalUtil.java
 * 
 * @author Ben Morris
 */
public class GraphTraversalUtilTest {

	/** Undirected Graph */
	private Graph<String, Integer> undirectedGraph;
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

	/**
	 * Creates tree to traverse
	 */
	@Before
	public void setUp() {
		undirectedGraph = new AdjacencyListGraph<String, Integer>();
		
		v1 = undirectedGraph.insertVertex("Raleigh");
        v2 = undirectedGraph.insertVertex("Asheville");
        v3 = undirectedGraph.insertVertex("Wilmington");
        v4 = undirectedGraph.insertVertex("Durham");
        v5 = undirectedGraph.insertVertex("Greenville");
        
        undirectedGraph.insertEdge(v1, v2, 5);
        undirectedGraph.insertEdge(v1, v3, 10);
        undirectedGraph.insertEdge(v1, v4, 15);
        undirectedGraph.insertEdge(v1, v5, 20);
        undirectedGraph.insertEdge(v2, v3, 25);
        undirectedGraph.insertEdge(v2, v4, 30);
        undirectedGraph.insertEdge(v2, v5, 35);
        undirectedGraph.insertEdge(v3, v4, 40);
        undirectedGraph.insertEdge(v3, v5, 45);
        undirectedGraph.insertEdge(v4, v5, 50);		
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.GraphTraversalUtil#depthFirstSearch(edu.ncsu.csc316.dsa.graph.Graph, 
	 * 					edu.ncsu.csc316.dsa.graph.Graph.Vertex)}.
	 */
	@Test
	public void testDepthFirstSearch() {
		setUp();

		Map<Vertex<String>, Edge<Integer>> dfsMap = new LinearProbingHashMap<Vertex<String>, Edge<Integer>>(); 
		
		assertTrue(dfsMap.isEmpty());
		assertEquals(0, dfsMap.size());
		dfsMap = GraphTraversalUtil.depthFirstSearch(undirectedGraph, v2);
		assertEquals(4, dfsMap.size());
		assertEquals((Integer) 5, (Integer) dfsMap.get(v1).getElement());
		assertEquals((Integer) 10, (Integer) dfsMap.get(v3).getElement());
		assertEquals((Integer) 40, (Integer) dfsMap.get(v4).getElement());
		assertEquals((Integer) 50, (Integer) dfsMap.get(v5).getElement());
	}

	/**
	 * Test method for {@link edu.ncsu.csc316.dsa.graph.GraphTraversalUtil#breadthFirstSearch(edu.ncsu.csc316.dsa.graph.Graph, edu.ncsu.csc316.dsa.graph.Graph.Vertex)}.
	 */
	@Test
	public void testBreadthFirstSearch() {
		setUp();

		Map<Vertex<String>, Edge<Integer>> bfsMap = new LinearProbingHashMap<Vertex<String>, Edge<Integer>>(); 
		
		assertTrue(bfsMap.isEmpty());
		assertEquals(0, bfsMap.size());
		bfsMap = GraphTraversalUtil.breadthFirstSearch(undirectedGraph, v2);
		assertEquals(4, bfsMap.size());
		assertEquals((Integer) 5, (Integer) bfsMap.get(v1).getElement());
		assertEquals((Integer) 25, (Integer) bfsMap.get(v3).getElement());
		assertEquals((Integer) 30, (Integer) bfsMap.get(v4).getElement());
		assertEquals((Integer) 35, (Integer) bfsMap.get(v5).getElement());
	}

}
