package com.example.expressiontree_framework; public

/**
 * @class Negate
 *
 * @brief Defines a node in the parse tree for unary minus
 *        operator non-terminal expression.
 */
class Negate extends com.example.expressiontree_framework.UnaryOperator {
    /** Ctor */
    public Negate() {
        super(null, com.example.expressiontree_framework.Interpreter.negatePrecedence);
    }

    /** Adds precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence =
            com.example.expressiontree_framework.Interpreter.negatePrecedence + accumulatedPrecedence;
    } public

    /** Method for building a @a Negate node. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.CompositeNegateNode(right.build());
    }

    /** Returns the current precedence. */
    public int precedence() {
        return precedence;
    }
}