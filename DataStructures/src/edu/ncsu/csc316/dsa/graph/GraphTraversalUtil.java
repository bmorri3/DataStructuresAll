package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.queue.ArrayBasedQueue;
import edu.ncsu.csc316.dsa.queue.Queue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * GraphTraversalUtil provides a collection of behaviors for traversing graphs,
 * including depth-first search and breadth-first search.
 * 
 * The GraphTraversalUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class GraphTraversalUtil {
    
    /**
     * Returns a map of discovery edges that represent a depth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the depth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */
    public static <V, E> Map<Vertex<V>, Edge<E>> depthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	
    	// Use a set to keep track of visited vertices
    	Set<Vertex<V>> known = new HashSet<>();
    	// Use a map to keep track of the forest of discovery edges that discover each vertex
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<>();
    	
    	dfsHelper(graph, start, known, forest);
    	
    	return forest;
    }
    
    /**
     * Helper function for depth-first search.
     * 
     * @param <V> Vertex
     * @param <E> Edge
     * @param graph Graph to search
     * @param u Vertex to start from
     * @param known Set of known Vertices
     * @param forest Forest of Vertices and Edges
     */
    private static <V, E> void dfsHelper(Graph<V, E> graph, Vertex<V> u, Set<Vertex<V>> known, 
    									 Map<Vertex<V>, Edge<E>> forest) {
    	known.add(u);
    	
    	for (Edge<E> edge : graph.outgoingEdges(u)) {
    		
			Vertex<V> v = graph.opposite(u, edge);

			if (!known.contains(v)) {
				forest.put(v, edge);
				dfsHelper(graph, v, known, forest);
			}
		}    
    }

    /**
     * Returns a map of discovery edges that represent a breadth-first search
     * traversal of the given graph from a given starting vertex.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph a graph to traverse
     * @param start the vertex at which to start the breadth-first search traversal
     * @return a map of discovery edges that were used to discover vertices in the
     *         graph
     */    
    public static <V, E> Map<Vertex<V>, Edge<E>> breadthFirstSearch(Graph<V, E> graph, Vertex<V> start) {
    	// Use a set to keep track of visited vertices
    	Set<Vertex<V>> known = new HashSet<>();
    	// Use a map to keep track of the forest of discovery edges that discover each vertex
    	Map<Vertex<V>, Edge<E>> forest = new LinearProbingHashMap<>();
    	// Use a queue to keep track of reachable vertices
    	Queue<Vertex<V>> reachable = new ArrayBasedQueue<>();

    	known.add(start);
    	reachable.enqueue(start);
    	while (!reachable.isEmpty()) {
    		Vertex<V> u = reachable.dequeue();
    		for (Edge<E> edge : graph.outgoingEdges(u)) {
    			Vertex<V> w = graph.opposite(u, edge);
    			if (!known.contains(w)) {
    				known.add(w);
    				forest.put(w, edge);
    				reachable.enqueue(w);
    			}
    		}
		}
    	
    	return forest;
    }
}