package edu.ncsu.csc316.dsa.sorter;


import java.util.Comparator;

/**
 * BubbleSorter uses the bubble sort algorithm to sort data
 * @author Ben Morris
 *
 * @param <E> the generic type of data to sort
 */
public class BubbleSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	
	 /** 
     * Constructor
     * @param comparator Comparator to sort 
     */
    public BubbleSorter(Comparator<E> comparator) {
        super(comparator);
    }
    
    /** Constructor */
	public BubbleSorter() {
        super(null);
    }
    
	/**
	 * Uses the bubble sort algorithm to sort data.
	 * @param data E[] array to be sorted.
	 */
    public void sort(E[] data) {
    	E temp;
    	
    	boolean repeat = true;
    	while(repeat) {
    		repeat = false;
    		for(int i = 1; i < data.length; i++) {
    			if(compare(data[i], data[i - 1]) < 0) {
    				temp = data[i - 1];
    				data[i - 1] = data[i];
    				data[i] = temp;
    				repeat = true;   		
    			}
    		}
    	} 	
    }
}
