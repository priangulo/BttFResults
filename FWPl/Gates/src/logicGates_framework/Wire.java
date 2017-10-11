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

import java.util.HashSet;
import java.util.LinkedList;

import GatesApp_framework.Value;

/**
 *
 * @author don
 */
public class Wire extends logicGates_framework.Printable {
    public Pins_framework.InputPin i;
    public Pins_framework.OutputPin o;
    
    public Wire( Pins_framework.OutputPin o, Pins_framework.InputPin i ) {
        this.i = i;
        this.o = o;
        i.addWire(this);
        o.addWire(this);
        //if (Feature.tables) {
            table.add(this);
        //}
    }
    
    public Wire( logicGates_framework.InputPort o, logicGates_framework.Gate i, String name) {
        this(o.getOutput(), i.getInput(name));
    }
    
    public Wire( logicGates_framework.Gate from, String frompin, logicGates_framework.Gate to, String topin ) {
        this(from.getOutput(frompin),to.getInput(topin));
    }
    
    public Wire( logicGates_framework.Gate from, logicGates_framework.Gate to,String topin ) {
        this(from, "o", to, topin);
    }
    
    public Wire( logicGates_framework.Gate from, logicGates_framework.OutputPort port) {
        this(from.getOutput("o"),port.getInput());
    }
    
    public void print(String x) {
        System.out.println("wire from " + o + " to " + i);   // param x is ignored
    }
    
    //@Feature(Feature.tables)    
    static public LinkedList<logicGates_framework.Wire> table;
    
    public static void resetTable() {
        table = new LinkedList<logicGates_framework.Wire>();
    }
    
    public static LinkedList<logicGates_framework.Wire> getTable() { 
        return table;
    }
    
    //@Feature(Feature.constraints)    
    public boolean isUsed() {
        return i.isUsed() && o.isUsed();
    }
    
    public static boolean verify() {
        boolean OK = true;
        for (logicGates_framework.Wire w : table) {
            OK = w.isUsed() && OK;
        }
        return OK;
    }
    
    //@Feature(Feature.eval)    
    public int getValue() {
        return o.getValue();
    }
}