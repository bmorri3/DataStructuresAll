package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Iterator;
import edu.ncsu.csc316.dsa.list.ArrayBasedList;

/**
 * A Search Table map is an ordered (meaning entries are stored in a sorted
 * order based on the keys of the entries) contiguous-memory representation of
 * the Map abstract data type. This array-based map delegates to an existing
 * array-based list. To improve efficiency of lookUps, the search table map
 * implements binary search to locate entries in O(logn) worst-case runtime.
 * Insertions and deletions have O(n) worst-case runtime.
 * 
 * @author Dr. King
 * 
 * Further implementation done by
 * @author Richard Bedford
 * 
 * CSC 316 Summer 2022
 * Workshop 5
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class SearchTableMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {
	/** Underlying data structure for our SearchTableMap */
    private ArrayBasedList<Entry<K, V>> list;

    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SearchTableMap() {
        this(null);
    }
    
    /**
     * Constructs a new SearchTableMap where keys of entries are compared based on a
     * provided {@link Comparator}
     * 
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */ 
    public SearchTableMap(Comparator<K> compare) {
        super(compare);
        list = new ArrayBasedList<Entry<K, V>>();
    }
    
    /**
     * Private search helper that determines if a key exists within the SearchTableMap and returns the 
     * proper index position, offset by 1
     * @param key The key to locate
     * @return The index at which a newly added key, or one that isn't already in the Map, should be added by calling another private helper method binarySearchHelper
     */
    private int lookUp(K key) {
       return binarySearchHelper(0, list.size() - 1, key);
    }

    /**
     * Private helper method that implements the Binary Search algorithm
     * @param min The minimum index for the search range
     * @param max The maximum index for the search range
     * @param key The key to locate within the Map
     * @return The index at which a newly added key, or one that isn't already in the Map, should be added
     */
    private int binarySearchHelper(int min, int max, K key) {
        if (min > max) {
        	return -1 * (min + 1);
        }
        int mid = (max + min) / 2;
        if (compare(list.get(mid).getKey(), key) == 0) {
        	return mid;
        } else if (compare(list.get(mid).getKey(), key) > 0) {
        	return binarySearchHelper(min, mid - 1, key);	
        } else {
       		return binarySearchHelper(mid + 1, max, key);
       	}
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public V get(K key) {
        int index = lookUp(key);
        if (index < 0) {
        	return null;
        } else {
        	return list.get(index).getValue();
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        for (Entry<K, V> entry : list) {
            set.add(entry);
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        int index = lookUp(key);
        V rtn; 
        if (index < 0) {
        	list.add(-1 * (index + 1), new MapEntry<K, V>(key, value));
        	return null;
        } else {
        	Map.Entry<K, V> holder = list.set(index, new MapEntry<K, V>(key, value));
        	rtn = holder.getValue();
        	return rtn;
        }
        
    }

    @Override
    public V remove(K key) {
        int index = lookUp(key);
        if (index < 0) {
        	return null;
        } else {
        	V rtn = list.remove(index).getValue();
        	return rtn;
        }
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SearchTableMap[");
        Iterator<Entry<K, V>> it = list.iterator();
        while(it.hasNext()) {
            sb.append(it.next().getKey());
            if(it.hasNext()) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
