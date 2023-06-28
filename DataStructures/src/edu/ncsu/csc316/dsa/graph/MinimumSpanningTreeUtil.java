package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.Weighted;
import edu.ncsu.csc316.dsa.disjoint_set.DisjointSetForest;
import edu.ncsu.csc316.dsa.disjoint_set.UpTreeDisjointSetForest;
import edu.ncsu.csc316.dsa.graph.Graph.Edge;
import edu.ncsu.csc316.dsa.graph.Graph.Vertex;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;
import edu.ncsu.csc316.dsa.priority_queue.AdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.HeapAdaptablePriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue;
import edu.ncsu.csc316.dsa.priority_queue.PriorityQueue.Entry;
import edu.ncsu.csc316.dsa.set.HashSet;
import edu.ncsu.csc316.dsa.set.Set;

/**
 * MinimumSpanningTreeUtil provides a collection of behaviors for computing
 * minimum spanning trees for a given graph.
 * 
 * The MinimumSpanningTreeUtil class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 */
public class MinimumSpanningTreeUtil {
    
    /**
     * Returns a positional list of minimum spanning tree edges for the given graph
     * using Prim-Jarnik's minimum spanning tree algorithm.
     * 
     * @param <V>   the type of data in the graph vertices
     * @param <E>   the type of data in the graph edges
     * @param g the graph for which to compute a minimum spanning tree
     * @return a positional list of minimum spanning tree edges
     */
    public static <V, E extends Weighted> PositionalList<Edge<E>> primJarnik(Graph<V, E> g) {
        AdaptablePriorityQueue<Integer, Vertex<V>> q = new HeapAdaptablePriorityQueue<>();
        Map<Vertex<V>, Integer> weights = new LinearProbingHashMap<>();
        Set<Vertex<V>> known = new HashSet<>();
        Map<Vertex<V>, Entry<Integer, Vertex<V>>> pqEntries = new LinearProbingHashMap<>();
        Map<Vertex<V>, Edge<E>> connectingEdges = new LinearProbingHashMap<>();
        
        PositionalList<Edge<E>> tree = new PositionalLinkedList<>();
        
        Vertex<V> src = g.vertices().iterator().next();
        
        for(Vertex<V> v : g.vertices()) {
            if(v == src) {
                weights.put(v, 0);
            } else {
                weights.put(v, Integer.MAX_VALUE);
            }
            pqEntries.put(v, q.insert(weights.get(v), v));
        }
        while(!q.isEmpty()) {
            Entry<Integer, Vertex<V>> entry = q.deleteMin();
            Vertex<V> u = entry.getValue();
            if(connectingEdges.get(u) != null) {
                tree.addLast(connectingEdges.get(u));
            }
            known.add(u);
            for(Edge<E> e : g.outgoingEdges(u)) {
                Vertex<V> z = g.opposite(u, e);
                int r = e.getElement().getWeight();
                if(!known.contains(z) && r < weights.get(z)) {
                    weights.put(z, r);
                    connectingEdges.put(z, e);
                    q.replaceKey(pqEntries.get(z), r);
                }
            }
        }
        return tree;
    }

	/**
	 * Returns a positional list of minimum spanning tree edges for the given graph
	 * using Kruskal's minimum spanning tree algorithm. Adapted from Dr. King's algorithm in the
	 * project description.
	 * 
	 * @param <V>   the type of data in the graph vertices
	 * @param <E>   the type of data in the graph edges
	 * @param g the graph for which to compute a minimum spanning tree
	 * @return a positional list of minimum spanning tree edges
	 */
	public static <V, E extends Weighted> PositionalList<Edge<E>> kruskal(Graph<V, E> g) {
		// New empty PositionalList to maintain edges in the MST
		PositionalList<Edge<E>> edgeList = new PositionalLinkedList<>();
		// New empty priority queue to keep edges sorted in ascending order
		AdaptablePriorityQueue<Integer, Edge<E>> pq = new HeapAdaptablePriorityQueue<>();
		// New empty forest of disjoint sets
		DisjointSetForest<Vertex<V>> f = new UpTreeDisjointSetForest<>();

		// Insert the edges into the PQ
		for (Edge<E> e : g.edges()) {		
			pq.insert(e.getElement().getWeight(), e);
		}
		
		// Initially, each vertex is in a component by itself
		int components = g.numVertices();
	
		// Create the forest of individual vertices
		for (Vertex<V> v : g.vertices()) {
			f.makeSet(v);
		}
			
		// Process edges in order of increasing weight
		while (components > 1) {
			PriorityQueue.Entry<Integer, Edge<E>> entry = pq.deleteMin();
			Edge<E> edge = entry.getValue();
			Vertex<V>[] endpoints = g.endVertices(edge);
			Position<Graph.Vertex<V>> u = f.find(endpoints[0]);
			Position<Graph.Vertex<V>> v = f.find(endpoints[1]);
			if (!u.equals(v)) {
				f.union(u, v);
				edgeList.addLast(edge);
				components--;
			}
		}
		return edgeList;
	}
}