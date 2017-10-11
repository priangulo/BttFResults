package com.example.expressiontree_framework;

import java.util.Iterator;

/**
 * @class InstrumentedExpressionTree
 * 
 * @brief Interface for the Composite pattern that is used to contain
 *        all the operator and operand nodes in the expression tree.
 *        Plays the role of the "Abstraction" in the Bridge pattern
 *        and delegates to the appropriate "Implementor" that performs
 *        the expression tree operations, after first logging the
 *        start and finish of each call.
 */
public class InstrumentedExpressionTree extends com.example.expressiontree_framework.ExpressionTree { public
    /** A factory class capable of creating iterators dynamically. */
    com.example.expressiontree_framework.IteratorFactory treeIteratorFactory
        = new com.example.expressiontree_framework.IteratorFactory();

    /**
     * Ctor that takes a @a Node * that contains all the nodes in the
     * expression tree.
     */
    public InstrumentedExpressionTree(com.example.expressiontree_framework.ComponentNode root) {
        super(root);
    }

    /** Returns whether a the tree is null. */
    public boolean isNull() {
        System.out.println("starting isNull() call");
        boolean temp = super.isNull();
        System.out.println("finished isNull() call");
        return temp;
    }

    /** Returns root. */
    public com.example.expressiontree_framework.ComponentNode getRoot() {
        System.out.println("starting getRoot() call");
        com.example.expressiontree_framework.ComponentNode temp = root;
        System.out.println("finished getRoot() call");
        return temp;
    }

    /** Returns the root item. */
    public int item() throws Exception {
        System.out.println("starting left() call");
        int temp = super.item();
        System.out.println("finished left() call");
        return temp;
    }

    /** Returns the tree's left node. */
    public com.example.expressiontree_framework.ExpressionTree left() {
        System.out.println("starting left() call");
        com.example.expressiontree_framework.ExpressionTree temp = super.left();
        System.out.println("finished right() call");
        return temp;
    }

    /** Returns the tree's right node. */
    public com.example.expressiontree_framework.ExpressionTree right() {
        System.out.println("starting right() call");
        com.example.expressiontree_framework.ExpressionTree temp = super.right();
        System.out.println("finished right() call");
        return temp;
    }

    /** Accepts a @a visitor. */
    public void accept(com.example.expressiontree_framework.Visitor visitor) {
        System.out.println("starting accept() call");
        super.accept(visitor);
        System.out.println("finished accept() call");
    }

    /** 
     * Returns the designated iterator after requesting it from
     * factory method. 
     */
    public Iterator<com.example.expressiontree_framework.ExpressionTree> makeIterator(String traversalOrder) {
        System.out.println("starting makeIterator() call");
        Iterator<com.example.expressiontree_framework.ExpressionTree> temp =
            
                                             treeIteratorFactory.makeIterator(this, traversalOrder);
        System.out.println("finished makeIterator() call");
        return temp;
    }
}