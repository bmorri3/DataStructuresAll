package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * SelectionSorter uses the selection sort algorithm to sort data
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the generic type of data to sort
 */
public class SelectionSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
    
    /** 
     * Constructor
     * @param comparator Comparator to sort 
     */
    public SelectionSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /** Constructor */
	public SelectionSorter() {
        super(null);
    }
    
    /**
	 * Uses the selection sort algorithm to sort data.
	 * @param data E[] array to be sorted.
	 */
    public void sort(E[] data) {
    	E e;
    	int min = 0;
  
    	for(int i = 0; i < data.length; i++) {
    	    min = i;
    	    for(int j = i + 1; j < data.length; j++) {
    	        if(compare(data[j], data[min]) < 0)
    	            min = j; 
    	    }
    	    if(i != min) {
    	    	e = data[i];
    	    	data[i] = data[min];
    	    	data[min] = e;
    	    }
    	}
    }
}