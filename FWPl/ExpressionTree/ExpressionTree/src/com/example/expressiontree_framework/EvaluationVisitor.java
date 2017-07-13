package com.example.expressiontree_framework;

import java.util.Stack;

/**
 * @class EvaluationVisitor
 *
 * @brief This plays the role of a visitor for evaluating nodes in an
 *        expression tree that is being iterated in post-order fashion
 *        (and does not work correctly with any other iterator).  This
 *        class plays the role of the "ConcreteVisitor" in the Visitor
 *        pattern.
 */
public class EvaluationVisitor implements com.example.expressiontree_framework.Visitor {
    /** 
     * Stack containing the integral total of the expression tree
     * that's being visited.
     */
     public Stack<Integer> stack =
        new Stack<Integer>();

    /** Ctor. */
    public EvaluationVisitor() {
    }

    /** Visit a @a LeafNode. */
    public void visit(com.example.expressiontree_framework.LeafNode node) {
        stack.push(node.item());
    }

    /** Visit a  CompositeSubtractNode. */
    public void visit(com.example.expressiontree_framework.CompositeNegateNode node) {
        if(stack.size() >= 1)
            stack.push(-stack.pop());
    }

    /** Visit a @a CompositeAddNode. */
    public void visit(com.example.expressiontree_framework.CompositeAddNode node) {
        if(stack.size() >= 2)
            stack.push(stack.pop() + stack.pop());
    }

    /** Visit a @a CompositeSubtractNode. */
    public void visit(com.example.expressiontree_framework.CompositeSubtractNode node) {
        if(stack.size() >= 2) {
            int rhs =stack.pop();
            stack.push(stack.pop() -rhs);
        }
    }

    /** Visit a @a CompositeDivideNode. */
    public void visit(com.example.expressiontree_framework.CompositeDivideNode node) {
        if(stack.size() >= 2) {
            if(stack.peek() != 0) {
                int rhs = stack.pop();
                stack.push(stack.pop() / rhs);
            } else {
                com.example.expressiontree_framework.Platform platform = com.example.expressiontree_framework.Platform.instance();
                
                                  platform.errorLog("EvaluationVisitor", "\n\n**: Division by zero is not allowed. ");
                
                                  platform.errorLog("EvaluationVisitor", "Resetting evaluation visitor.\n\n");
                reset();
            }
        }
    }

    /** Visit a @a CompositeMultiplyNode. */
    public void visit(com.example.expressiontree_framework.CompositeMultiplyNode node) {
        if (stack.size() >= 2)
            stack.push(stack.pop() * stack.pop());
    }

    /** Print the total of the evaluation. */
    public int total() {
        if(!stack.isEmpty())
            return stack.peek();
        else
            return 0;
    }

    /** Resets the evaluation to it can be reused. */
    public void reset() {
        stack.clear();
    }
}