package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;

import edu.ncsu.csc316.dsa.list.List;
import edu.ncsu.csc316.dsa.list.SinglyLinkedList;

/**
 * A skeletal implementation of the Map abstract data type. This class provides
 * implementation for common methods that can be implemented the same no matter
 * what specific type of concrete data structure is used to implement the map
 * abstract data type.
 * 
 * @author Dr. King
 * 
 * Further implementation of moveToFront, lookUp, put, get, and remove were done by
 * @author Richard Bedford
 * 
 * CSC 316, Summer 2022
 * Workshop 5
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public abstract class AbstractMap<K, V> implements Map<K, V> {

    /**
     * MapEntry implements the Entry abstract data type.
     * 
     * @author Dr. King
     *
     * @param <K> the type of key stored in the entry
     * @param <V> the type of value stored in the entry
     */
    protected static class MapEntry<K, V> implements Entry<K, V> {
    	/** Unique identifier used to look for an element in a map */
        private K key;
        /** Value associated with the key */
        private V value;

        /**
         * Constructs a MapEntry with a provided key and a provided value
         * 
         * @param key   the key to store in the entry
         * @param value the value to store in the entry
         */
        public MapEntry(K key, V value) {
            setKey(key);
            setValue(value);
        }

        @Override
        public K getKey() {
            return key;
        }

        @Override
        public V getValue() {
            return value;
        }

        /**
         * Set the key of the entry to the provided key
         * 
         * @param key the key to store in the entry
         */
        private void setKey(K key) {
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
        
        @SuppressWarnings("unchecked")
        @Override
        public int compareTo(Entry<K, V> o) {
            return ((Comparable<K>)this.key).compareTo(o.getKey());
        }       
    }
    
    /**
     * EntryCollection implements the {@link Iterable} interface to allow traversing
     * through the entries stored in the map. EntryCollection does not allow removal
     * operations
     * 
     * @author Dr. King
     *
     */
    protected class EntryCollection implements Iterable<Entry<K, V>> {
    	/** Underlying data structure */
    	private List<Entry<K, V>> list;

    	/**
    	 * Constructor for an EntryCollection
    	 */
        public EntryCollection() {
            list = new SinglyLinkedList<Entry<K, V>>();
        }

        /**
         * Adds a new entry to the underlying list
         * @param entry Entry to be added to our map
         */
        public void add(Entry<K, V> entry) {
            list.addLast(entry);
        }

        /**
         * An iterator to iterate over the Map collection
         * @return An Iterator object
         */
        public Iterator<Entry<K, V>> iterator() {
            return new EntryCollectionIterator();
        }
        
        /**
         * Inner class that returns an iterator for EntryCollection
         * Cite: Code provided by teaching staff per Workshop instructions
         * @author Dr. King 
         *
         */
        private class EntryCollectionIterator implements Iterator<Entry<K, V>> {
        	/**Instance of an iterator */
            private Iterator<Entry<K, V>> it;
            
            /**
             * Constructor of an EntryCollectionIterator
             */
            public EntryCollectionIterator() {
                it = list.iterator();
            }

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Entry<K, V> next() {
                return it.next();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("The remove operation is not supported yet.");
            }
        } 
    }       

    /**
     * KeyIterator implements the {@link Iterator} interface to allow traversing
     * through the keys stored in the map
     * 
     * @author Dr. King
     *
     */
    protected class KeyIterator implements Iterator<K> {
    	/** Instance of an iterator for delegation to our PositionalLinkedList */
    	private Iterator<Entry<K, V>> it;
    	/**
    	 * Constructor for an iterator to iterate over and return keys
    	 */
    	public KeyIterator() {
    		it = entrySet().iterator();
    	}
    	
    	@Override
    	public boolean hasNext() {
    		return it.hasNext();
    	}
    	
    	@Override
    	public K next() {
    		return it.next().getKey();
    	}
    	
    	@Override
    	public void remove() {
    		throw new UnsupportedOperationException("The remove operation is not supported yet.");
    	}
    }

    /**
     * ValueIterator implements the {@link Iterator} interface to allow traversing
     * through the values stored in the map
     * 
     * @author Dr. King
     * 
     * Further implementation done by
     * @author Richard Bedford
     * 
     * CSC 316, Summer 2022
     * Workshop 5
     *
     */
    protected class ValueIterator implements Iterator<V> {
        /** Instance of an iterator for delegation to our PositionalLinkedList */
    	private Iterator<Entry<K, V>> it;
    	/**
    	 * Constructor for an iterator that will iterate and return values from an 
    	 * Entry within out map
    	 */
    	public ValueIterator() {
    		it = entrySet().iterator();
    	}
    	
    	@Override
    	public boolean hasNext() {
    		return it.hasNext();
    	}
    	
    	@Override
    	public V next() {
    		return it.next().getValue();
    	}
    	
    	@Override
    	public void remove() {
    		throw new UnsupportedOperationException("The remove operation is not supported yet.");
    	}
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public Iterator<K> iterator() {
        return new KeyIterator();
    }

    @Override
    public Iterable<V> values() {
        return new ValueIterable();
    }

    private class ValueIterable implements Iterable<V> {
    	@Override
        public Iterator<V> iterator() {
            return new ValueIterator();
        }
    }

}
