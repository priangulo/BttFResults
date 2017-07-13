package com.example.expressiontree_framework;

/**
 * @class NullCommand
 *
 * @brief No-op command.  This plays the role of the "ConcreteCommand"
 *        in the Command pattern.
 */
public class NullCommand extends com.example.expressiontree_framework.UserCommand { public
    /**
     * Constructor that provides the appropriate @a * TreeOps and the
     * requested format.
     */
    NullCommand(com.example.expressiontree_framework.TreeOps context) {
        super.treeOps = context;
    }

    /** Set the desired format. */
    public void execute() {
        /** No-op.*/
    }

    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
        com.example.expressiontree_framework.Platform platform = com.example.expressiontree_framework.Platform.instance();
    	platform.disableAll(verboseField);
        
                            
                            platform.outputMenu("", "", "");
        
                            
                            platform.outputMenu("1a.", "format", "[post-order]");
        
                            
                            platform.outputMenu("1b.", "set", "[variable=value]");
        
                            
                            platform.outputMenu("2.", "expr", "[expression]");
        
                            
                            platform.outputMenu("3a.", "eval", "[post-order]");
        
                            
                            platform.outputMenu("3b.", "print", "[in-order | pre-order | post-order| level-order]");
        
                            
                            platform.outputMenu("0c.", "quit", "");
        
                            
                            platform.outputMenu("", "", "");
    }
}