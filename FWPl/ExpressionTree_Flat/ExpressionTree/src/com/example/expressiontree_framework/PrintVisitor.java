package com.example.expressiontree_framework;

/**
 * @class PrintVisitor
 *
 * @brief This class serves as a visitor that print the contents of
 *        each type of node in an expression tree.  This class plays
 *        the role of the "ConcreteVisitor" in the Visitor pattern.
 */
public class PrintVisitor implements com.example.expressiontree_framework.Visitor {
    /** Ctor */
    public PrintVisitor() {    
    }

    /** Visits a @a LeafNode and prints it contents. */
    public void visit(com.example.expressiontree_framework.LeafNode node) {
        com.example.expressiontree_framework.Platform.instance().addString(node.item() + " ");
    }

    /** Visit a @a CompositeNegateNode and prints its contents. */
    public void visit(com.example.expressiontree_framework.CompositeNegateNode node) {
        com.example.expressiontree_framework.Platform.instance().addString("-");
    }

    /** Visit a @a CompositeAddNode and prints its contents. */
    public void visit(com.example.expressiontree_framework.CompositeAddNode node) {
        com.example.expressiontree_framework.Platform.instance().addString("+ ");
    }

    /** Visit a @a CompositeSubtractNode and prints its contents. */
    public void visit(com.example.expressiontree_framework.CompositeSubtractNode node) {
        com.example.expressiontree_framework.Platform.instance().addString("- ");
    }

    /** Visit a @a CompositeDivideNode and prints its contents. */
    public void visit(com.example.expressiontree_framework.CompositeDivideNode node) {
        com.example.expressiontree_framework.Platform.instance().addString("/ ");
    }

    /** Visit a @a CompositeMultiplyNode and print its contents. */
    public void visit(com.example.expressiontree_framework.CompositeMultiplyNode node) {
        com.example.expressiontree_framework.Platform.instance().addString("* ");
    }
}