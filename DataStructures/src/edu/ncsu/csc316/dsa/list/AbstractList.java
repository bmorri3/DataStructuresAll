package edu.ncsu.csc316.dsa.list;

/**
 * A skeletal implementation of the List abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the list
 * abstract data type.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the list
 */
public abstract class AbstractList<E> implements List<E> {

	/** addFirst for AbstractList */
    @Override
    public void addFirst(E element) {
        add(0, element);
    }

    /** addLast for AbstractList */
    @Override
    public void addLast(E element) {
        add(size(), element);
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when accessing any specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /**
     * Checks whether the provided index is a legal index based on the current state
     * of the list. This check should be performed when adding elements at specific
     * indexes within the list.
     * 
     * @param index the index for which to check whether it is valid/legal in the
     *              current list or not
     */
    protected void checkIndexForAdd(int index) {
        if (index < 0 || index > size()) {
            throw new IndexOutOfBoundsException("Index is invalid: " + index + " (size=" + size() + ")");
        }
    }

    /** 
     * Returns first element of the data array
     * @return first element
     */
    @Override
    public E first() {
        return get(0);
    }

    /**
     * Returns true if the array is empty, false if not
     * @return true if the array is empty, false if not
     */
    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns last element of the array
     * @return last element of the array
     */
    @Override
    public E last() {
        return get(size() - 1);
    }

    /**
     * Removes and returns the first element of the array
     * @return the first element of the array
     */
    @Override
    public E removeFirst() {
        return remove(0);
    }

    /** 
     * Removes and returns the last element of the array
     * @return the last element of the array
     */
    @Override
    public E removeLast() {
        return remove(size() - 1);
    }
}