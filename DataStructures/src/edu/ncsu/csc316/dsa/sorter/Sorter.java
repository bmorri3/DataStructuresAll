package edu.ncsu.csc316.dsa.sorter;

/**
 * Interface that defines the sorting behavior
 * @author Dr. King
 * @author Ben Morris
 * @param <E> to sort
 */
public interface Sorter<E> {
	/** 
	 * Sort method to be implemented at the subclass level 
	 * @param data to sort
	 */
	void sort(E[] data);
}
