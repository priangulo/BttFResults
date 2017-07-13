package com.example.expressiontree_framework;

/**
 * @class UserCommand
 *
 * @brief Plays the role of the "Command" in the Command pattern to
 *        define an API for "ConcreteCommand" implementations that
 *        perform an operation on the expression tree when it's
 *        executed.
 */
public abstract class UserCommand {
    /** 
     * Holds the expression tree that is the target of the
     * commands. 
     */
     public com.example.expressiontree_framework.TreeOps treeOps;
	
    /** Runs the command. */
    abstract public void execute() throws Exception;

    /** Print the valid commands available to users. */
    abstract public void printValidCommands(boolean verboseField);
}