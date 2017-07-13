package com.example.expressiontree_framework;

/**
 * @class Visitor
 * 
 * @brief Abstract base class for all visitors to all classes that
 *        derive from @a ComponentNode.  This class plays the role of
 *        the "Visitor" in the Visitor pattern.
 */
public interface Visitor { public
    /** Visit a @a LeafNode. */
    void visit(com.example.expressiontree_framework.LeafNode node); public

    /** Visit a @a CompositeNegateNode. */
    void visit(com.example.expressiontree_framework.CompositeNegateNode node); public

    /** Visit a @a CompositeAddNode. */
    void visit(com.example.expressiontree_framework.CompositeAddNode node); public

    /** Visit a @a CompositeSubtractNode. */
    void visit(com.example.expressiontree_framework.CompositeSubtractNode node); public

    /** Visit a @a CompositeDivideNode. */
    void visit(com.example.expressiontree_framework.CompositeDivideNode node); public

    /** Visit a @a CompositeMultiplyNode. */
    void visit(com.example.expressiontree_framework.CompositeMultiplyNode node);
}