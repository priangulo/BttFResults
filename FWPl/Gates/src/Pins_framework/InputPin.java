/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Pins_framework;

//import GatesApp.*;
import logicGates_framework.Gate;
import Errors_framework.PinAlreadySet;
//import Errors.*;
import GatesApp_framework.Value;
import logicGates_framework.Wire;

/**
 *
 * @author don
 */
public class InputPin {

    public String name; public
    logicGates_framework.Gate inputOf; public
    logicGates_framework.Wire wireFrom; // only from one source
    
    public InputPin(String name, logicGates_framework.Gate parent) {
        this.name = name;
        inputOf = parent;
        wireFrom = null;
    }
    
    public void addWire(logicGates_framework.Wire w) {
        if (wireFrom != null)
            throw new Errors_framework.PinAlreadySet("pin "+ name + " of gate " + inputOf.name + " with " + wireFrom.o.name);
        wireFrom = w;
    }
    
    public String toString() {
        return inputOf.name + "." +name;
    }
    
    //@Feature(Feature.constraints)    
    public boolean isUsed() {
        return wireFrom != null;
    }
    
    public String nameOfGate() {
        return inputOf.name;
    }
    
    //@Feature(Feature.eval)    /*  this is for circuit execution */    
    public int getValue() {
        return wireFrom.getValue();
    }
}