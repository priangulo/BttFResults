package com.example.expressiontree_framework;

/**
 * @class CompositeBinaryNode
 *
 * @brief Defines a left and right node (via inheritance).  It plays
 *        the role of a "Composite" in the Composite pattern.
 */
public class CompositeBinaryNode extends com.example.expressiontree_framework.CompositeUnaryNode {
    /** Reference to the left child. */
     public com.example.expressiontree_framework.ComponentNode left;
  
	
    /** Ctor */
    public CompositeBinaryNode(com.example.expressiontree_framework.ComponentNode left,
                               com.example.expressiontree_framework.ComponentNode right) {
        super(right);
        this.left = left;
    }
    
    public CompositeBinaryNode() {
    	super(null);
    }

    /** Return the left child. */
    public com.example.expressiontree_framework.ComponentNode left() {
	return left;
    }
}