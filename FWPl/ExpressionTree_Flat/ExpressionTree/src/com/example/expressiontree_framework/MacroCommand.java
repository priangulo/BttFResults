package com.example.expressiontree_framework;

import java.util.Vector;

/**
 * @class MacroCommand
 *
 * @brief Execute a sequence of commands.  This plays the role of the
 *        "ConcreteCommand" in the Command pattern.
 */
public class MacroCommand extends com.example.expressiontree_framework.UserCommand {
    /** Vector of commands that are executed as a macro. */
     public Vector<com.example.expressiontree_framework.UserCommand> macroCommands =
        new Vector<com.example.expressiontree_framework.UserCommand>();

    /** Expression input by the user. */
     public String expr; public

    /** 
     * Constructor that provides the appropriate @a TreeOps and
     * sequence of commands.
     */
    MacroCommand(com.example.expressiontree_framework.TreeOps context,
                 Vector<com.example.expressiontree_framework.UserCommand> macroCommands) {
        super.treeOps = context;
        this.macroCommands = macroCommands;
    }

    /** Quit the event loop. */
    public void execute() throws Exception {
        for(com.example.expressiontree_framework.UserCommand c : macroCommands)
            c.execute();
    }

    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
        /** No menu to print in succinct mode. */
    }
}