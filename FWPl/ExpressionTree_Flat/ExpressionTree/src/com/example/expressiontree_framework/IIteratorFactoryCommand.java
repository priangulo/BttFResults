package com.example.expressiontree_framework;

import java.util.Iterator;

/** 
 * This interface uses the Command pattern to create @a Iterator
 * implementations at runtime.
 */
public interface IIteratorFactoryCommand {
    public Iterator<com.example.expressiontree_framework.ExpressionTree> execute(com.example.expressiontree_framework.ExpressionTree tree);
}