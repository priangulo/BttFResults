/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicGates_framework;

import GatesApp_framework.Value;
//import Pins.*;
//import GatesApp.*;
import Pins_framework.InputPin;
import Pins_framework.OutputPin;

import java.util.LinkedList;


/**
 *
 * @author don
 */
public class Not extends logicGates_framework.Gate { public
    Pins_framework.InputPin i1;

    public Not(String name) {
        super(name);
        i1 = new Pins_framework.InputPin("i1",this);
        inputs.put("i1", i1);
        Pins_framework.OutputPin o = new Pins_framework.OutputPin("o",this);
         outputs.put("o", o);
        //if (Feature.tables) {
            table.add(this);
        //}
    }
    
    //@Feature(Feature.tables)     
    static public LinkedList<logicGates_framework.Not> table;
    
    public static void resetTable() {
        table = new LinkedList<logicGates_framework.Not>();
    }
    
    public static LinkedList<logicGates_framework.Not> getTable() { 
        return table;
    }
    
    //@Feature(Feature.eval)   /* for logic diagram evaluation */    
    public int getValue() {
    	int v = i1.getValue();
        return (v == GatesApp_framework.Value.TRUE)? GatesApp_framework.Value.FALSE : GatesApp_framework.Value.TRUE;
    }
    
}