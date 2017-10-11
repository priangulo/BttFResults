package com.example.expressiontree_framework; public

/**
 * @class Add
 *
 * @brief Defines a node in the parse tree for the binary add
 *        operator non-terminal expression.
 */
class Add extends com.example.expressiontree_framework.Operator {
    /** Ctor */
    public Add() {
        super(null, null, com.example.expressiontree_framework.Interpreter.addSubPrecedence);
    }

    /** Adds Precedence to its current value. */
    public int addPrecedence(int accumulatedPrecedence) {
        return precedence =
            com.example.expressiontree_framework.Interpreter.addSubPrecedence + accumulatedPrecedence;
    } public

    /** Method for building an @a Add node. */
    com.example.expressiontree_framework.ComponentNode build() {
        return new com.example.expressiontree_framework.CompositeAddNode(left.build(),
                                    right.build());
    }

    /** Returns the current precedence. */
    public int precedence() {
        return precedence;
    }
}