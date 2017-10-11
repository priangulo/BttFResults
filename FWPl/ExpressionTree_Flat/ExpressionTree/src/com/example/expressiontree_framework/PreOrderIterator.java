package com.example.expressiontree_framework;

import java.util.Iterator;
import java.util.Stack;

/**
 * @class PreOrderIterator
 * 
 * @brief Iterates through an @a Tree in pre-order.  Plays
 *        the role of the "ConcreteStrategy" in the Strategy pattern
 *        that defines the pre-order iteration algorithm.
 */
public class PreOrderIterator implements Iterator<com.example.expressiontree_framework.ExpressionTree> {
    /** Stack of expression trees. */
     public Stack <com.example.expressiontree_framework.ExpressionTree> stack =
        new Stack<com.example.expressiontree_framework.ExpressionTree>();
	
    /** Ctor */
    public PreOrderIterator(com.example.expressiontree_framework.ExpressionTree tree) {
        if (!tree.isNull()) 
            stack.push(tree);
    }

    /** Proceeds to next expression tree in the stack. */
    public com.example.expressiontree_framework.ExpressionTree next() {
        com.example.expressiontree_framework.ExpressionTree result = stack.peek();
			
        if (!stack.isEmpty()) {
            /**
             * We need to pop the node off the stack before
             * pushing the children, or else we'll revisit this
             * node later.
             */
            com.example.expressiontree_framework.ExpressionTree temp = stack.pop();
	
            /** 
             * Note the order here: right first, then left. Since
             * this is LIFO, this results in the left child being
             * the first evaluated, which fits into the Pre-order
             * traversal strategy.
             */
            if (!temp.right().isNull())
                stack.push(temp.right());
            if (!temp.left().isNull())
                stack.push(temp.left());
        }

        return result;
    }
		
    /** Returns false if stack is empty. */
    public boolean hasNext() {
        return !stack.empty();
    }

    /** Removes an expression tree from the top of the stack. */
    public void remove() {
        stack.pop();
    }
}