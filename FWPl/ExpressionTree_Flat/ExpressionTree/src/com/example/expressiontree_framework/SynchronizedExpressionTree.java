package com.example.expressiontree_framework;

import java.util.Iterator;

/**
 * @class SynchronizedExpressionTree
 * 
 * @brief Interface for the Composite pattern that is used to contain
 *        all the operator and operand nodes in the expression tree.
 *        Plays the role of the "Abstraction" in the Bridge pattern
 *        and delegates to the appropriate "Implementor" that performs
 *        the expression tree operations, after first synchronizing
 *        each call.
 */
public class SynchronizedExpressionTree extends com.example.expressiontree_framework.ExpressionTree { public
    /** A factory class capable of creating iterators dynamically. */
    com.example.expressiontree_framework.IteratorFactory treeIteratorFactory
        = new com.example.expressiontree_framework.IteratorFactory();

    /**
     * Ctor that takes a @a Node * that contains all the nodes in the
     * expression tree.
     */
    public SynchronizedExpressionTree(com.example.expressiontree_framework.ComponentNode root) {
        super(root);
    }

    /** Returns whether a the tree is null. */
    public boolean isNull() {
        boolean temp;
        synchronized(this) {
            temp = super.isNull();
        }
        return temp;
    }

    /** Returns root. */
    public com.example.expressiontree_framework.ComponentNode getRoot() {
        com.example.expressiontree_framework.ComponentNode temp;
        synchronized(this) {
            temp = root;
        }
        return temp;
    }

    /** Returns the root item. */
    public int item() throws Exception {
        int temp;
        synchronized(this) {
            temp = super.item();
        }
        return temp;
    }

    /** Returns the tree's left node. */
    public com.example.expressiontree_framework.ExpressionTree left() {
        com.example.expressiontree_framework.ExpressionTree temp;
        synchronized(this) {
            temp = super.left();
        }
        return temp;
    }

    /** Returns the tree's right node. */
    public com.example.expressiontree_framework.ExpressionTree right() {
        com.example.expressiontree_framework.ExpressionTree temp;
        synchronized(this) {
            temp = super.right();
        }
        return temp;
    }

    /** Accepts a @a visitor. */
    public void accept(com.example.expressiontree_framework.Visitor visitor) {
        synchronized(this) {
            super.accept(visitor);
        }
    }

    /** 
     * Returns the designated iterator after requesting it from
     * factory method. 
     */
    public Iterator<com.example.expressiontree_framework.ExpressionTree> makeIterator(String traversalOrder) {
        Iterator<com.example.expressiontree_framework.ExpressionTree> temp;
        synchronized(this) {
            
                                                    temp = treeIteratorFactory.makeIterator(this, traversalOrder);
        }
        return temp;
    }
}