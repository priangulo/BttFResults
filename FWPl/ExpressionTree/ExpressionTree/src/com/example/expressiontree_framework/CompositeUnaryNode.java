package com.example.expressiontree_framework;

/**
 * @class CompositeUnaryNode
 *
 * @brief Defines a right child (but not a left one) and thus is
 *        useful for unary operations.  It plays the role of a
 *        "Composite" in the Composite pattern.
 */
public class CompositeUnaryNode extends com.example.expressiontree_framework.ComponentNode {
    /** Reference to the right child. */
     public com.example.expressiontree_framework.ComponentNode right;
	
	public CompositeUnaryNode(){}
	
    /** Ctor */
    public CompositeUnaryNode(com.example.expressiontree_framework.ComponentNode right) {
        this.right = right;
 
    }
	
    /** Return the right child. */
    public com.example.expressiontree_framework.ComponentNode right() {
        return this.right;
    }
}