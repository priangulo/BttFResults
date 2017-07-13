package com.example.expressiontree_plugin;

import java.util.HashMap;
import java.util.Vector;

/**
 * @class UserCommandFactory
 *
 * @brief Implementation of the Factory Method pattern that
 *        dynamically allocates the appropriate @a UserCommand object
 *        requested by caller.  This variant of the pattern doesn't
 *        use inheritance, so it plays the role of the ConcreteCreator
 *        in the Factory Method pattern.
 */
public class UserCommandFactory {
    /** 
     * Holds the expression tree that is the target of the commands.
     */
    private TreeOps treeOps;
	
    /** 
     * This interface uses the Command pattern to create @a
     * UserCommand implementations at runtime.
     */
    public static interface IUserCommandFactoryCommand {
        public com.example.expressiontree_framework.UserCommand execute(String param);
    }
	
    /**
     * Map used to validate input requests for @a UserCommand
     * implementations and dispatch the execute() method of the
     * requested user command.
     */
    private HashMap<String, IUserCommandFactoryCommand> commandMap =
        new HashMap<String, IUserCommandFactoryCommand>();

	public UserCommandFactory(){}
		
    /** Ctor */
    UserCommandFactory(final TreeOps treeOps) {   	
    	/** Initialize the TreeOps member. */
        this.treeOps = treeOps;
   
    	/** 
         * A "format" string maps to a command object that creates
         * an @a FormatCommand implementation.
         */
         commandMap.put("format", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new com.example.expressiontree_framework.FormatCommand(treeOps, param);
                }
            });
        
    	/** 
         * An "expr" string maps to a command object that creates
         * an @a ExprCommand implementation.
         */
         commandMap.put("expr", new IUserCommandFactoryCommand() { 
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new com.example.expressiontree_framework.ExprCommand(treeOps, param);
                }
            });
        
    	/** 
         * A "print" string maps to a command object that creates
         * an @a PrintCommand implementation.
         */
         commandMap.put("print", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new com.example.expressiontree_framework.PrintCommand(treeOps, param);
                }
            });
		
    	/** 
         * An "eval" string maps to a command object that creates
         * an @a EvalCommand implementation.
         */
         commandMap.put("eval", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new com.example.expressiontree_framework.EvalCommand(treeOps, param);
                }
            });
        
    	/** 
         * A "set" string maps to a command object that creates a @a
         * SetCommand implementation.
         */
         commandMap.put("set", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new com.example.expressiontree_framework.SetCommand(treeOps, param);
                }
            });
		
    	/** 
         * A "macro" string maps to a command object that creates a @a
         * MacroCommand implementation.
         */
         commandMap.put("macro", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    Vector <com.example.expressiontree_framework.UserCommand> macroCommands =
                        new Vector <com.example.expressiontree_framework.UserCommand>();

                    /** 
                     * A MacroCommand contains a "in-order"
                     * FormatCommant, the user input expression, and a
                     * "post-order" EvalCommand.  It's used to
                     * implement "Succinct Mode".
                     */
                    macroCommands.add(new com.example.expressiontree_framework.FormatCommand(treeOps,
                                                        "in-order"));
                    macroCommands.add(new com.example.expressiontree_framework.ExprCommand(treeOps,
                                                      param));
                    macroCommands.add(new com.example.expressiontree_framework.EvalCommand(treeOps,
                                                      "post-order"));

                    return new com.example.expressiontree_framework.MacroCommand(treeOps,
                                            macroCommands);
                }
            });
		
    	/** 
         * A "quit" string maps to a command object that creates a @a
         * QuitCommand implementation.
         */
         commandMap.put("quit", new IUserCommandFactoryCommand() {
                public com.example.expressiontree_framework.UserCommand execute(String param) {
                    return new QuitCommand(treeOps);
                }
            });
    }

    /** 
     * Create a new @a UserCommand object based on the caller's
     * designated @a inputString.
     */
    public com.example.expressiontree_framework.UserCommand makeUserCommand(String inputString) {
        String parameters = "";
        String commandRequest = inputString;

        int spacepos = inputString.indexOf(' ');
        if (spacepos >= 0) {
            parameters = inputString.substring(spacepos + 1);
             
                                                   commandRequest = inputString.substring(0, spacepos);
        } else /** There's only a command, but no parameters. */
            ; 

        /** Try to find the pre-allocated factory command. */
        IUserCommandFactoryCommand command = 
            commandMap.get(commandRequest);

        if(command != null)
            /** If we find it then execute it. */
            return command.execute(parameters);
        else
            /** 
             * Otherwise, the user gave an unknown request, so we'll
             * quit.
             */
            return new QuitCommand(treeOps);
    }
}