package com.example.expressiontree_framework;

import java.util.Iterator;
import java.util.HashMap;

/**
 * @class IteratorFactory
 * 
 * @brief Implementation of the Factory Method pattern that
 *        dynamically allocates the appropriate @a Iterator strategy
 *        requested by a caller.  This variant of the pattern doesn't
 *        use inheritance, so it plays the role of the ConcreteCreator
 *        in the Factory Method pattern.
 */
public class IteratorFactory {
    /**
     * Map used to validate input requests for @a Iterator
     * implementations and dispatch the execute() method of the
     * requested iterator. .
     */
     public HashMap<String, com.example.expressiontree_framework.IIteratorFactoryCommand> traversalMap = 
        new HashMap<String, com.example.expressiontree_framework.IIteratorFactoryCommand>();
	
    /** Ctor */
    public IteratorFactory() {
        /**
         * The IteratorFactory maps strings to an interface capable of
         * building the appropriate @a Iterator implementation at
         * runtime.
         */

    	/** 
         * An "in-order" string maps to a command object that creates
         * an @a InOrderIterator implementation.
         */
         traversalMap.put("in-order", new com.example.expressiontree_framework.IIteratorFactoryCommand() {
                public Iterator<com.example.expressiontree_framework.ExpressionTree> execute(com.example.expressiontree_framework.ExpressionTree tree) {
                    return new com.example.expressiontree_framework.InOrderIterator(tree);
                }
            });
            
    	/** 
         * A "pre-order" string maps to a command object that creates
         * a @a PreOrderIterator implementation.
         */
         traversalMap.put("pre-order", new com.example.expressiontree_framework.IIteratorFactoryCommand() {
                public Iterator<com.example.expressiontree_framework.ExpressionTree> execute(com.example.expressiontree_framework.ExpressionTree tree) {
                    return new com.example.expressiontree_framework.PreOrderIterator(tree);
                }
            });
            
    	/** 
         * A "post-order" string maps to a command object that creates
         * a @a PostOrderIterator implementation.
         */
         traversalMap.put("post-order", new com.example.expressiontree_framework.IIteratorFactoryCommand() {
                public Iterator<com.example.expressiontree_framework.ExpressionTree> execute(com.example.expressiontree_framework.ExpressionTree tree) {
                    return new com.example.expressiontree_framework.PostOrderIterator(tree);
                }
            });
            
    	/** 
         * A "level-order" string maps to a command object that
         * creates a @a LevelOrderIterator implementation.
         */
         traversalMap.put("level-order", new com.example.expressiontree_framework.IIteratorFactoryCommand() {
                public Iterator<com.example.expressiontree_framework.ExpressionTree> execute(com.example.expressiontree_framework.ExpressionTree tree) {
                    return new com.example.expressiontree_framework.LevelOrderIterator(tree);
                }
            });  
    }
	
    /** 
     * Create a new @a Iterator implementation based on the caller's
     * designated @a traversalOrderRequest.
     */
    public Iterator<com.example.expressiontree_framework.ExpressionTree> makeIterator(com.example.expressiontree_framework.ExpressionTree tree,
                                                 String traversalOrderRequest) {
        if (traversalOrderRequest.equals(""))
            /** 
             * Default to in-order if user doesn't explicitly request
             * a traversal order.
             */
            traversalOrderRequest = "in-order";

        /** Try to find the pre-allocated factory command. */
        com.example.expressiontree_framework.IIteratorFactoryCommand command =
            traversalMap.get(traversalOrderRequest);

        if(command != null)
            /** If we find it then execute it. */
            return command.execute(tree);
        else
            /** 
             * Otherwise, the user gave an unknown request, so throw
             * an exception.
             */
            throw new IllegalArgumentException
                (traversalOrderRequest 
                 + " is not a supported traversal order");
    }
}