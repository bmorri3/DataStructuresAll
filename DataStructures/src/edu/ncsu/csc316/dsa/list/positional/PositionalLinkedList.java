package edu.ncsu.csc316.dsa.list.positional;

import java.util.Iterator;

import java.util.NoSuchElementException;

import edu.ncsu.csc316.dsa.Position;

/**
 * The Positional Linked List is implemented as a doubly-linked list data
 * structure to support efficient, O(1) worst-case Positional List abstract data
 * type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The PositionalLinkedList class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the positional list
 */
public class PositionalLinkedList<E> implements PositionalList<E> {

    /** A dummy/sentinel node representing at the front of the list **/
    private PositionalNode<E> front;

    /** A dummy/sentinel node representing at the end/tail of the list **/
    private PositionalNode<E> tail;

    /** The number of elements in the list **/
    private int size;

    /**
     * Constructs an empty positional linked list
     */
    public PositionalLinkedList() {
        front = new PositionalNode<E>(null);
        tail = new PositionalNode<E>(null, null, front);
        front.setNext(tail);
        size = 0;
    }
    
    /**
     * Helper method. Adds a new node with element after prev and before next
     * @param element element to add
     * @param next node to add before
     * @param prev node to add after
     * @return position of new node
     */
    private Position<E> addBetween(E element, PositionalNode<E> next, PositionalNode<E> prev) {
        PositionalNode<E> newNode = new PositionalNode<E>(element, next, prev);
        
        // Set previous's next node to newNode
        prev.setNext(newNode);
        // Set next's previous node to newNode
        next.setPrevious(newNode);
        // Increment size
        size++;
        
        return newNode;
    }
    
    /**
     * Returns the size of the list
     * @return size of the list
     */
    public int size() {
    	return size;
    }
    
    /**
     * Creates a new iterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Adds a new node with element after prev
     * @param prev node to add after
     * @param element element to add
     * @return position of new node
     */
    public Position<E> addAfter(Position<E> prev, E element) {
    	PositionalNode<E> node = validate(prev);
    	
    	return addBetween(element, node.getNext(), node);
    }
    
    /**
     * Adds a new node with element before node
     * @param next node to add before
     * @param element element to add
     * @return position of new node
     */
    public Position<E> addBefore(Position<E> next, E element) {
    	PositionalNode<E> node = validate(next);
    	
    	return addBetween(element, node, node.getPrevious());
    }
    
    /**
     * Adds a new node after the head sentinel node
     * @param element element to add
     * @return position of new node
     */
    public Position<E> addFirst(E element) {
    	 	   	
    	return addAfter(front, element);
    }
    
    /**
     * Adds a new node at the tail sentinel node
     * @param element element to add
     * @return position of new node
     */
    public Position<E> addLast(E element) {
    	return addAfter(tail.previous, element);
    }
    
    /**
     * Returns the node after element
     * @param element node to look after
     * @return the node after element
     */
    public Position<E> after(Position<E> element) {
    	PositionalNode<E> node = validate(element);
    	
    	if(node.getNext().equals(tail))
    		return null;
    	else
    		return node.getNext();
    }
    
    /** 
     * Returns the node before the element
     * @param element node to look before
     * @return the node before the element
     */
    public Position<E> before(Position<E> element) {
    	PositionalNode<E> node = validate(element);

    	if(node.getPrevious().equals(front))
    		return null;
    	else 
    		return node.getPrevious(); 
    }
    
    /**
     * Gets the first element of the list.
     * 
     * @return the first element of the list
     */
    public PositionalNode<E> first() {
    	if(front.getNext().getElement() == null)
    		return null;
    	return front.getNext();
    }
    
    /**
     * Returns true if the list is empty
     * 
     * @return true if the list is empty
     */
    public boolean isEmpty() {
    	return size == 0;
    }

    /**
     * Gets the last element of the list
     * 
     * @return the last element of the list
     */
    public PositionalNode<E> last() {
    	if(tail.getPrevious().getElement() == null)
    		return null;
    	
    	return tail.getPrevious();
    }
    
    /**
	 * Returns a new Iterable collection of list Positions
	 * 
	 * @return a new Iterable collection of list positions
	 */
    @Override
    public Iterable<Position<E>> positions() {
        return new PositionIterable();
    }
    
    /**
     * Removes node at position remove
     * @param remove Position to remove
     * @return removed node
     */
    public E remove(Position<E> remove) {
    	PositionalNode<E> node = validate(remove);
    	
    	node.previous.setNext(node.getNext());
    	node.next.setPrevious(node.getPrevious());
   		size--;    		
    	
    	return node.getElement();
    }
    
    /**
     * Sets nodeToSet's element
     * 
     * @param nodeToSet node whose element to set
     * @param element element to set
     * @return original element
     */
    public E set(Position<E> nodeToSet, E element) {
    	// Validate the node
    	PositionalNode<E> node = validate(nodeToSet);
    	
    	// Get the original element to return
    	E originalElement = node.getElement();
    	
    	// Set the new element
    	node.setElement(element);
    	
    	return originalElement;
    }
    
    /**
	 * Safely casts a Position, p, to be a PositionalNode.
	 * 
	 * @param p the position to cast to a PositionalNode
	 * @return a reference to the PositionalNode
	 * @throws IllegalArgumentException if p is null, or if p is not a valid
	 *                                  PositionalNode
	 */
	private PositionalNode<E> validate(Position<E> p) {
	    if (p instanceof PositionalNode) {
	        return (PositionalNode<E>) p;
	    }
	    throw new IllegalArgumentException("Position is not a valid positional list node.");
	}

	/**
     * Subclass to implement PositionNode
     * 
     * @author Ben Morris
     *
     * @param <E> Element
     */
    private static class PositionalNode<E> implements Position<E> {

    	/** element of node */
        private E element;
        /** next node */
        private PositionalNode<E> next;
        /** previous node */
        private PositionalNode<E> previous;

        /**
         * Constructor
         * @param value E to initialize to node
         */
        public PositionalNode(E value) {
            this(value, null);
        }

        /**
         * Constructor
         * @param value E to initialize to node
         * @param next next node
         */
        public PositionalNode(E value, PositionalNode<E> next) {
            this(value, next, null);
        }

        /**
         * Constructor
         * @param value E to inititialize to node
         * @param next next node
         * @param prev previous node
         */
        public PositionalNode(E value, PositionalNode<E> next, PositionalNode<E> prev) {
            setElement(value);
            setNext(next);
            setPrevious(prev);
        }

        /**
         * Sets current's previous node
         * @param prev node to set
         */
        public void setPrevious(PositionalNode<E> prev) {
            previous = prev;
        }

        /**
         * Gets current's previous node
         * @return current's previous node
         */
        public PositionalNode<E> getPrevious() {
            return previous;
        }
        
        /**
         * Sets current's next node
         * @param next node to set
         */
        public void setNext(PositionalNode<E> next) {
            this.next = next;
        }

        /**
         * Gets current's next node
         * @return current's next node
         */
        public PositionalNode<E> getNext() {
            return next;
        }

        /** 
         * Gets this.element 
         * @return this.element 
         */
        @Override
        public E getElement() {
            return element;
        }
        
        /**
         * Sets this.element
         * @param element to set
         */
        public void setElement(E element) {
            this.element = element;
        }
    }
    
    /**
     * Class to iterate Positions 
     */
    private class PositionIterator implements Iterator<Position<E>> {

    	/** Current position field */
        private Position<E> current;
        /** Have the conditions been met to remove a position? */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public PositionIterator() {
        	current = front;
            removeOK = false;
        }

        /**
         * Returns true if there is a next node, false if not
         * @return true if there is a next node, false if not
         */
        @Override
        public boolean hasNext() {
        	// Validate current
        	PositionalNode<E> node = validate(current);
        	
        	// If neither the current node nor the next node are the tail, return true.
        	return node.next != null && node.next.getElement() != null;
        }

        /**
	     * Returns the E at current if hasNext() == true
	     * Sets removeOK to true.
	     * @return E at current
	     * @throws NoSuchElementException "There are no more elements." if there is no next()
	     */
        @Override
        public Position<E> next() {
        	if(hasNext()) {
        		// Validate current
        		PositionalNode<E> node = validate(current);
        		// It is okay to remove
	        	removeOK = true;
	        	// Reassign node
	        	current = node.getNext();
	        	// Return new current
	        	return current;
	        }
	        else
	        	throw new NoSuchElementException("There are no more elements.");	
        }

        /**
         * Removes the node at current if removeOK is true
         */
        @Override
        public void remove() {
        	// Validate current
        	PositionalNode<E> node = validate(current);
        	
        	// Check removeOK and call PositionalLinkedList.this.remove() if true
        	// Set current to the next node.
        	if(removeOK) {
        		PositionalLinkedList.this.remove(node);
        		current = node.getPrevious();
        		removeOK = false;
        	}
	        else
	        	throw new IllegalStateException("Successful next() not yet called.");
        }
    }
    
    /**
     * Class to iterate Elements
     */
    private class ElementIterator implements Iterator<E> {
    	/** Iterator field */
        private Iterator<Position<E>> it;

        /** Constructor */
        public ElementIterator() {
            it = new PositionIterator();
        }

        /** 
         * Returns true if there is a next Element, false if not 
         * @return true if there is a next Element, false if not
         */
        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        /**
	     * Returns the E at current if hasNext() == true
	     * Sets removeOK to true.
	     * @return E at current
         */
        @Override
        public E next() {
            return it.next().getElement();
        }

        /**
         * Removes node at current if RemoveOK is true
         */
        @Override
        public void remove() {
            it.remove();
        }
    }
    
    /**
     * Class to iterate by position
     */
    private class PositionIterable implements Iterable<Position<E>> {
        
    	/** 
    	 * Returns new PositionIterator
    	 * @return new PositionIterator 
    	 */
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }
}