package com.example.expressiontree_plugin;

/**
 * @class VerboseModeInputHandler
 * 
 * @brief Provides a concrete interface for verbosely handling input
 *        events associated with the expression tree application. 
 *
 *        This class overrides several hook methods for use in the
 *        Template Method pattern.
 */
public class VerboseModeInputHandler extends com.example.expressiontree_plugin.InputHandler {
    /** Keeps track of whether we've prompted the user already. */
    private boolean prompted;
	
    /** Ctor */
    VerboseModeInputHandler() {
        prompted = false;
    }

    /** This hook method verbosely prompts the user for input. */
    public void promptUser() {
        com.example.expressiontree_framework.Platform platform = com.example.expressiontree_framework.Platform.instance();

        if (!prompted) {
            platform.disableAll(verboseField);
              platform.outputMenu("", "", "");  
              platform.outputMenu("1a.", "format", "[in-order]");
              platform.outputMenu("1b.", "set", "[variable=value]");
              platform.outputMenu("2.", "expr", "[expression]");
              platform.outputMenu("3a.", "eval", "[post-order]");
              platform.outputMenu("3b.", "print", "[in-order | pre-order | post-order| level-order]");
              platform.outputMenu("0c.", "quit", "");
              platform.outputMenu("", "", "");

            prompted = true;
        }
        /** Output the '>' prompt character. */
        platform.outputString("> ");
    }

    /** 
     * This hook method makes the appropriate command based on the
     * user input.
     */
    public com.example.expressiontree_framework.UserCommand makeUserCommand(String userinput) {
        return userCommandFactory.makeUserCommand(userinput);
    }	
}