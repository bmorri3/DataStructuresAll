package edu.ncsu.csc316.dsa.tree;

import edu.ncsu.csc316.dsa.Position;

/**
 * The LinkedBinaryTree is implemented as a linked data structure to support
 * efficient Binary Tree abstract data type behaviors.
 * 
 * Size is maintained as a global field to ensure O(1) worst-case runtime of
 * size() and isEmpty().
 * 
 * The LinkedBinaryTree class is based on the implementation developed for use
 * with the textbook:
 *
 * Data Structures and Algorithms in Java, Sixth Edition Michael T. Goodrich,
 * Roberto Tamassia, and Michael H. Goldwasser John Wiley and Sons, 2014
 * 
 * @author Dr. King
 * @author Ben Morris
 *
 * @param <E> the type of elements stored in the binary tree
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

	/** Root field */
    private BinaryTreeNode<E> root;
    /** Size field */
    private int size;

    /**
     * Create a new empty binary tree
     */
    public LinkedBinaryTree() {
        root = null;
        size = 0;
    }
    
    /**
     * Safely casts a Position, p, to be a BinaryTreeNode.
     * 
     * @param p the position to cast to a BinaryTreeNode
     * @return a reference to the BinaryTreeNode
     * @throws IllegalArgumentException if p is null, or if p is not a valid
     *                                  BinaryTreeNode
     */
    protected BinaryTreeNode<E> validate(Position<E> p) {
        if (!(p instanceof BinaryTreeNode)) {
            throw new IllegalArgumentException("Position is not a valid linked binary tree node");
        }
        return (BinaryTreeNode<E>) p;
    }

    /**
     * A BinaryTreeNode stores an element and references the node's parent, left
     * child, and right child
     * 
     * @author Dr. King
     * @author Ben Morris
     *
     * @param <E> the type of element stored in the node
     */
    public static class BinaryTreeNode<E> extends AbstractTreeNode<E> {
        /** parent field */
    	private BinaryTreeNode<E> parent;
    	/** left child field */
        private BinaryTreeNode<E> left;
        /** right child field */
        private BinaryTreeNode<E> right;

        /**
         * Constructs a new BinaryTreeNode with the provided element
         * 
         * @param element the element to store in the node
         */
        public BinaryTreeNode(E element) {
            this(element, null);
        }

        /**
         * Constructs a new BinaryTreeNode with the provided element and provided parent
         * reference
         * 
         * @param element the element to store in the node
         * @param parent  the parent of the newly created node
         */
        public BinaryTreeNode(E element, BinaryTreeNode<E> parent) {
            super(element);
            setParent(parent);
        }

        /**
         * Returns the left child of the current node
         * 
         * @return the left child of the current node
         */
        public BinaryTreeNode<E> getLeft() {
            return left;
        }

        /**
         * Returns the right child of the current node
         * 
         * @return the right child of the current node
         */
        public BinaryTreeNode<E> getRight() {
            return right;
        }

        /**
         * Sets the left child of the current node
         * 
         * @param left the node to set as the left child of the current node
         */
        public void setLeft(BinaryTreeNode<E> left) {
            this.left = left;
        }

        /**
         * Sets the right child of the current node
         * 
         * @param right the node to set as the right child of the current node
         */
        public void setRight(BinaryTreeNode<E> right) {
            this.right = right;
        }

        /**
         * Returns the parent of the current node
         * 
         * @return the parent of the current node
         */
        public BinaryTreeNode<E> getParent() {
            return parent;
        }

        /**
         * Sets the parent of the current node
         * 
         * @param parent the node to set as the parent of the current node
         */
        public void setParent(BinaryTreeNode<E> parent) {
            this.parent = parent;
        }
    }

    @Override
    public Position<E> left(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getLeft();
    }

    @Override
    public Position<E> right(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getRight();
    }

    @Override
    public Position<E> addLeft(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (left(node) != null) {
            throw new IllegalArgumentException("Node already has a left child.");
        }
        
        size++;
        node.left = createNode(value, node, null, null); 
        return node.left;
    }

    @Override
    public Position<E> addRight(Position<E> p, E value) {
        BinaryTreeNode<E> node = validate(p);
        if (right(node) != null) {
            throw new IllegalArgumentException("Node already has a right child.");
        }
        
        size++;
        node.right = createNode(value, node, null, null); 
        return node.right;
    }

    @Override
    public Position<E> root() {
        return root;
    }

    @Override
    public Position<E> parent(Position<E> p) {
        BinaryTreeNode<E> node = validate(p);
        return node.getParent();
    }

    @Override
    public Position<E> addRoot(E value) {
        if (root() != null) {
            throw new IllegalArgumentException("The tree already has a root.");
        }
        this.root = createNode(value, null, null, null);
        size++;
        return root;
    }

    @Override
    public E remove(Position<E> p) {
    	        
    	BinaryTreeNode<E> node = null;
    	
    	node = validate(p);
    	
    	// If the node has two children
    	if (numChildren(p) == 2) {
            throw new IllegalArgumentException("The node has two children");
        }
        
        // If p is the root
        if(isRoot(p)) {
        	// If p has a left child
            if(left(p) != null) {
            	// left(p) will be the root
            	node.getLeft().setParent(null);
            	// Set the tree's root to p's left child
            	setRoot(node.getLeft());
    	        size--;
    	        return p.getElement();
            }
            
            // If p has a right child
            if(right(p) != null) {
            	// right(p) will be the root
    	        node.getRight().setParent(null);
    	        // Set the tree's root to p's right child
    	        setRoot(node.getRight());
    	        size--;
    	        return p.getElement();
            }
         	// P has no children
    	    else {
    	    	size--;
    	    	root = null;
    	    	return p.getElement();
            }
        }
        
        // If p has a left child
        if(left(p) != null) {
	        // If p is parent's left link, set the parent's left link to p's left
	        if(left(parent(p)) != null && left(parent(p)).equals(p))
	        	node.getParent().setLeft(node.getLeft());
	        // If p is parent's right link, set the parent's right link to p's left
	        if(right(parent(p)) != null && right(parent(p)).equals(p))
	        	node.getParent().setRight(node.getLeft());
	        // Set p's child to link to p's parent
	        node.getLeft().setParent(node.getParent()); 
	        size--;
	        return p.getElement();
        }
        
        // If p has a right child
        if(right(p) != null) {
        	// If p is parent's left link, set the parent's left link to p's right
	        if(left(parent(p)) != null && left(parent(p)).equals(p))
	        	node.getParent().setLeft(node.getRight());
	        // If p is parent's right link, set the parent's right link to p's right
	        else if(right(parent(p)) != null && right(parent(p)).equals(p))
	        	node.getParent().setRight(node.getRight());
	        // Set p's child to link to p's parent
	        node.getRight().setParent(node.getParent()); 
	        size--;
	        return p.getElement();
        }
     	// P has no children
	    else {
	    	// If p is parent's left link, set the parent's left link to null
	        if(left(parent(p)) != null && left(parent(p)).equals(p))
	        	node.getParent().setLeft(null);
	        // If p is parent's right link, set the parent's right link to null
	        if(right(parent(p)) != null && right(parent(p)).equals(p))
	        	node.getParent().setRight(null);
	        size--;
	        return p.getElement();
        }
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Creates new node
     * @param e element to set
     * @param parent parent of new node
     * @param left left child of new node
     * @param right right child of new node
     * @return new node
     */
    protected BinaryTreeNode<E> createNode(E e, BinaryTreeNode<E> parent, BinaryTreeNode<E> left,
            BinaryTreeNode<E> right) {
        BinaryTreeNode<E> newNode = new BinaryTreeNode<E>(e);
        newNode.setParent(parent);
        newNode.setLeft(left);
        newNode.setRight(right);
        return newNode;
    }

    // setRoot is needed for a later lab...
    // ...but THIS DESIGN IS BAD! If a client arbitrarily changes
    // the root by using the method, the size may no longer be correct/valid.
    // Instead, the precondition for this method is that
    // it should *ONLY* be used when rotating nodes in 
    // balanced binary search trees. We could instead change
    // our rotation code to not need this setRoot method, but that
    // makes the rotation code messier. For the purpose of this lab,
    // we will sacrifice a stronger design for cleaner/less code.
    protected Position<E> setRoot(Position<E> p) {
        root = validate(p);
        return root;
    }
}