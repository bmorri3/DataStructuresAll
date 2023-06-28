package edu.ncsu.csc316.dsa.disjoint_set;

import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.map.Map;
import edu.ncsu.csc316.dsa.map.hashing.LinearProbingHashMap;

/**
 * The UpTreeDisjointSetForest is implemented as a forest of linked up-trees.
 * Using balanced union, {@link DisjointSetForest#union} has worst-case runtime
 * of O(1). Using path-compression find, {@link DisjointSetForest#find} has
 * worst-case O(logn), but over time has worst-case runtime O(log*(n))
 * [log-star].
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the disjoint set
 */
public class UpTreeDisjointSetForest<E> implements DisjointSetForest<E> {
    
	/** Map for element->position */
    private Map<E, UpTreeNode<E>> map;

    /**
     * Constructs a new DisjointSetForest
     */
    public UpTreeDisjointSetForest() {
        // Use an efficient map!
        map = new LinearProbingHashMap<E, UpTreeNode<E>>();
    }

    /**
     * An UpTreeNode maintains an element, a reference to the node's parent, and (if
     * it's the root of an up-tree) the number of nodes stored within that up-tree.
     * 
     * @author Dr. King
     *
     * @param <E> Type of Element to store in node
     */
    private static class UpTreeNode<E> implements Position<E> {

    	/** Element stored in node */
        private E element;
        /** parent of node, itself if the node is the root */
        private UpTreeNode<E> parent;
        /** Number of nodes in the UpTree */
        private int count;

        /**
         * Constructs a new UpTreeNode with the given element, a reference to itself as
         * the parent, and a count of 1.
         * 
         * @param element the element to store in the new UpTreeNode
         */
        public UpTreeNode(E element) {
            setElement(element);
            setParent(this);
            setCount(1);
        }

        /**
         * Sets the element of the UpTreeNode to the given element
         * 
         * @param element the element to store in the UpTreeNode
         */
        public void setElement(E element) {
            this.element = element;
        }

        @Override
        public E getElement() {
            return element;
        }

        /**
         * Sets the parent of the UpTreeNode to the given UpTreeNode
         * 
         * @param parent the UpTreeNode to set as the current node's parent
         */
        public void setParent(UpTreeNode<E> parent) {
            this.parent = parent;
        }

        /**
         * Returns a reference to the parent of the current UpTreeNode
         * 
         * @return a reference to the parent of the current UpTreeNode
         */
        public UpTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the number of nodes contained in the UpTree rooted at the current
         * UpTreeNode
         * 
         * @param count Number of nodes contained in the UpTree rooted at current
         * UpTreeNode
         */
        public void setCount(int count) {
            this.count = count;
        }

        /**
         * If the current UpTreeNode is the root of an up-tree, returns the number of
         * elements stored within the UpTree. Otherwise, if the current UpTreeNode is
         * not the root of an up-tree, count is undefined.
         * 
         * @return the number of elements stored within the UpTree, if the current
         *         UpTreeNode is the root; otherwise, count is undefined.
         */
        public int getCount() {
            return count;
        }
    }

    @Override
    public Position<E> makeSet(E value) {
        UpTreeNode<E> newNode = new UpTreeNode<E>(value);
        newNode.setParent(newNode);
        newNode.setCount(1);
        
        // Add value and its node to the map
        map.put(value, newNode);
        
        return newNode;
    }

    @Override
    public Position<E> find(E value) {
        // NOTE: The textbook solution requires the client to keep
        // track of the location of each position in the forest.
        // Our implementation includes a Map to handle this for the
        // client, so we should allow the client to find the set
        // that contains a node by specifying the element
    	
        UpTreeNode<E> node = map.get(value);
//        System.out.println(value.toString() + " " + node.getParent().toString());
        
        while (node.getParent() != node) {
        	node = node.getParent();
        }
        
        return node;
    }

    /**
     * Find using the recursive path compression find algorithm
     * @param current node to find root of set for
     * @return root node of the set containing current 
     */
    private UpTreeNode<E> findHelper(UpTreeNode<E> current) {    		
    	if(!current.equals(current.getParent())) {
    		current.setParent(findHelper(current.getParent()));
    	}

    	return current.getParent();
    }

    @Override
    public void union(Position<E> s, Position<E> t) {
        UpTreeNode<E> a = validate(s);
        UpTreeNode<E> b = validate(t);
        UpTreeNode<E> aParent = findHelper(a);
        UpTreeNode<E> bParent = findHelper(b);        
        	 
        if (aParent.count > bParent.count) {
        	aParent.setCount(aParent.getCount() + bParent.getCount());
        	bParent.setParent(aParent);
        }
        else {
        	bParent.setCount(bParent.getCount() + aParent.getCount());
    		aParent.setParent(bParent);
        }       
    }

    private UpTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof UpTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid up tree node.");
        }
        return (UpTreeNode<E>) p;
    }
}