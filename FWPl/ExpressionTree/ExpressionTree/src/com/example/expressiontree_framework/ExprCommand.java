package com.example.expressiontree_framework;

/**
 * @class ExprCommand
 *
 * @brief Set the desired expression, e.g., "1+2*3".  This plays the
 *        role of the "ConcreteCommand" in the Command pattern.
 */
public class ExprCommand extends com.example.expressiontree_framework.UserCommand {
    /** Requested expression. */
     public String expr;

    /** 
     * Constructor that provides the appropriate @a TreeOps and the
     * requested expression.
     */
    public ExprCommand(com.example.expressiontree_framework.TreeOps context, String newexpr) {
        super.treeOps = context;
        expr = newexpr;
    }

    /** Create the desired expression tree. */
    public void execute() throws Exception {
        treeOps.makeTree(expr);
    }

    /** Print the valid commands available to users. */
    public void printValidCommands(boolean verboseField) {
        com.example.expressiontree_framework.Platform platform = com.example.expressiontree_framework.Platform.instance();
    	platform.disableAll(verboseField);
        
                            
                            platform.outputMenu("", "", "");
        
                            
                            platform.outputMenu("1a.", "eval", "[post-order]");
        
                            
                            platform.outputMenu("1b.", "print", "[in-order | pre-order | post-order| level-order]");
        
                            
                            platform.outputMenu("0a.", "format", "[in-order]");
        
                            
                            platform.outputMenu("0b.", "set", "[variable = value]");
        
                            
                            platform.outputMenu("0c.", "quit", "");
        
                            
                            platform.outputMenu("", "", "");
    }
}