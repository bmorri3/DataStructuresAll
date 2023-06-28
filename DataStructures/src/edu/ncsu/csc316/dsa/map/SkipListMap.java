package edu.ncsu.csc316.dsa.map;

import java.util.Comparator;
import java.util.Random;

/**
 * A SkipListMap is an ordered (meaning entries are stored in a sorted order
 * based on the keys of the entries) linked-memory representation of the Map
 * abstract data type. This link-based map maintains several levels of linked
 * lists to help approximate the performance of binary search using a
 * linked-memory structure. SkipListMap ensures a O(logn) expected/average
 * runtime for lookUps, insertions, and deletions.
 *
 * The SkipListMap class is based on algorithms developed for
 * use with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
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
public class SkipListMap<K extends Comparable<K>, V> extends AbstractOrderedMap<K, V> {

    /**
     * Coin tosses are used when inserting entries into the data structure to ensure
     * 50/50 probability that an entry will be added to the current level of the
     * skip list structure
     */
    private Random coinToss;

    /**
     * Start references the topmost, leftmost corner of the skip list. In other
     * words, start references the sentinel front node at the top level of the skip
     * list
     */
    private SkipListNode<K, V> start;

    /**
     * The number of entries stored in the map
     */
    private int size;

    /**
     * The number of levels of the skip list data structure
     */
    private int height;

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on
     * their natural ordering based on {@link Comparable#compareTo}
     */
    public SkipListMap() {
        this(null);
    }

    /**
     * Constructs a new SkipListMap where keys of entries are compared based on a
     * provided {@link Comparator}
     *
     * @param compare a Comparator that defines comparisons rules for keys in the
     *                map
     */
    public SkipListMap(Comparator<K> compare) {
        super(compare);
        coinToss = new Random();
        // Create a dummy head node for the left "-INFINITY" sentinel tower
        start = new SkipListNode<K, V>(null);
        // Create a dummy tail node for the right "+INFINITY" sentinel tower
        start.setNext(new SkipListNode<K, V>(null));
        // Set the +INFINITY tower's previous to be the "start" node
        start.getNext().setPrevious(start);
        size = 0;
        height = 0;
    }

    /**
     * Helper method to determine if an entry is one of the sentinel. This was provided by Dr. King in
     * the workshop instructions
     * -INFINITY or +INFINITY nodes (containing a null key)
     * @param node SkipListNode to test
     * @return true or false is the node is equal to null
     */
    private boolean isSentinel(SkipListNode<K, V> node) {
        return node.getEntry() == null;
    }

    /**
     * Search helper method that checks whether or not the entry is present in the SkipListMap
     * @param key Part of the Entry that is used for comparison against other keys from existing entries
     * @return SkipListNode with the sought after entry, or SkipListNode with a null entry
     */
    private SkipListNode<K, V> lookUp(K key) {
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
            while (!isSentinel(current.next) && compare(key, current.next.getEntry().getKey()) >= 0) {
                current = current.next;
            }
        }
        return current;
    }

    @Override
    public V get(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        try {
        	if (compare(temp.getEntry().getKey(), key) == 0) {
        		return temp.getEntry().getValue();
        	}
        } catch (Exception e) {
        	//do nothing
        } 
        return null;
    }

    /**
     * Private helper method that inserts a copy of the new entry up n number of levels, depending on a coin toss
     * @param prev Node from the newly added entry found behind
     * @param down Node from the newly added entry found below
     * @param entry Entry value containing the key and value
     * @return Copy of the node that was newly put into the Map
     */
    private SkipListNode<K, V> insertAfterAbove(SkipListNode<K, V> prev, SkipListNode<K, V> down, Entry<K, V> entry) {
        SkipListNode<K, V> newNode = new SkipListNode<K, V>(entry);
        newNode.setBelow(down);
        newNode.setPrevious(prev);
        if (prev != null) {
        	newNode.setNext(prev.getNext());
        	newNode.getPrevious().setNext(newNode);
        }
        if (newNode.getNext() != null) {
        	newNode.getNext().setPrevious(newNode);
        }
        if (down != null) {
        	down.setAbove(newNode);
        }
        return newNode;
    }

    @Override
    public V put(K key, V value) {
        SkipListNode<K, V> temp = lookUp(key);
        V originalValue = null;
        if (temp.getEntry() != null && compare(temp.getEntry().getKey(), key) == 0) {
       		originalValue = temp.getEntry().getValue();
        	while (temp != null) {
        			((MapEntry<K, V>) temp.getEntry()).setValue(value);
        			temp = temp.getAbove();
        		}
        	return originalValue;
        	
        }
        SkipListNode<K, V> q = null;
        int currentLevel = -1;
        do {
        	currentLevel++;
        	if (currentLevel >= height) {
        		height++;
        		SkipListNode<K, V> tail = start.getNext();
        		start = insertAfterAbove(null, start, null);
        		insertAfterAbove(start, tail, null);
        	}
        q = insertAfterAbove(temp, q, new MapEntry<K, V>(key, value));
        while (temp.getAbove() == null) {
        	temp = temp.getPrevious();
        }
        temp = temp.getAbove();
        } while (!coinToss.nextBoolean());
        size++;
        return null;
    }

    @Override
    public V remove(K key) {
        SkipListNode<K, V> temp = lookUp(key);
        if (temp.getEntry() == null || compare(temp.getEntry().getKey(), key) != 0) {
        	return null;
        } else {
        	V rtn = temp.getEntry().getValue();
        	while (temp != null) {
        		temp.prev.setNext(temp.getNext());
        		temp.next.setPrevious(temp.getPrevious());
        		temp = temp.getAbove();
        	}
        	size--;
        	return rtn;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        EntryCollection set = new EntryCollection();
        SkipListNode<K, V> current = start;
        while (current.below != null) {
            current = current.below;
        }
        current = current.next;
        while (!isSentinel(current)) {
            set.add(current.getEntry());
            current = current.next;
        }
        return set;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("SkipListMap[");
        SkipListNode<K, V> cursor = start;
        while (cursor.below != null) {
            cursor = cursor.below;
        }
        cursor = cursor.next;
        while (cursor != null && !isSentinel(cursor) && cursor.getEntry().getKey() != null) {
            sb.append(cursor.getEntry().getKey());
            if (!isSentinel(cursor.next)) {
                sb.append(", ");
            }
            cursor = cursor.next;
        }
        sb.append("]");

        return sb.toString();
    }

    // This method may be useful for testing or debugging.
    // You may comment-out this method instead of testing it, since
    // the full string will depend on the series of random coin flips
    // and will not have deterministic expected results.
//    public String toFullString() {
//        StringBuilder sb = new StringBuilder("SkipListMap[\n");
//        SkipListNode<K, V> cursor = start;
//        SkipListNode<K, V> firstInList = start;
//        while (cursor != null) {
//            firstInList = cursor;
//            sb.append("-INF -> ");
//            cursor = cursor.next;
//            while (cursor != null && !isSentinel(cursor)) {
//                sb.append(cursor.getEntry().getKey() + " -> ");
//                cursor = cursor.next;
//            }
//            sb.append("+INF\n");
//            cursor = firstInList.below;
//        }
//        sb.append("]");
//        return sb.toString();
//    }
   /**
    * Inner class that creates a collections of nodes as a single unit for a SkipListMap element. Provided by
    * @author Dr. King
    *
    *
    * @param <K> Key for the Entry instance variable
    * @param <V> Value for the Value instance variable
    */
    private static class SkipListNode<K, V> {
    	/**Entry for key and value */
        private Entry<K, V> entry;
        /**Node for level above current level*/
        private SkipListNode<K, V> above;
        /**Node for level below current level*/
        private SkipListNode<K, V> below;
        /**Node for level behind current node*/
        private SkipListNode<K, V> prev;
        /**Node for level in front of current node*/
        private SkipListNode<K, V> next;

        /**
         * Constructor for a SkipListNode and sets all the contained nodes
         * @param entry Entry that contains key and value. 
         */
        public SkipListNode(Entry<K, V> entry) {
            setEntry(entry);
            setAbove(null);
            setBelow(null);
            setPrevious(null);
            setNext(null);
        }
        
        /**
         * Retrieves the node above the current node
         * @return node located on the next level, if it exists, null otherwise
         */
        public SkipListNode<K, V> getAbove() {
            return above;
        }

        /**
         * Retrieves the Entry for the SkipListNode
         * @return The Entry contained within the SkipListNode 
         */
        public Entry<K, V> getEntry() {
            return entry;
        }

        /**
         * Retrieves the node in front of the current node
         * @return node located in front of the current node, if it exists, null otherwise
         */
        public SkipListNode<K, V> getNext() {
            return next;
        }

        /**
         * Retrieves the node behind the current node
         * @return node located behind the current node, if it exists, null otherwise
         */
        public SkipListNode<K, V> getPrevious() {
            return prev;
        }

        /**
         * Sets the node above the current node
         * @param up The node above
         */
        public void setAbove(SkipListNode<K, V> up) {
            this.above = up;
        }

        /**
         * Sets the node below the current node
         * @param down The node below
         */
        public void setBelow(SkipListNode<K, V> down) {
            this.below = down;
        }

        /**
         * Sets the Entry for the current node
         * @param entry The entry for the node
         */
        public void setEntry(Entry<K, V> entry) {
            this.entry = entry;
        }

        /**
         * Sets the node in front of the current node
         * @param next The node in front
         */
        public void setNext(SkipListNode<K, V> next) {
            this.next = next;
        }

        /**
         * Retrieves the node behind the current node
         * @param prev The node from behind
         */
        public void setPrevious(SkipListNode<K, V> prev) {
            this.prev = prev;
        }
    }
}
