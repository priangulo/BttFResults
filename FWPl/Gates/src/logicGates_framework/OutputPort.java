/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicGates_framework;

//import Pins.*;
//import GatesApp.*;
import Pins_framework.InputPin;

import java.util.LinkedList;

//import java.util.*;
//import static logicGates.InputPort.table;

import GatesApp_framework.Value;

/**
 *
 * @author don
 */
public class OutputPort extends logicGates_framework.Gate {

    public OutputPort(String name) {
        super(name);
        Pins_framework.InputPin i1 = new Pins_framework.InputPin("i1",this);
        inputs.put("i1", i1);
        //if (Feature.tables) {
            table.add(this);
        //}
    }
    
    public Pins_framework.InputPin getInput() { 
        return inputs.get("i1");
    }
    
    //@Feature(Feature.tables)     
    static public LinkedList<logicGates_framework.Gate> table;
    
    public static void resetTable() {
        table = new LinkedList<logicGates_framework.Gate>();
    }
    
    public static LinkedList<logicGates_framework.Gate> getTable() { 
        return table;
    }
    
    //@Feature(Feature.eval)    
    public int getValue() {
        return getInput().getValue();
    }
}