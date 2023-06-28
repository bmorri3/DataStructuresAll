package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * A skeletal implementation of the Binary Tree abstract data type. This class
 * provides implementation for common methods that can be implemented the same
 * no matter what specific type of concrete data structure is used to implement
 * the binary tree abstract data type.
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the binary tree
 */
public abstract class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    
	/** No children */
	private static final int NO_CHILDREN = 0;
	/** One child */
	private static final int ONE_CHILD = 1;
	/** Two children */
	private static final int TWO_CHILDREN = 2;	

	@Override
    public Iterable<Position<E>> inOrder() {
		PositionCollection traversal = new PositionCollection();		
		
		if(root() == null) {
			return traversal;		
		}
		inOrderHelper(root(), traversal);
		
		return traversal;
    }

    private void inOrderHelper(Position<E> p, PositionCollection traversal) {
    	if(p == null) {
			return;		
		}
    	inOrderHelper(left(p), traversal);
    	traversal.add(p);
    	inOrderHelper(right(p), traversal);
    }
    
    @Override
    public int numChildren(Position<E> p) {
    	AbstractTreeNode<E> node = validate(p);
    	
    	// If neither child is null, return 2
    	if(left(node) != null && right(node) != null)
    		return TWO_CHILDREN;
    	// If only one is not null, return 1 
    	if(left(node) != null || right(node) != null)
    		return ONE_CHILD;
    	// Both are null; return 0
    	return NO_CHILDREN;    		
    }
    
    @Override
    public Position<E> sibling(Position<E> p) {	
    	validate(p);
    	
    	AbstractTreeNode<E> node = null;
    	
    	if(root().equals(p))
    		return node;
    	
    	// If p is the left child of parent, set node to parent's right child
    	if(left(parent(p)) != null && right(parent(p)) != null && left(parent(p)).equals(p))
    		node = validate(right(parent(p)));
    	// If p is the right child of parent, set node to parent's left child
    	if(left(parent(p)) != null && right(parent(p)) != null && right(parent(p)).equals(p))
    		node = validate(left(parent(p)));
    	
    	return node;
    }
    
    @Override
    public Iterable<Position<E>> children(Position<E> p) {
        AbstractTreeNode<E> node = validate(p);
        PositionCollection childrenCollection = new PositionCollection();
        if (left(node) != null) {
            childrenCollection.add(left(node));
        }
        if (right(node) != null) {
            childrenCollection.add(right(node));
        }
        return childrenCollection;
    }
}