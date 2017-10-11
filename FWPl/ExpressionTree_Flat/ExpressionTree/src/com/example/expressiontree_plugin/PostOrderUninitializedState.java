package com.example.expressiontree_plugin;

/**
 * A state formated post order without an expression tree. 
 */
public class PostOrderUninitializedState extends com.example.expressiontree_plugin.UninitializedState {
    /**Ctor*/
    public PostOrderUninitializedState() {
    }

    /** 
     * Process the @a expression using a post-order
     * interpreter and update the state of the @a context to
     * the @a PostOrderInitializedState.
     */
    void makeTree(TreeOps context, String expression) {
        throw new IllegalStateException("PostOrderUninitializedState.makeTree() not yet implemented");
    }
}