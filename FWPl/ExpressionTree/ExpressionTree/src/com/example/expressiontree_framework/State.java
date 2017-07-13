package com.example.expressiontree_framework;

public abstract class State { public

    /**
     * Throws an exception if called in the wrong state.
     */
    void format(TreeOps context, String newFormat) {
        throw new IllegalStateException("State.format() called in invalid state");
    } public
	  
    /** 
     * Throws an exception if called in the wrong state.
     */
    void makeTree(TreeOps context,
                  String expression) {
        throw new IllegalStateException("State.makeTree() called in invalid state");
    } public
	  
    /** 
     * Throws an exception if called in the wrong state.
     */
    void print(TreeOps context,
               String format) {
        throw new IllegalStateException("State.print() called in invalid state");
    } public
	  
    /** 
     * Throws an exception if called in the wrong state.
     */
    void evaluate(TreeOps context,
                  String format) {
        throw new IllegalStateException("State.evaluate() called in invalid state");
    }
}