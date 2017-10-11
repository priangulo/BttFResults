package com.example.expressiontree_plugin;

/**
 * A state formated level order without an expression tree. 
 */
public class LevelOrderUninitializedState extends com.example.expressiontree_plugin.UninitializedState {
    public LevelOrderUninitializedState() {
    }

    /**
     * Process the @a expression using a level-order
     * interpreter and update the state of the @a context to
     * the @a LevelOrderInitializedState.
     */
    void makeTree(TreeOps context, String expression) {
        throw new IllegalStateException("LevelOrderUninitializedState.makeTree() not yet implemented");
    }
}