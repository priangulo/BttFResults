package com.example.expressiontree_plugin;

/** 
 * This interface uses the Command pattern to create @a
 * UserCommand implementations at runtime.
 */
public interface IUserCommandFactoryCommand {
    public com.example.expressiontree_framework.UserCommand execute(String param);
}