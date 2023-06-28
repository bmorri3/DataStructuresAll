package edu.ncsu.csc316.dsa.map;

import java.util.Iterator;
import edu.ncsu.csc316.dsa.Position;
import edu.ncsu.csc316.dsa.list.positional.PositionalLinkedList;
import edu.ncsu.csc316.dsa.list.positional.PositionalList;

/**
 * An unordered link-based map is an unordered (meaning keys are not used to
 * order entries) linked-memory representation of the Map abstract data type.
 * This link-based map delegates to an existing doubly-linked positional list.
 * To help self-organizing entries to improve efficiency of lookUps, the
 * unordered link-based map implements the move-to-front heuristic: each time an
 * entry is accessed, it is shifted to the front of the internal list.
 * 
 * @author Dr. King
 * 
 * Further implementation written by
 * @author Richard Bedford
 * 
 * CSC 316 Summer 2022
 * Workshop 5
 *
 * @param <K> the type of keys stored in the map
 * @param <V> the type of values that are associated with keys in the map
 */
public class UnorderedLinkedMap<K, V> extends AbstractMap<K, V> {
	/** Underlying data structure for our Map ADT */
    private PositionalList<Entry<K, V>> list;
    
    /** Constructor for UnorderedLinkedMap */
    public UnorderedLinkedMap() {
        this.list = new PositionalLinkedList<Entry<K, V>>();
    }
    /**
     * Helper method that searches the list to determine if the passed key
     * exists within the list. 
     * @param key The unique identifier used to search the list
     * @return If the key exists, a Position is returned. If the key does not exist, null is returned
     */
    private Position<Entry<K, V>> lookUp(K key) { 	    	
    	
    	Iterator<Position<Entry<K, V>>> it = list.positions().iterator();
    	Position<Entry<K, V>> rtn;
    	while (it.hasNext()) {
    		rtn = it.next();
    		if (rtn.getElement().getKey().equals(key)) {
    			return rtn;
    		}
    	}
    	return null;
    }

    @Override
    public V get(K key) {
    	Position<Entry<K, V>> p = lookUp(key);
    	if (p == null) {
        	return null;
        } else {
        	V rtn = p.getElement().getValue();
        	moveToFront(p);
        	return rtn;
        }
    }
    
    /**
     * Helper method that removes a Position from a list and places it at the front 
     * of the list. Implements move-to-front heuristic. 
     * @param position Position to be moved to the front.
     */
    private void moveToFront(Position<Entry<K, V>> position) {
        list.addFirst(list.remove(position));
    }

    @Override
    public V put(K key, V value) {
    	Position<Entry<K, V>> p = lookUp(key);
    	MapEntry<K, V> replace = new MapEntry<K, V>(key, value);
    	if (p == null) {
    		list.addFirst(replace);
    		return null;
    	} else {
    		V rtn = list.remove(p).getValue();
    		list.addFirst(replace);
    		return rtn;
    	}
    }
    
    @Override
    public V remove(K key) {
    	Position<Entry<K, V>> p = lookUp(key);
    	if (p == null) {
    		return null;
    	}
    	V rtn = list.remove(p).getValue();
    	return rtn;
    }
    
    @Override
    public int size() {
        return list.size();
    }
    
    
    @Override
    public Iterable<Entry<K, V>> entrySet() {
    	  EntryCollection collection = new EntryCollection();
          for(Entry<K, V> entry : list) {
              collection.add(entry);
          }
          return collection;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("UnorderedLinkedMap[");
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
