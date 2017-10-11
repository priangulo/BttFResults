package com.example.expressiontree_framework;

/**
 * @class Operator
 *
 * @brief Defines a base class in the parse tree for operator
 *        non-terminal expressions.
 */
public abstract class Operator extends com.example.expressiontree_framework.Symbol { public
    /** Ctor */
    Operator(com.example.expressiontree_framework.Symbol left,
             com.example.expressiontree_framework.Symbol right,
             int precedence) {
        super(left, right, precedence);
    }
}