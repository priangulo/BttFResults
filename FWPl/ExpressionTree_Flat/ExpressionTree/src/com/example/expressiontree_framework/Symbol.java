package com.example.expressiontree_framework;

/**
 * @class Symbol
 *
 * @brief Base class for the various parse tree subclasses.
 */
abstract public class Symbol {
    /** 
     * The following static consts set the precedence levels of
     * the various operations and operands.
     */

    /** Default precedence. */
    protected int precedence = 0;

    /** Left symbol. */
    protected com.example.expressiontree_framework.Symbol left;

    /** Right symbol. */
    protected com.example.expressiontree_framework.Symbol right;

    /** Ctor */
    public Symbol(com.example.expressiontree_framework.Symbol left,
                  com.example.expressiontree_framework.Symbol right,
                  int precedence) {
        this.precedence = precedence;
        this.left = left;
        this.right = right;
    }

    /** 
     * Method for returning precedence level (higher value means
     * higher precedence.
     */
    public int precedence() {
        return precedence;
    }

    /** Abstract method for adding precedence. */
    public abstract int addPrecedence(int accumulatedPrecedence);

    /** Abstract method for building a @a ComponentNode. */
    abstract public com.example.expressiontree_framework.ComponentNode build();
}