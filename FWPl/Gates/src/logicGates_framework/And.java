/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicGates_framework;

//import Pins.*;
//import GatesApp.*;
import Pins_framework.InputPin;
import Pins_framework.OutputPin;

import java.util.LinkedList;

//import java.util.*;

import GatesApp_framework.Value;

/**
 *
 * @author don
 */
public class And extends logicGates_framework.Gate { public
    Pins_framework.InputPin i1; public
	Pins_framework.InputPin i2; public
    Pins_framework.OutputPin o;

    public And(String name) {
        super(name);
        i1 = new Pins_framework.InputPin("i1",this);
        i2 = new Pins_framework.InputPin("i2",this);
        inputs.put("i1", i1);
        inputs.put("i2", i2);
        o = new Pins_framework.OutputPin("o",this);
         outputs.put("o", o);
        
        //if (Feature.tables) {
            table.add(this);
        //}
    }
    
    //@Feature(Feature.tables)     
    static public LinkedList<logicGates_framework.Gate> table;
    
    public static void resetTable() {
        table = new LinkedList<logicGates_framework.Gate>();
    }
    
    public static LinkedList<logicGates_framework.Gate> getTable() { 
        return table;
    }
    
        
   // @Feature(Feature.eval)    /* for evaluation */    
    public int getValue() { 
    	int v1 = i1.getValue();
        int v2 = i2.getValue();
        if (v1==GatesApp_framework.Value.TRUE && v2==GatesApp_framework.Value.TRUE)
            return GatesApp_framework.Value.TRUE;
        else
            return GatesApp_framework.Value.FALSE;
    }
    
}