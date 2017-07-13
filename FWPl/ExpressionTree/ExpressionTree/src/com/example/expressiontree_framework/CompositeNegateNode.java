package com.example.expressiontree_framework;

/**
 * @class CompositeNegateNode
 *
 * @brief A node containing only a right child.  The meaning of this
 *        node is -right (e.g., -5, -7, etc).  It plays the role of a
 *        "Composite" in the Composite pattern.
 */
public class CompositeNegateNode extends com.example.expressiontree_framework.CompositeUnaryNode {
    /** Ctor */
    public CompositeNegateNode(com.example.expressiontree_framework.ComponentNode right) {
        super(right);
    }

    /** Return the printable character stored in the node. */
    public int item() {
        return '-';
    }

    /** 
     * Define the @a accept() operation used for the Visitor pattern
     * to accept the @a visitor.
     */
    public void accept(com.example.expressiontree_framework.Visitor visitor) {
        visitor.visit(this);
    }
}