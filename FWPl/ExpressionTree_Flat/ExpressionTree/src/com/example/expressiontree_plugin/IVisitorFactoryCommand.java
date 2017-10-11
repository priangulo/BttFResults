package com.example.expressiontree_plugin;

/** 
 * This interface uses the Command pattern to create @a Visitor
 * implementations at runtime.
 */
interface IVisitorFactoryCommand {
    public com.example.expressiontree_framework.Visitor execute();
}