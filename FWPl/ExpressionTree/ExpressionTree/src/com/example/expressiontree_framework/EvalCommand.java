package com.example.expressiontree_framework;

/**
 * @class EvalCommand
 *
 * @brief Evaluates the expression tree in the desired format, e.g.,
 *        "in-order," "pre-order," "post-order", or "level-order".
 *        This plays the role of the "ConcreteCommand" in the Command
 *        pattern.
 */
public class EvalCommand extends com.example.expressiontree_framework.UserCommand {
    /** Format to use for the evaluation. */
     public String format;

    /** 
     * Constructor that provides the appropriate TreeOps and the
     * requested format.
     */
    public EvalCommand(com.example.expressiontree_framework.TreeOps context, 
                       String evalformat) {
        super.treeOps = context;
        format = evalformat;
    }

    /** Evaluate the expression tree. */
    public void execute() throws Exception {
        treeOps.evaluate(format);
    }

    /** Creates a menu for the user. */
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