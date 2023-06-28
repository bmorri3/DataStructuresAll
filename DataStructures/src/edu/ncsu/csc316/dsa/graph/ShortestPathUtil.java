package edu.ncsu.csc316.dsa.graph;


import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * ShortestPathUtil provides a collection of behaviors for computing shortest
 * path spanning trees for a given graph.
 * 
 * The ShortestPathUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class ShortestPathUtil {
	
	/**
     * For a connected graph, returns a map that represents shortest path costs to
     * all vertices computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param graph the graph for which to compute the shortest path spanning tree
     * @param start the vertex at which to start computing the shorest path spanning
     *              tree
     * @return a map that represents the shortest path costs to all vertices in the
     *         graph
     */ 
	public static <V, E extends Weighted> Map<Vertex<V>, Integer> dijkstra(Graph<V, E> graph, Vertex<V> start) {    	  
    	AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
    	Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
    	Set<Vertex<V>> known = new HashSet<>();
    	Map<Vertex<V>, PriorityQueue.Entry<Integer, Vertex<V>>> apqEntries = new LinearProbingHashMap<>();    	
    	
    	for (Vertex<V> vertex : graph.vertices()) {    							
    		if (vertex.equals(start)) 
    			weights.put(vertex, 0);
    		else
    			weights.put(vertex, Integer.MAX_VALUE);
  	      	Integer currentCost = weights.get(vertex);
  	        PriorityQueue.Entry<Integer, Vertex<V>> apqEntry = q.insert(currentCost, vertex);	      	
  	      	apqEntries.put(vertex, apqEntry);
		}
    	
    	while (!q.isEmpty()) {
    		PriorityQueue.Entry<Integer, Vertex<V>> entry = q.deleteMin();
    		Vertex<V> u = entry.getValue();
    		known.add(u);
    		for (Edge<E> edge : graph.outgoingEdges(u)) {
				Vertex<V> z = graph.opposite(u, edge);
				if (!known.contains(z)) {
					Integer r = edge.getElement().getWeight() + weights.get(u);
					if (r < weights.get(z)) {
						weights.put(z, r);
						q.replaceKey(apqEntries.get(z), r);
					}				
				}
			}
    	}
    	
    	return weights;
    }
    
    /**
     * For a connected graph, returns a map that represents shortest path spanning
     * tree edges computed using Dijkstra's single-source shortest path algorithm.
     * 
     * @param <V>       the type of data in the graph vertices
     * @param <E>       the type of data in the graph edges
     * @param graph         the graph for which to compute the shortest path spanning
     *                  tree
     * @param start         the vertex at which to start computing the shortest path
     *                  spanning tree
     * @param costs the map of shortest path costs to reach each vertex in the
     *                  graph
     * @return a map that represents the shortest path spanning tree edges
     */ 
    public static <V, E extends Weighted> Map<Vertex<V>, Edge<E>> 
    			  shortestPathTree(Graph<V, E> graph, Vertex<V> start, Map<Vertex<V>, Integer> costs) {
        
    	// Create a map to store edges in the shortest path tree
    	Map<Vertex<V>, Edge<E>> seMap = new LinearProbingHashMap<>();
    	
    	for (Vertex<V> v : costs) {
    		if (!v.equals(start)) {
    			for (Edge<E> e : graph.incomingEdges(v)) {
					Vertex<V> u = graph.opposite(v, e);
					if (costs.get(v).equals(costs.get(u) + e.getElement().getWeight())) {
						seMap.put(v, e);
					}
				}
    		}
		}
    return seMap;
    }        
}