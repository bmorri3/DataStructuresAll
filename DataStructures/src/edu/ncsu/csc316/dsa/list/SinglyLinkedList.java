package edu.ncsu.csc316.dsa.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly-linked list is a linked-memory representation of the List abstract
 * data type. This list maintains a dummy/sentinel front node in the list to
 * help promote cleaner implementations of the list behaviors. This list also
 * maintains a reference to the tail/last node in the list at all times to
 * ensure O(1) worst-case cost for adding to the end of the list. Size is
 * maintained as a global field to allow for O(1) size() and isEmpty()
 * behaviors.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the list
 */
public class SinglyLinkedList<E> extends AbstractList<E> {

    /** A reference to the dummy/sentinel node at the front of the list **/
    private LinkedListNode<E> front;  
    /** A reference to the last/final node in the list **/
    private LinkedListNode<E> tail;
    /** The number of elements stored in the list **/
    private int size;
        
    /**
     * Constructs an empty singly-linked list
     */     
    public SinglyLinkedList() {
        front = new LinkedListNode<E>(null);
        tail = null;
        size = 0;
    }
    
    /**
     * Constructs and returns new Iterator
     * @return new ElementIterator
     */
    @Override
    public Iterator<E> iterator() {
        return new ElementIterator();
    }
    
    /**
     * Returns the last element in the list. Throws an IndexOutOfBoundsException if the list is empty.
     */
    @Override
    public E last() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return tail.getElement();
    }
    
    /**
     * Returns the first element in the list. Throws an IndexOutOfBoundsException if the list is empty.
     */
    @Override
    public E first() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException("The list is empty");
        }
        return front.next.getElement();
    }    

    /**
     * {@inheritDoc}
     * For this singly-linked list, addLast(element) behavior has O(1) worst-case runtime.
     */ 
    @Override
    public void addLast(E element) {
        checkIndexForAdd(size);
        add(size, element); 
    }
    
    /**
     * add E at index
     * @param index index of list to add E
     * @param element E to add
     */
    public void add(int index, E element) {
    	//Check index in bounds (see @throws)
    	checkIndexForAdd(index);
    	
    	LinkedListNode<E> currentNode = front;
    	
    	for(int i = 0; i < index; i++) {
    		currentNode = currentNode.getNext();
    	}
    	
    	LinkedListNode<E> newNode = new LinkedListNode<E>(element, currentNode.getNext());
    	currentNode.setNext(newNode);
    	
    	if(index == size)
    		tail = newNode;
    	
    	size++;
    }
    
    /**
     * Remove the E at index
     * @param index index to remove
     * @return the removed E
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
    public E remove(int index) {
    	if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
    	
    	E removed = get(index);
    	
    	LinkedListNode<E> currentNode = front;
    	
    	//Iterate to the node before index
    	for(int i = 0; i < index; i++) {
    		currentNode = currentNode.getNext();
    	}
    	  	
    	// If removing the last element, set new tail
    	if(index == size - 1) {
    		tail = currentNode;
    		currentNode.setNext(null);
    	}
    	else {
	    	// Assigning next by skipping a node
	    	currentNode.setNext(currentNode.getNext().getNext());
    	}
    	
    	// Decrement size
    	size--;
    	
    	return removed;
    }
    
    /**
     * Replace the E at index with element.
     * @param index index of node to replace
	 * @param data element of new node
	 * @return the original E
     */
    public E set(int index, E data) {
    	E replaced = get(index);
    	
    	LinkedListNode<E> currentNode = front;

    	//Iterate to the appropriate node
    	for(int i = 0; i < index; i++) {
    		currentNode = currentNode.getNext();
    	}
    	
    	//Add a new node with data at index, but skip the node to replace
    	LinkedListNode<E> newNode = new LinkedListNode<E>(data, currentNode.getNext().getNext());
    	currentNode.setNext(newNode);
    	
    	return replaced;
    }
    
    /**
     * Gets size of list
     * @return size of list
     */
    public int size() {
    	return size;
    }
    
    /**
     * Get the data at index
     * @param index index of list to grab data from
     * @return data at node with index
     */
    public E get(int index) {
    	LinkedListNode<E> currentNode = front;
    	
    	for(int i = 0; i <= index; i++) {
    		currentNode = currentNode.getNext();
    	}
    	    	
    	return currentNode.getElement();
    }
    
    /**
     * Class for nodes for the SinglyLinkedList Class.
     * 
     * @param <E> the type of elements stored in the list 
     */
    private static class LinkedListNode<E> {
        
    	/** Data stored in node */
        private E data;
        /** Link to the next node */
        private LinkedListNode<E> next;
        
        /** 
         * Constructor
         * @param element E to store in the node 
         */
        private LinkedListNode(E element) {
        	setElement(element);
        }
        
        /**
         * Constructor
         * @param element E to store in the node
         * @param node next node
         */
        private LinkedListNode(E element, LinkedListNode<E> node) {
        	setElement(element);
        	setNext(node);
        }
        
        /**
         * Get the next node
         * @return next next node
         */
        private LinkedListNode<E> getNext() {
        	return next;
        }
        
        /**
         * Get the element stored in the node
         * @return E data stored in the node
         */
        private E getElement() {
        	return data;
        }
        
        /**
         * Set node's next node
         * @param node next node to set
         */
        private void setNext(LinkedListNode<E> node) {
        	next = node;
        }
        
        /**
         * Set node's data
         * @param element element to store at the node 
         */
        private void setElement(E element) {
        	data = element;
        }
    }
    
    /**
     * Class to iterate SinglyLinkedList
     */
    private class ElementIterator implements Iterator<E> {
        /**
         * Keep track of the next node that will be processed
         */
        private LinkedListNode<E> current;
        
        /** 
         * Keep track of the node that was processed on the last call to 'next'
         */
        private LinkedListNode<E> previous;
        
        /** 
         * Keep track of the previous-previous node that was processed
         * so that we can update 'next' links when removing
         */
        private LinkedListNode<E> previousPrevious;
        
        /**
         * Keep track of whether it's ok to remove an element (based on whether
         * next() has been called immediately before remove())
         */
        private boolean removeOK;

        /**
         * Construct a new element iterator where the cursor is initialized 
         * to the beginning of the list.
         */
        public ElementIterator() {
            current = front;
            previous = null;
            previousPrevious = null;
            removeOK = false;
        }

        /**
         * Returns true if there is a next node, false if not
         * @return true if there is a next node, false if not
         */
        @Override
        public boolean hasNext() {
        	return current != null && current.next != null;    		
        }

        /**
	     * Returns the E at current if hasNext() == true
	     * Sets removeOK to true.
	     * @return E at current
	     * @throws NoSuchElementException "There are no more elements." if there is no next()
	     */
        @Override
        public E next() {
        	if(hasNext()) {
        		// It is okay to remove
	        	removeOK = true;
	        	// Reassign nodes
	        	previousPrevious = previous;
	        	previous = current;
	        	current = current.getNext();
	        	// Return element
	        	return current.getElement();
	        }
	        else
	        	throw new NoSuchElementException("There are no more elements.");	 	
	    }
        
        /**
	     * Removes the E at this.position
	     */
        @Override    
        public void remove() {
	    	if(removeOK) {
	    		//If it's the last element, set tail to previous node
	    		if(current == tail)
	    			tail = previous;
	    		
	    		//Otherwise, delete node by skipping it

	    		// Reassign nodes
	    		// Skip current node
	    		previous.setNext(current.getNext());
	    		// Back the cursor up to the previous node
	    		current = previous;
	    		// Move the other two nodes back
	    		previous = previousPrevious;
	    		previousPrevious = null;
	    		
	    		// Reduce size by 1.
	    		size--;
	    		// Reset removeOK to false
	        	removeOK = false;
	        }
	        else
	        	throw new IllegalStateException("Successful next() not yet called.");
	    }
    }
}