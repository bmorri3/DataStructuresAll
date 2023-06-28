package edu.ncsu.csc316.dsa.priority_queue;

import java.util.Comparator;

/**
 * A skeletal implementation of the Priority Queue abstract data type. This
 * class provides implementation for common methods that can be implemented the
 * same no matter what specific type of concrete data structure is used to
 * implement the priority queue abstract data type.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <K> the type of keys (priorities) stored in the priority queue
 * @param <V> the type of values that are associated with keys in the priority
 *            queue
 */
public abstract class AbstractPriorityQueue<K extends Comparable<K>, V> implements PriorityQueue<K, V> {

	/** Comparator to use */
    private Comparator<K> comparator;

    /**
     * Constructs a new AbstractPriorityQueue using a custom comparator
     * 
     * @param c the custom Comparator to use when comparing keys (priorities)
     */
    public AbstractPriorityQueue(Comparator<K> c) {
        setComparator(c);
    }

    /** 
     * Setter for comparator 
     * @param c the custom Comparator to use when comparing keys (priorities)
     */
    private void setComparator(Comparator<K> c) {
        if (c == null) {
            comparator = new NaturalOrder();
        } else {
            comparator = c;
        }
    }
    
    /**
     * Getter for comparator
     * @return comparator
     */
	public Comparator<K> getComparator() {
    	return comparator;
    }

    /**
     * Delegates to the {atlink Comparable#compareTo) implementation that defines the
     * natural ordering of the keys (priorities)
     * 
     * @author Dr. King
     *
     */
    public class NaturalOrder implements Comparator<K> {
    	
    	/**
    	 * Method to compare the keys by natural order.
    	 * 
    	 * @param first 1st key to compare
    	 * @param second 2nd key to compare
    	 * @return greater than zero if first is greater than second
    	 * 		   less than zero if first is less than second
    	 * 		   equal to zero if first is equal to second
    	 */
        public int compare(K first, K second) {
            return ((Comparable<K>) first).compareTo(second);
        }
    }

    /**
     * Compare keys (priorities) to determine their relative ordering. Return a
     * negative number to indicate key1 should appear before key2. Return a positive
     * number to indicate key1 should appear after key2. Return 0 to indicate key1
     * and key2 are considered equal.
     * 
     * @param data1 a key to compare
     * @param data2 a key to compare
     * @return a number to indicate the relative ordering of key1 and key2: a
     *         negative number indicates key1 should appear before key2; a positive
     *         number indicates key1 should appear after key2; 0 indicates the two
     *         keys are considered equal
     */
    public int compare(K data1, K data2) {
        return comparator.compare(data1, data2);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    ////////////////////////////////////////////////////////////////////
    // When using Maps and Priority Queues, double-check that you use
    // the correct imports: MapEntry is used with Maps, PQEntry
    // is used with Priority Queues!
    ////////////////////////////////////////////////////////////////////

    /**
     * PQEntry implements the Entry abstract data type for priority queues.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key (priority) stored in the priority queue entry
     * @param <V> the type of value stored in the priority queue entry
     */
    public static class PQEntry<K, V> implements Entry<K, V> {

    	/** Key of the map */
        private K key;
        /** Value associated with the key */
        private V value;

        /**
         * Constructs a PQEntry with a provided key (priority) and a provided value
         * 
         * @param key   the key (priority) to store in the entry
         * @param value the value to store in the entry
         */
        public PQEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        /**
         * Set the key (priority) of the entry to the provided key
         * 
         * @param key the key (priority) to store in the entry
         */
        public void setKey(K key) {
            this.key = key;
        }

        /**
         * Set the value of the entry to the provided value
         * 
         * @param value the value to store in the entry
         */
        public void setValue(V value) {
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }
    }

    /**
     * Creates a new PQEntry with the provided key and value
     * 
     * @param key   the key (priority) for the new PQEntry
     * @param value the value for the new PQEntry
     * @return the new PQEntry
     */
    protected Entry<K, V> createEntry(K key, V value) {
        return new PQEntry<K, V>(key, value);
    }
}