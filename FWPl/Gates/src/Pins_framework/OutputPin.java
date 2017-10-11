/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pins_framework;

//import java.util.*;
//import logicGates.*;

import java.util.AbstractList;
import java.util.LinkedList;

import GatesApp_framework.Value;
//import GatesApp.*;
import logicGates_framework.Gate;
import logicGates_framework.Wire;

/**
 *
 * @author don
 */
public class OutputPin { public
	int value;
    public String name; public
    logicGates_framework.Gate outputOf; public
    AbstractList<logicGates_framework.Wire> wiresFrom;
    
    public OutputPin(String name, logicGates_framework.Gate parent) {
        this.name = name;
        outputOf = parent;
        wiresFrom = new LinkedList<logicGates_framework.Wire>();
        value = GatesApp_framework.Value.UNKNOWN;
    }
    
    public void addWire(logicGates_framework.Wire w) {
        wiresFrom.add(w);
    }
    
    public String toString() {
        return outputOf.name + "." +name;
    }
    
    public String nameOfGate() {
        return outputOf.name;
    }
    
    //@Feature(Feature.constraints)    
    public boolean isUsed() {
        return !wiresFrom.isEmpty();
    }
    
    //@Feature(Feature.eval)    
    public int getValue() {
        return outputOf.getValue();
    }
}