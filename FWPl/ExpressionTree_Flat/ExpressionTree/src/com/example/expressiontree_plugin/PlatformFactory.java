package com.example.expressiontree_plugin;

import java.util.HashMap;

/**
 * @class PlatformFactory
 * 
 * @brief This class is a factory that is responsible for building the
 *        designated @a Platform implementation at runtime.
 */
public class PlatformFactory {
    /**
     * HashMap used to map strings containing the Java platform names
     * and dispatch the execute() method of the associated @a Platform
     * implementation.
     */
    private HashMap<String, IPlatformFactoryCommand> platformMap = 
        new HashMap<String, IPlatformFactoryCommand>();
	
    /** 
     * Ctor that stores the objects that perform input and output for
     * a particular platform, such as CommandLinePlatform or the
     * AndroidPlatform.
     */
    public PlatformFactory(final Object input,
                           final Object output,
                           final Object activity) {
    	/** 
         * The "The Android Project" string maps to a command object
         * that creates an @a AndroidPlatform implementation.
         */
        
                        platformMap.put("The Android Project", new IPlatformFactoryCommand() {
                            /** 
                             * Receives the three parameters, input
                             * (EditText), output (TextView), activity
                             * (activity).
                             */
                            public com.example.expressiontree_framework.Platform execute() {
                                return new AndroidPlatform(input,
                                                           output,
                                                           activity);
                            }
                        });
	
    	/** 
         * The "Sun Microsystems Inc." string maps to a command object
         * that creates an @a CommandLinePlatform implementation.
         */
        
                        platformMap.put("Sun Microsystems Inc.", new IPlatformFactoryCommand() {
                            public com.example.expressiontree_framework.Platform execute() {
                                return new CommandLinePlatform(input,
                                                               output);
                            }
                        });

    	/** 
         * The "Oracle Corporation" string maps to a command object
         * that creates an @a CommandLinePlatform implementation.
         */
         
                        platformMap.put("Oracle Corporation", new IPlatformFactoryCommand() {
                            public com.example.expressiontree_framework.Platform execute() {
                                return new CommandLinePlatform(input,
                                                               output);
                            }
                        });
    }

    /** 
     * Create a new @a Platform object based on underlying Java
     * platform.
     */
    public com.example.expressiontree_framework.Platform makePlatform() {
        String name = System.getProperty("java.specification.vendor");

        return platformMap.get(name).execute();
    }
}