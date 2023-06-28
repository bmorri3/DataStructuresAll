package edu.ncsu.csc316.dsa.graph;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * An AdjacencyMapGraph is an implementation of the {@link Graph} abstract data
 * type. AdjacencyMapGraph maintains a list of vertices in the graph and a list
 * of edges in the graph. In addition, AdjacencyMapGraph vertices each maintain
 * maps of incoming and outgoing edges to improve efficiency.
 * 
 * The AdjacencyMapGraph class is based on the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <V> the type of data in the vertices in the graph
 * @param <E> the type of data in the edges in the graph
 */
public class AdjacencyMapGraph<V, E> extends AbstractGraph<V, E> {
	/** List of Vertices in the Graph */
    private PositionalList<Vertex<V>> vertexList;
    /** List of Edges in the Graph */
    private PositionalList<Edge<E>> edgeList;
    
    /**
     * Creates a new undirected adjacency map graph
     */
    public AdjacencyMapGraph() {
        this(false);
    }

    /**
     * Creates a new adjacency map graph
     * 
     * @param directed if true, the graph is directed; if false, the graph is
     *                 undirected
     */    
    public AdjacencyMapGraph(boolean directed) {
        super(directed);
        vertexList = new PositionalLinkedList<Vertex<V>>();
        edgeList = new PositionalLinkedList<Edge<E>>();
    }
    
    @Override
    public int numVertices() {
        return vertexList.size();
    }

    @Override
    public Iterable<Vertex<V>> vertices() {
        return vertexList;
    }

    @Override
    public int numEdges() {
        return edgeList.size();
    }

    @Override
    public Iterable<Edge<E>> edges() {
        return edgeList;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> vertex1, Vertex<V> vertex2) {
        return validate(vertex1).getOutgoing().get(vertex2);
    }

    @Override
    public int outDegree(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().size();
    }

    @Override
    public int inDegree(Vertex<V> vertex) {
        return validate(vertex).getIncoming().size();
    }

    @Override
    public Iterable<Edge<E>> outgoingEdges(Vertex<V> vertex) {
        return validate(vertex).getOutgoing().values();
    }

    @Override
    public Iterable<Edge<E>> incomingEdges(Vertex<V> vertex) {
        return validate(vertex).getIncoming().values();
    }

    @Override
    public Vertex<V> insertVertex(V vertexData) {
        AMVertex vertex = new AMVertex(vertexData, isDirected());
        Position<Vertex<V>> pos = vertexList.addLast(vertex);
        vertex.setPosition(pos);
        
        return vertex;
    }

    @Override
    public Edge<E> insertEdge(Vertex<V> v1, Vertex<V> v2, E edgeData) {
        AMVertex origin = validate(v1);
        AMVertex destination = validate(v2);
        GraphEdge edge = new GraphEdge(edgeData, origin, destination);
        Position<Edge<E>> pos = edgeList.addLast(edge);
        edge.setPosition(pos);
        
        // If the graph is undirected, add the edge to both the incoming and outgoing edges of
        // both Vertices
        if (!isDirected()) {
        	origin.incoming.put(destination, edge);
        	origin.outgoing = origin.getIncoming();
        	destination.incoming.put(origin, edge);
        	destination.outgoing = destination.getIncoming();
        }
        else {
        	origin.outgoing.put(destination, edge);
        	destination.incoming.put(origin, edge);
        }

        return edge;
    }

    @Override
    public void removeVertex(Vertex<V> vertex) {
        AMVertex v = validate(vertex);

        // Remove all of the Edges incident to the Vertex
        for (Edge<E> e : incomingEdges(v)) {
            removeEdge(e);
        }
        if (isDirected())
	        for (Edge<E> e : outgoingEdges(v)) {
	            removeEdge(e);
	        }
        else
        	v.outgoing = v.incoming;
        
        // Remove the Vertex from the master list
        vertexList.remove(v.getPosition());
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        GraphEdge e = validate(edge);
        Vertex<V>[] ends = e.getEndpoints();
        AMVertex origin = validate(ends[0]);
        AMVertex destination = validate(ends[1]);

        // Remove the edge from the master Edge list       
        if (getEdge(origin, destination) != null) {        
        	edgeList.remove(e.getPosition());
        }
        
        // If the graph is directed, remove the Edges from origin's outgoing Edges
        // and dest's incoming Edges
        if (isDirected()) {
        	origin.getOutgoing().remove(destination);
        	destination.getIncoming().remove(origin);                
        }
        // If the graph is undirected, remove the Edges from both incoming and outgoing edge Maps
        // of each endpoint
        else {
        	origin.getIncoming().remove(destination);
        	origin.outgoing = origin.getIncoming();        	
        	destination.getIncoming().remove(origin);
        	destination.outgoing = destination.getIncoming();
        }  
    }
    
    /**
     * Represents a vertex in an AdjacencyMapGraph
     * 
     * @author Dr. King
     *
     */
    private class AMVertex extends GraphVertex {

        /**
         * A map of outgoing edges -- opposite vertex to edge to reach opposite vertex
         */
        private Map<Vertex<V>, Edge<E>> outgoing;

        /**
         * A map of incoming edges -- opposite vertex to edge to reach opposite vertex
         */
        private Map<Vertex<V>, Edge<E>> incoming;

        /**
         * Creates a new adjacency map vertex.
         * 
         * @param data       the data to store in the vertex
         * @param isDirected if true, the vertex belongs to a directed graph; if false,
         *                   the vertex belongs to an undirected graph
         */
        public AMVertex(V data, boolean isDirected) {
            super(data);
            outgoing = new LinearProbingHashMap<Vertex<V>, Edge<E>>();
            if (isDirected) {
                incoming = new LinearProbingHashMap<>();
            } else {
                incoming = outgoing;
            }
        }

        /**
         * Returns a map of outgoingEdges from the vertex. For an undirected graph,
         * returns the same as getIncoming()
         * 
         * @return a map of outgoing edges from the vertex
         */
        public Map<Vertex<V>, Edge<E>> getOutgoing() {
            return outgoing;
        }

        /**
         * Returns a map of incomingEdges to the vertex. For an undirected graph,
         * returns the same as getOutgoing()
         * 
         * @return a map of incoming edges to the vertex
         */
        public Map<Vertex<V>, Edge<E>> getIncoming() {
            return incoming;
        }
    }

    /**
     * Safely casts a Vertex to an adjacency map vertex
     * 
     * @param v Vertex to validate
     * @return an adjacency map vertex representation of the given Vertex
     * @throws IllegalArgumentException if the vertex is not a valid adjacency map
     *                                  vertex
     */
    private AMVertex validate(Vertex<V> v) {
        if (!(v instanceof AdjacencyMapGraph.AMVertex)) {
            throw new IllegalArgumentException("Vertex is not a valid adjacency map vertex.");
        }
        return (AMVertex) v;
    }
}