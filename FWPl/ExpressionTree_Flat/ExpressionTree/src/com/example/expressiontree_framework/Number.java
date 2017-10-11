package com.example.expressiontree_framework; public

/**
 * @class Symbol
 *
 * @brief Defines a node in the parse tree for number terminal
 *        expressions.
 */
class Number extends com.example.expressiontree_framework.Symbol {
    /** Value of Number. */
    public int item;

    /** Ctor */
    public Number(String input) {
        super(null, null, com.example.expressiontree_framework.Interpreter.numberPrecedence);
        item = Integer.parseInt(input);
    }

    /** Ctor */
    public Number(int input) {
        super(null, null, com.example.expressiontree_framework.Interpreter.numberPrecedence);
        item = input;
    }

    /** 
     * Adds numberPrecedence to the current accumulatedPrecedence
     * value.
     */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence = 
            com.example.expressiontree_framework.Interpreter.numberPrecedence + accumulatedPrecedence;
    }

    /** 
     * Method for returning precedence level (higher value means
     * higher precedence).
     */
    public int precedence() {
        return precedence;
    } public

    /** Builds a @a LeadNode. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.LeafNode(item);
    }
}