package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;
import java.util.Random;

/**
 * QuickSorter sorts arrays of comparable elements using the quicksort
 * algorithm. This implementation allows the client to specify a specific pivot
 * selection strategy: (a) use the first element as the pivot, (b) use the last
 * element as the pivot, (c) use the middle element as the pivot, or (d) use an
 * element at a random index as the pivot.
 * 
 * Using the randomized pivot selection strategy ensures O(nlogn)
 * expected/average case runtime when sorting n elements that are comparable
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements to sort; elements must be {@link Comparable}
 */
public class QuickSorter<E extends Comparable<E>> extends AbstractComparisonSorter<E> {
	/**
     * Pivot selection strategy that uses the element at the first index each time a
     * pivot must be selected
     */
    public static final PivotSelector FIRST_ELEMENT_SELECTOR = new FirstElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the last index each time a
     * pivot must be selected
     */
    public static final PivotSelector LAST_ELEMENT_SELECTOR = new LastElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at the middle index each time
     * a pivot must be selected
     */
    public static final PivotSelector MIDDLE_ELEMENT_SELECTOR = new MiddleElementSelector();
    
    /**
     * Pivot selection strategy that uses the element at a randomly-chosen index
     * each time a pivot must be selected
     */
    public static final PivotSelector RANDOM_ELEMENT_SELECTOR = new RandomElementSelector();

    /** PivotSelector strategy chosen */
    private PivotSelector selector;
    
    /**
     * Constructs a new QuickSorter with a provided custom Comparator and a
     * specified PivotSelector strategy
     * 
     * @param comparator a custom comparator to use when sorting
     * @param selector   the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(Comparator<E> comparator, PivotSelector selector) {
        super(comparator);
        setSelector(selector);
    }

    /**
     * Constructs a new QuickSorter using the natural ordering of elements. Pivots
     * are selected using the provided PivotSelector strategy
     * 
     * @param selector the pivot selection strategy to use when selecting pivots
     */
    public QuickSorter(PivotSelector selector) {
        this(null, selector);
    }

    /**
     * Constructs a new QuickSorter with a provided custom Comparator and the
     * default random pivot selection strategy
     * 
     * @param comparator a custom comparator to use when sorting
     */
    public QuickSorter(Comparator<E> comparator) {
        this(comparator, null);
    }

    /**
     * Constructs a new QuickSorter that uses an element's natural ordering and uses
     * the random pivot selection strategy
     */
    public QuickSorter() {
        this(null, null);
    }
    
    /**
     * Sets the selector for the pivot
     * @param selector the pivot selected
     */
    private void setSelector(PivotSelector selector) {
        if(selector == null) {
            this.selector = new RandomElementSelector();
        } else {
            this.selector = selector;
        }
    }
	
	/**
     * Defines the behaviors of a PivotSelector
     * 
     * @author Dr. King
     *
     */
    private interface PivotSelector {
        /**
         * Returns the index of the selected pivot element
         * 
         * @param low  - the lowest index to consider
         * @param high - the highest index to consider
         * @return the index of the selected pivot element
         */
        int selectPivot(int low, int high);
    }
    
    /**
     * FirstElementSelector chooses the first index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class FirstElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return low;
        }
    }
    
    /**
     * LastElementSelector chooses the last index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Ben Morris
     *
     */
    public static class LastElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return high;
        }
    }
    
    /**
     * MiddleElementSelector chooses the (high + low) / 2 index of the array as the index of the
     * pivot element that should be used when sorting
     * 
     * @author Ben Morris
     *
     */
    public static class MiddleElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
            return (high + low) / 2;
        }
    }
    
    /**
     * RandomElementSelector chooses a random of the array index between 
     * high and low (inclusive) as the index of the pivot element that 
     * should be used when sorting
     * 
     * @author Dr. King
     *
     */
    public static class RandomElementSelector implements PivotSelector {

        @Override
        public int selectPivot(int low, int high) {
        	Random r = new Random();
            return r.nextInt(high - low) + low;
        }
    }
    
    /**
     * Main method to call quicksort with the low and high indices of index
     * 
     * @param data data to be sorted
     */
    public void sort(E[] data) {
    	int low = 0;
    	int high = data.length - 1;
    	
    	quickSort(data, low, high);
    }
    
    /**
     * Uses the quick sort algorithm to sort data
     * 
     * @param data data to sort
     * @param low low index of data
     * @param high high index of data
     */
    private void quickSort(E[] data, int low, int high) {
   
    	if(low < high) {
    		int pivotLocation = partition(data, low, high);
    		quickSort(data, low, pivotLocation - 1);
    		quickSort(data, pivotLocation + 1, high);
    	}
    }
    
    /**
     * Separates data into two partitions using the selected pivot by returning a new pivot
     * @param data data to sort
     * @param low low index of data
     * @param high high index of data
     * @return the index of the pivot element after moving values
     */
    private int partition(E[] data, int low, int high) {
    	
    	// Select a Pivot element
    	int pivotIndex = selector.selectPivot(low, high);
    	
    	// Swap the pivot to be the last element in the array
    	E temp = data[pivotIndex];
    	data[pivotIndex] = data[high];
    	data[high] = temp;
    	
    	return partitionHelper(data, low, high);
    }
    
    /**
     * Swaps elements and returns the new pivot
     * 
     * @param data data to sort
     * @param low low index of data
     * @param high high index of data
     * @return the index of the pivot element after moving values
     */
    private int partitionHelper(E[] data, int low, int high) {
    	
    	// The pivot will be in the last index location
    	E  pivot = data[high];
    	
    	E temp;
    	int index = low; // index of smaller element
    	
    	for(int i = low; i < high; i++) {
    		if(compare(data[i], pivot) < 0) {
    			// If data[i], < data[index], swap data[index] and data[i]
    			temp = data[index];
    			data[index] = data[i];
    	    	data[i] = temp;
    	    	
    	    	index++;
    		}
    	}

    	// Swap the index with the pivot. (Data[high] is the pivot.)
    	temp = data[index];
		data[index] = data[high];
    	data[high] = temp;

    	// Return the index of the pivot element
    	return index;
    }
}