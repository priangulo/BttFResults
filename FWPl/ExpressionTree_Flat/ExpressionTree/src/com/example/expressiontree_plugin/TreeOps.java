package com.example.expressiontree_plugin;

import com.example.expressiontree_plugin.UninitializedState;

/**
 * @class TreeOps
 *
 * @brief Plays the role of the "Context" in the State pattern that
 *        ensures user operations on an expression tree are invoked
 *        according to the correct protocol.  Most of its methods
 *        delegate to the corresponding methods in sublcasses of
 *        the @a State base class, which then perform the requested
 *        operations.
 */
public class TreeOps extends com.example.expressiontree_framework.TreeOps {

    /** Ctor */
    public TreeOps() {
        state = new UninitializedState();
        formatted = false;
        interpreter = new com.example.expressiontree_framework.Interpreter();
        tree = new com.example.expressiontree_framework.ExpressionTree(null);
    }

    /**
     * Set the current @a State to the designated @a
     * newstate pointer.
     */
    void state(com.example.expressiontree_framework.State newState) {
        this.state = newState;
    }
}