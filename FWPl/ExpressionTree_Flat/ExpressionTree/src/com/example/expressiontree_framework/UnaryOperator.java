package com.example.expressiontree_framework;

/**
 * @class UnaryOperator
 *
 * @brief Defines a node in the parse tree for unary operator
 *        non-terminal expressions.
 */
public abstract class UnaryOperator extends com.example.expressiontree_framework.Symbol { public
    /** Ctor */
    UnaryOperator(com.example.expressiontree_framework.Symbol right,
                  int precedence) {
        super(null, right, precedence);
    }

    /** Abstract method for building a @a UnaryOperator node. */
    abstract public com.example.expressiontree_framework.ComponentNode build();
}