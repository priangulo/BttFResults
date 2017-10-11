package com.example.expressiontree_framework; public

/**
 * @class Multiply
 *
 * @brief Defines a node in the parse tree for the binary multiply
 *        operator non-terminal expression.
 */
class Multiply extends com.example.expressiontree_framework.Operator {
    /** Ctor */
    public Multiply() {
        super(null, null, com.example.expressiontree_framework.Interpreter.mulDivPrecedence);
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence =
            com.example.expressiontree_framework.Interpreter.mulDivPrecedence + accumulatedPrecedence;
    } public

    /** Method for building a @a Multiple node. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.CompositeMultiplyNode(left.build(),
                                         right.build());
    }

    /** Returns the precedence. */
    public int precedence() {
        return precedence;
    }
}