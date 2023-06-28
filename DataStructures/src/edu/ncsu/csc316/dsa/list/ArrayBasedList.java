package edu.ncsu.csc316.dsa.list;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * An array-based list is a contiguous-memory representation of the List
 * abstract data type. This array-based list dynamically resizes to ensure O(1)
 * amortized cost for adding to the end of the list. Size is maintained as a
 * global field to allow for O(1) size() and isEmpty() behaviors.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the list
 */
public class ArrayBasedList<E> extends AbstractList<E> {

    /**
     * The initial capacity of the list if the client does not provide a capacity
     * when constructing an instance of the array-based list
     **/
    private final static int DEFAULT_CAPACITY = 0;

    /** The array in which elements will be stored **/
    private E[] data;

    /** The number of elements stored in the array-based list data structure **/
    private int size;

    /**
     * Constructs a new instance of an array-based list data structure with the
     * default initial capacity of the internal array
     */
    public ArrayBasedList() {
        this(DEFAULT_CAPACITY);
    }

    /**
     * Constructs a new instance of an array-based list data structure with the
     * provided initial capacity
     * 
     * @param capacity the initial capacity of the internal array used to store the
     *                 list elements
     */
    @SuppressWarnings("unchecked")
    public ArrayBasedList(int capacity) {
        data = (E[]) (new Object[capacity]);
        size = 0;
    }
    
    /**
     * Adds value to data at index.
     * 
     * @param index of data to add value
     * @param value to add at data[index]
     * @throws IndexOutOfBoundsException "Index is invalid: (index) (size=(size()))
     */
    public void add(int index, E value) {
    	//Ensure capacity of array to add another element
    	ensureCapacity(size + 1);
    	//Check index in bounds (see @throws)
    	checkIndexForAdd(index);
    	//Increment size
    	size++;

    	//If empty array, add the element
    	if(size == 1)
    		data[0] = value;
    	//Shift all elements up by one 
    	else
    		for(int i = size - 1; i > index; i-- ) {
    			data[i] = data[i - 1];
    		}
    	//Insert new value
    	data[index] = value;
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
	 * To ensure amortized O(1) cost for adding to the end of the array-based list,
	 * use the doubling strategy on each resize. Here, we add +1 after doubling to
	 * handle the special case where the initial capacity is 0 (otherwise, 0*2 would
	 * still produce a capacity of 0).
	 * 
	 * @param minCapacity the minimium capacity that must be supported by the
	 *                    internal array
	 */
    private void ensureCapacity(int minCapacity) {
        int oldCapacity = data.length;
        if (minCapacity > oldCapacity) {
            int newCapacity = oldCapacity * 2 + 1;
            if (newCapacity < minCapacity) {
                newCapacity = minCapacity;
            }
            data = Arrays.copyOf(data, newCapacity);
        }
    }

    /**
     * Returns E at data[index]
     * @param index index to return
     * @return data[index]
     */
    public E get(int index) {
    	return data[index];
    }
    
    /** 
     * Returns the current size of the list
     * @return size 
     */
    public int size() {
    	return size;
    }

    /**
     * Remove the E at index. Return the E.
     * @return the element removed
     * @throws IndexOutOfBoundsException if the provided index is not a valid index
     *                                   based on the current state of the list
     */
	@Override
	public E remove(int index) {
		if(size == 0 || index < 0 || index >= size)
			throw new IndexOutOfBoundsException();
		
		//Get E to remove
		E removed = data[index];
		
		//Starting at index, shift all elements down one.
		for(int i = index; i < size - 1; i++) {
			data[i] = data[i + 1];
		}
		//Reduce size by 1.
		size--;
		
		return removed;
	}

	/**
	 * Replace the E at index with element.
	 * @param index index of node to replace
	 * @param element element of new node
	 * @return the original E
	 */
	@Override
	public E set(int index, E element) {
		//Get E to replace
		E replaced = data[index];
		
		//Replace E at index with element
		data[index] = element;
		
		return replaced;
	}
	
	/**
	 * Iterates ArrayBasedList
	 */
	private class ElementIterator implements Iterator<E> {
		
	    /** Position of the cursor */		
		private int position;
		/** Flag denoting whether or not conditions have passed in order to remove() */
	    private boolean removeOK;

	    /**
	     * Construct a new element iterator where the cursor is initialized 
	     * to the beginning of the list.
	     */
	    public ElementIterator() {
	       position = 0;
	       removeOK = false;
	    }

	    /**
	     * Returns true if there is an element at this.position.
	     * @return true if there is an element at this.position, false otherwise
	     */
	    @Override
	    public boolean hasNext() {
	        return size() != 0 && position < size() && data[position] != null;
	    }

	    /**
	     * Returns the E at this.position if hasNext() is true
	     * Sets removeOK to true
	     * @return E at this.position
	     * @throws NoSuchElementException "There are no more elements." if there is no next()
	     */
	    @Override
	    public E next() {
	        if(hasNext()) {
	        	removeOK = true;
	        	return data[position++];
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
	    		//If it's the last element, set it to null
	    		if(position == size)
	    			data[position - 1] = null;
	    		//Otherwise, shift data and set the last element in list to null
	    		else
	    			//Starting at this.position, shift all E in data down by one
	    			for(int i = position - 1; i < size - 1; i++) {	    	
		    			data[i] = data[i + 1];
		    		}
	    		// Set end of list to null
	    		data[size - 1] = null;
	    		// Reduce size by 1.
	    		size--;
	    		position--;
	    		
	        	removeOK = false;
	        }
	        else
	        	throw new IllegalStateException("Successful next() not yet called.");
	    }
	}
}