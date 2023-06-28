package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * RadixSorter uses the radix sort algorithm to sort data by ID number
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the generic type of data to sort
 */
public class RadixSorter<E extends Identifiable> implements Sorter<E> {
	
	/** Number of possible values in a decimal number's place */
	private static final int DECIMAL = 10;

	/** 
	 * Uses the radix sort algorithm to sort 
	 * @param data to sort
	 */
	public void sort(E[] data) {
    	//Initialize max to zero
    	int max = 0;
    	
    	//Find the max to determine the number of digits
    	for(int i = 0; i < data.length; i++) {
    		max = Math.max(data[i].getId(), max);
    	}
    	
    	//Calculate the number of digits in max
    	int numDigits = (int) Math.ceil(Math.log10(max + 1));
    	//Initialize place to one's place
    	int place = 1;
    	
    	@SuppressWarnings("unchecked")
    	E[] sortedArray = (E[])(new Identifiable[data.length]);
    	
    	for(int j = 0; j < numDigits; j++) {
    		int[] countingArray = new int[10];
    		
    		//Tally the frequencies
    		for(int i = 0; i < data.length; i++) {
    			countingArray[(data[i].getId() / place) % DECIMAL]++; 
    		}
    		//Accumulate the frequencies
    		for(int i = 1; i < DECIMAL; i++) {
    			countingArray[i] = countingArray[i - 1] + countingArray[i];
    		}    	
        	
        	//Sort the array into sortedArray[]
        	for(int i = data.length - 1; i >= 0; i--) {
        		int index = countingArray[(data[i].getId() / place) % DECIMAL] - 1;
        		//sortedArray[countingArray[(data[i].getId() / place) % DECIMAL] - 1] = data[i];
        		sortedArray[index] = data[i];
        		countingArray[(data[i].getId() / place) % DECIMAL]--;
        	}
        	
        	//Copy sortedArray[] into data[]
        	for(int i = 0; i < data.length; i++)
        		data[i] = sortedArray[i];
        	
        	place = place * 10;
    	}
    	
    	
    }
}
