package com.example.expressiontree_plugin;

/** 
 * This interface uses the Command pattern to create @a Platform
 * implementations at runtime.
 */
interface IPlatformFactoryCommand {
    public com.example.expressiontree_framework.Platform execute();
}