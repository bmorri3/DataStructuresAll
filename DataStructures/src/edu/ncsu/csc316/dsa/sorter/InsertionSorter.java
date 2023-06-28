package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * InsertionSorter uses the insertion sort algorithm to sort data.
 * 
 * @author Dr. King
 * @author Ben Morris
 * 
 * @param <E> Comparable to sort
 */
public class InsertionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	/** Constructor */
	public InsertionSorter() {
        super(null);
    }
    
	/**
	 * Constructor
	 * @param comparator to sort
	 */
    public InsertionSorter(Comparator<E> comparator) {
    	super(comparator);
    }
	
	/**
	 * Uses the insertion sort algorithm to sort data.
	 * @param data E[] array to be sorted.
	 */
	public void sort(E[] data) {
		E e;
		int j = 0;
				
		for(int i = 1; i < data.length; i++) {
			e = data[i];
			j = i - 1;
			while(j >= 0 && compare(data[j], e) > 0) {
				data[j + 1] = data[j];
				j = j - 1;
			}
			data[j + 1] = e;
		}
	}
}
