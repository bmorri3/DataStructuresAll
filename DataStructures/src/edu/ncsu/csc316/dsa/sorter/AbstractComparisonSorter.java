package edu.ncsu.csc316.dsa.sorter;

import java.util.Comparator;

/**
 * Abstract Class for sorting. Reduces redundancy.
 * @author Dr. King
 * @author Ben Morris
 * @param <E> Comparable to sort
 */
public abstract class AbstractComparisonSorter<E extends Comparable<E>> implements Sorter<E> {

	/** Comparator field */
    private Comparator<E> comparator;
    
    /** 
     * Constructor 
     * @param comparator Comparator to set
     */
    public AbstractComparisonSorter(Comparator<E> comparator) {
        setComparator(comparator);
    }
    
    /** 
     * Set Comparator 
     * 
     * @param comparator Comparator to set
     */
    private void setComparator(Comparator<E> comparator) {
        if(comparator == null) {
            this.comparator = new NaturalOrder();
        } else {
            this.comparator = comparator;
        }
    }   
    
    /**
     * Subclass to define sorting by natural order
     * 
     * @author Ben Morris
     */
    public class NaturalOrder implements Comparator<E> {
    	/** 
    	 * Implementation of compare() 
    	 * @param first Element to compare
    	 * @param second Element to compare
    	 * @return less than 0 if first is less than second, 
    	 * 		   0 if first = second, 
    	 * 		   greater than 0 if first is greater than second
    	 */
        public int compare(E first, E second) {
            return ((Comparable<E>) first).compareTo(second);
        }
    }
    
    /** 
     * Implementation of compare()
     * 
     * @param data1 One Element to compare
     * @param data2 Another Element to compare 
     * @return comparator of the two parameters
     */
    public int compare(E data1, E data2) {
        return comparator.compare(data1,  data2);
    }
}