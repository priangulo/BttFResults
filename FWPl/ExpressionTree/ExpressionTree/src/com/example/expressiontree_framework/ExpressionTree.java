package com.example.expressiontree_framework;

import java.util.Iterator;

/**
 * @class ExpressionTree
 *
 * @brief Interface for the Composite pattern that is used to contain
 *        all the operator and operand nodes in the expression tree.
 *        Plays the role of the "Abstraction" in the Bridge pattern
 *        and delegates to the appropriate "Implementor" that performs
 *        the expression tree operations.
 */
public class ExpressionTree {
    /** Base implementor. */
     public com.example.expressiontree_framework.ComponentNode root = null;

    /** A factory class capable of creating iterators dynamically. */
     public com.example.expressiontree_framework.IteratorFactory treeIteratorFactory
        = new com.example.expressiontree_framework.IteratorFactory();

    /**
     * Ctor that takes a @a Node * that contains all the nodes in the
     * expression tree.
     */
	 
	public ExpressionTree() {} 
	 
    public ExpressionTree(com.example.expressiontree_framework.ComponentNode root) {
        this.root = root;
    }

    /** Returns whether a the tree is null. */
    public boolean isNull() {
        return root == null;
    }

    /** Returns root. */
    public com.example.expressiontree_framework.ComponentNode getRoot() {
        return root;
    }

    /** Returns the root item. */
    public int item() throws Exception {
        return root.item();
    }

    /** Returns the tree's left node. */
    public com.example.expressiontree_framework.ExpressionTree left() {
        return new com.example.expressiontree_framework.ExpressionTree(root.left());
    }

    /** Returns the tree's right node. */
    public com.example.expressiontree_framework.ExpressionTree right() {
        return new com.example.expressiontree_framework.ExpressionTree(root.right());
    }

    /** Accepts a @a visitor. */
    public void accept(com.example.expressiontree_framework.Visitor visitor) {
        root.accept(visitor);
    }

    /** 
     * Returns an @a Iterator that supports the requested
     * traveralOrder.
     */
    public Iterator<com.example.expressiontree_framework.ExpressionTree> makeIterator
        (String traversalOrderRequest) {
        /* 
         * Use the TreeIteratorFactory to create the requested
         * iterator.
         */
        return 
             treeIteratorFactory.makeIterator(this, traversalOrderRequest);
    }
}