package edu.ncsu.csc316.dsa.sorter;

import edu.ncsu.csc316.dsa.data.Identifiable;

/**
 * CountingSorter uses the counting sort algorithm to sort data by ID number
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the generic type of data to sort
 */
public class CountingSorter<E extends Identifiable> implements Sorter<E> {
    
	/** 
	 * Uses the counting sort algorithm to sort 
	 * @param data to sort
	 */
    public void sort(E[] data) {
    	//Initialize min and max to element zero
    	int min = data[0].getId();
    	int max = data[0].getId();
    	
    	//Find the min and max
    	for(int i = 0; i < data.length; i++) {
    		min = Math.min(data[i].getId(), min);
    		max = Math.max(data[i].getId(), max);
    	}
    	
    	//Find the range
    	int range = max - min + 1;
    	
    	int[] countingArray = new int[range];
    	
    	//Shift to index 0 and tally the frequencies
    	for(int i = 0; i < data.length; i++) {
    		countingArray[data[i].getId() - min]++;
    	}
    	
    	//Accumulate the frequencies
    	for(int i = 1; i < range; i++) {
    		countingArray[i] = countingArray[i - 1] + countingArray[i];
    	}
    	
    	@SuppressWarnings("unchecked")
    	E[] sortedArray = (E[])(new Identifiable[data.length]);
    	
    	//Sort the array into sortedArray[]
    	for(int i = data.length - 1; i >= 0; i--) {
    		sortedArray[countingArray[data[i].getId() - min] - 1] = data[i];
    		countingArray[data[i].getId() - min]--;
    	}
    	
    	//Copy sortedArray[] into data[]
    	for(int i = 0; i < data.length; i++)
    		data[i] = sortedArray[i];
    }
}
