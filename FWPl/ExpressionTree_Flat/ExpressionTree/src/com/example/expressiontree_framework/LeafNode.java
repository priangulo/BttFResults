package com.example.expressiontree_framework;

/**
 * @class LeafNode
 * 
 * @brief Defines a terminal node of type integer.  It plays the role
 *        of the "Leaf" in the Composite pattern.
 */
public class LeafNode extends com.example.expressiontree_framework.ComponentNode {
    /** Integer value associated with the operand. */
     public int item;
  
    /* Ctor */
    public LeafNode(int item) {
        this.item = item;
    }

    /* Ctor */
    public LeafNode(String item) {
        this.item = Integer.parseInt(item);
    }

    /* Return the item stored in the node. */
    public int item() {
        return item;
    }

    /* 
     * Define the @a accept() operation used for the Visitor
     * pattern. 
     */
    public void accept(com.example.expressiontree_framework.Visitor visitor) {
        visitor.visit(this);
    }
}