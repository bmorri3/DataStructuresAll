package edu.ncsu.csc316.dsa.map.hashing;

import edu.ncsu.csc316.dsa.map.Map;

/**
 * The LinearProbingHashMap is implemented as a hash table that uses linear
 * probing for collision resolution.
 * 
 * The hash map uses a multiply-and-divide compression strategy for calculating
 * hash functions. The hash map ensures expected O(1) performance of
 * {@link Map#put}, {@link Map#get}, and {@link Map#remove}.
 * 
 * The hash table resizes if the load factor exceeds 0.5.
 * 
 * The LinearProbingHashMap class is based on the implementation developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <K> the type of keys stored in the hash map
 * @param <V> the type of values associated with keys in the hash map
 */
public class LinearProbingHashMap<K, V> extends AbstractHashMap<K, V> {

	/** Field for table of maps */
    private TableEntry<K, V>[] table;
    /** Size of table */
    private int size;

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table uses the
     * {@link AbstractHashMap#DEFAULT_CAPACITY}
     */
    public LinearProbingHashMap() {
        this(AbstractHashMap.DEFAULT_CAPACITY, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * uses the {@link AbstractHashMap#DEFAULT_CAPACITY}
     * 
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(boolean isTesting) {
        this(AbstractHashMap.DEFAULT_CAPACITY, isTesting);
    }

    /**
     * Constructs a new linear probing hash map that uses natural ordering of keys
     * when performing comparisons. The created hash table is initialized to have
     * the provided capacity.
     * 
     * @param capacity the initial capacity of the hash table
     */
    public LinearProbingHashMap(int capacity) {
        this(capacity, false);
    }

    /**
     * FOR TESTING PURPOSES ONLY! Constructs a new linear probing hash map that uses
     * natural ordering of keys when performing comparisons. The created hash table
     * is initialized to have the provided capacity.
     * 
     * @param capacity  the initial capacity of the hash table
     * @param isTesting if true, the hash table uses a predictable series of random
     *                  values for deterministic and repeatable testing
     */
    public LinearProbingHashMap(int capacity, boolean isTesting) {
        super(capacity, isTesting);
        size = 0;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	EntryCollection collection = new EntryCollection();
        
    	for (int i = 0; i < table.length; i++) {
            if (!isAvailable(i))
                collection.add(table[i]);
        }
        
        return collection;
    }

    @SuppressWarnings("unchecked")
    @Override
    public void createTable(int capacity) {
        table = (TableEntry<K, V>[]) new TableEntry[capacity];
        size = 0;
    }

    private boolean isAvailable(int index) {
        return (table[index] == null || table[index].isDeleted());
    }

    @Override
    public V bucketGet(int hash, K key) {
    	// See if the key exists
    	int bucketIndex = findBucket(hash, key);
       
    	// If it does exist, return the value.
        if (bucketIndex >= 0)
        	return table[bucketIndex].getValue();
        // Otherwise, it doesn't exist, so return null.
        return null;
    }

    @Override
    public V bucketPut(int hash, K key, V value) {
        // See if the key exists
    	int bucketIndex = findBucket(hash, key);
       
    	// If it does exist, return the old value and set the new value.
        if (bucketIndex >= 0) {
        	V oldValue = table[bucketIndex].getValue();
        	table[bucketIndex].setValue(value);
        	return oldValue;
        }
        // Otherwise, insert the value at the suggested index -(bucketIndex + 1)
        int index = -(bucketIndex + 1) % table.length; 
        table[index] = new TableEntry<>(key, value);
        // Increment size
        size++;
        // If the entry had been previously deleted, reset isDeleted to false
        if(table[index].isDeleted())
        	table[index].setDeleted(false);
                
        // Key wasn't found, so return null
        return null;
    }

    /**
     * Output the index of the bucket that contains the entry (if it exists), or
     * if the entry is not in the map, return -(a+1) where a is the index where 
     * the entry should be inserted. Comments below adapted from p. 395 of the 
     * text listed above.
     * 
     * @param index bucket index to search
     * @param key key to search for
     * @return the index of the bucket if the key exists, or -(a+1) where a
     * 		   is the index where the entry should be inserted
     */
    private int findBucket(int index, K key) {
    	// No slot available yet
    	int available = -1;
    	// Index while scanning table
    	int j = index;
    	do {
    		if (isAvailable(j)) {
    			// First available slot
    			if (available == -1)
    				available = j;
    			// If the table is empty, the search failed
    			if (table[j] == null)
    				return -(available + 1);
    		}
    		else if (table[j].getKey().equals(key))
    			// Successful match
    			return j;
    		// Look at the next cyclical index
    		j = (j + 1) % table.length;
    	} while (j != index); // Stop if we return to the beginning
    	
    	// Search failed. Key should be added at -(available + 1)
    	return -(available + 1);
    }

    @Override
    public V bucketRemove(int hash, K key) {
    	// See if the key exists
    	int bucketIndex = findBucket(hash, key);
       
    	// If the key exists, remove the entry, setDeleted(true), and return the old value.
        if (bucketIndex >= 0) {
        	V oldValue = table[bucketIndex].getValue();
        	table[bucketIndex].setDeleted(true);
        	size--;
        	
        	return oldValue;
        }
        
        // If the key doesn't exist, return null
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    protected int capacity() {
        return table.length;
    }

    /**
     * Object to store the new element 
     * Contains a field to indicate whether the element at the bucket has been deleted
     * in order to be able to track elements.
     * @author Ben Morris
     *
     * @param <K> Keys of entries
     * @param <V> Values of entries
     */
    private static class TableEntry<K, V> extends MapEntry<K, V> {

    	/** True if key has been deleted */
        private boolean isDeleted;

        /**
         * Constructor
         * @param key key to set
         * @param value value to set
         */
        public TableEntry(K key, V value) {
            super(key, value);
            setDeleted(false);
        }

        /**
         * Returns isDeleted
         * @return isDeleted 
         */
        public boolean isDeleted() {
            return isDeleted;
        }

        /** 
         * Sets isDeleted to deleted
         * @param deleted Boolean value to set isDeleted
         */
        public void setDeleted(boolean deleted) {
            isDeleted = deleted;
        }
    }
}