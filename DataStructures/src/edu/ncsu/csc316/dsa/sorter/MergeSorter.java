package edu.ncsu.csc316.dsa.sorter;

import java.util.Arrays;
import java.util.Comparator;

/**
 * MergeSorter sorts arrays of comparable elements using the merge sort
 * algorithm. This implementation ensures O(nlogn) worst-case runtime to sort an
 * array of n elements that are comparable.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class MergeSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {

    /**
     * Constructs a new MergeSorter with a specified custom Comparator
     * 
     * @param comparator a custom Comparator to use when sorting
     */
    public MergeSorter(Comparator<E> comparator) {
        super(comparator);
    }

    /**
     * Constructs a new MergeSorter with comparisons based on the element's natural
     * ordering
     */ 
    public MergeSorter() {
        this(null);
    }
    
    /**
     * MergeSorter uses the merge sort algorithm to sort data.
     * Adapted from "Data Structures and Algorithms", p. 538 
     * Goodrich, Tamassia, and Goldwasser
     * 
     * Sorts students based on last name.
     * 
     * @author Ben Morris
     *
     * @param data generic array of data to sort
     */
    public void sort(E[] data) {
    	
    	
    	if(data.length < 2)
    		return;
    	
    	int mid = data.length / 2;
    	
    	E[] left = Arrays.copyOfRange(data,  0, mid);
    	E[] right = Arrays.copyOfRange(data,  mid, data.length);
    			
		sort(left);
		sort(right);
		
		merge(left, right, data);
    }
    
    /**
     * Uses the merge algorithm to merge two arrays together by increasing order of the elements
     * @param left initial left array to compare element by element to right
     * @param right inital right array to compare element by element to left
     * @param data merged array
     */
    public void merge(E[] left, E[] right, E[] data) {
    	int leftIndex = 0;
    	int rightIndex = 0;
    	
    	while(leftIndex + rightIndex < data.length) {
    		if(rightIndex == right.length || 
    		  leftIndex < left.length && compare(left[leftIndex], right[rightIndex]) < 0) { 
    			data[leftIndex + rightIndex] = left[leftIndex];
    			leftIndex++;
    		}
    		else {
    		    data[leftIndex + rightIndex] = right[rightIndex];
    			rightIndex++;
    		}
    	}
    }
}