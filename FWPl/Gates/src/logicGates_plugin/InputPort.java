/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicGates_plugin;

import java.util.LinkedList;

import Errors_plugin.NoValueSet;
import GatesApp_framework.Value;
//import Pins.*;
//import GatesApp.*;
import Pins_framework.OutputPin;

//import java.util.*;

/**
 *
 * @author don
 */
public class InputPort extends logicGates_framework.InputPort {

    public InputPort(String name) {
        super(name);
        Pins_framework.OutputPin o = new Pins_framework.OutputPin("o",this);
         outputs.put("o", o);
        //if (Feature.tables) {
            table.add(this);
        //}
    }
    
    //@Feature(Feature.eval)   /* for evaluation */            
    int value = GatesApp_framework.Value.UNKNOWN;
        
    public void setValue(int v) {
        value = v;
    }
    
    public int getValue() {
        if (value == GatesApp_framework.Value.UNKNOWN)
           throw new NoValueSet("for port " + name);
        return value;
    }
}