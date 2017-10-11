package com.example.expressiontree_framework;

/**
 * @class ExpressionTreeFactory
 * 
 * @brief This class implements the Factory Method pattern to create
 *        @a ExpressionTree objects.  If you want a different type
 *        of @a ExpressionTree you can subclass from this class and
 *        override the @a makeExpressionTree() factory method.
 */
public class ExpressionTreeFactory {
    /** 
     * Create a new @a ExpressionTree object that encapsulates the @a
     * componentNodeRoot that's passed as a parameter.
     */
    public com.example.expressiontree_framework.ExpressionTree makeExpressionTree(com.example.expressiontree_framework.ComponentNode componentNodeRoot) {
        return new com.example.expressiontree_framework.ExpressionTree(componentNodeRoot);
    }
}