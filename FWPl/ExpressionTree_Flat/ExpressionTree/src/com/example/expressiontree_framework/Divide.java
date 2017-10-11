package com.example.expressiontree_framework; public

/**
 * @class Divide
 *
 * @brief Defines a node in the parse tree for the binary divide
 *        operator non-terminal expression.
 */
class Divide extends com.example.expressiontree_framework.Operator {
    /** Ctor */
    public Divide() {
        super(null, null, com.example.expressiontree_framework.Interpreter.mulDivPrecedence);
    }

    /** Returns the current precedence. */
    public int precedence() {
        return precedence;
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence = 
            com.example.expressiontree_framework.Interpreter.mulDivPrecedence + accumulatedPrecedence;
    } public

    /** Method for building a @a Divide node. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.CompositeDivideNode(left.build(),
                                       right.build());
    }
}