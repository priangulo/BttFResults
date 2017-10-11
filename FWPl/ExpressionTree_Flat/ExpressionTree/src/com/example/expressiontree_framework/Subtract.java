package com.example.expressiontree_framework; public

/**
 * @class Subtract
 *
 * @brief Defines a node in the parse tree for the binary subtract
 *        operator non-terminal expression.
 */
class Subtract extends com.example.expressiontree_framework.Operator {
    /** Ctor */
    public Subtract() {
        super(null, null, com.example.expressiontree_framework.Interpreter.addSubPrecedence);
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence =
            com.example.expressiontree_framework.Interpreter.addSubPrecedence + accumulatedPrecedence;
    } public

    /** Method for building a @a Subtract node. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.CompositeSubtractNode(left.build(),
                                         right.build());
    }

    /** Returns the current precedence. */
    public int precedence() {
        return precedence;
    }
}