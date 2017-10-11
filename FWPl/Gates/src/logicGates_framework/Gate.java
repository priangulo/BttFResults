/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logicGates_framework;

//import Pins.*;
//import Errors.*;
//import GatesApp.*;
import Pins_framework.InputPin;
import Pins_framework.OutputPin;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

//import java.util.*;
//import static logicGates.And.table;

import Errors_framework.NoPinFound;
import GatesApp_framework.Value;

/**
 *
 * @author don
 */
public abstract class Gate extends logicGates_framework.Printable {

    public String name; public
    HashMap<String, Pins_framework.InputPin> inputs; public
    HashMap<String, Pins_framework.OutputPin> outputs;

    public Gate(String name) {
        this.name = name;
        inputs = new HashMap<String, Pins_framework.InputPin>();
        outputs = new HashMap<String, Pins_framework.OutputPin>();
    }

    public Pins_framework.InputPin getInput(String name) {
        Pins_framework.InputPin i = inputs.get(name);
        if (i == null) {
            throw new Errors_framework.NoPinFound("input to gate " + this.name + "." + name + " not found");
        }
        return i;
    }

    public Pins_framework.OutputPin getOutput(String name) {
        Pins_framework.OutputPin o = outputs.get(name);
        if (o == null) {
            throw new Errors_framework.NoPinFound("output to gate " + this.name + "." + name + " not found");
        }
        return o;
    }

    public void print(String gateType) {
          System.out.printf("%6s gate %10s with inputs ( ", gateType, name);
        String comma = "";
        for (Pins_framework.InputPin i : inputs.values()) {
            System.out.print(comma + i);
            comma = ", ";
        }
        System.out.print(" ) and outputs ( ");
        comma = "";
        for (Pins_framework.OutputPin o : outputs.values()) {
            System.out.print(comma + o);
            comma = ", ";
        }
        System.out.println(" )");
    }

    //@Feature(Feature.tables)
    public static void resetDB() {
        logicGates_framework.And.resetTable();
        logicGates_framework.Not.resetTable();
        logicGates_framework.Or.resetTable();
        logicGates_framework.Wire.resetTable();
        logicGates_framework.InputPort.resetTable();
        logicGates_framework.OutputPort.resetTable();
    }

    public static void printDB() {
         logicGates_framework.Gate.printTable("And", logicGates_framework.And.getTable());
         logicGates_framework.Gate.printTable("Or", logicGates_framework.Or.getTable());
         logicGates_framework.Gate.printTable("Not", logicGates_framework.Not.getTable());
         logicGates_framework.Gate.printTable("Wire", logicGates_framework.Wire.getTable());
         logicGates_framework.Gate.printTable("InputPort", logicGates_framework.InputPort.getTable());
         logicGates_framework.Gate.printTable("OutputPort", logicGates_framework.OutputPort.getTable());
    }

    public static <G extends logicGates_framework.Printable> void printTable(String ttype, LinkedList<G> t) {
        for (G g : t) {
            g.print(ttype);
        }
    }

    //@Feature(Feature.constraints)
    public boolean extra() {  // subclasses override this method if something special needs to be done
        return true;
    }

    public boolean allInputsUsed() {
        boolean OK = true;
        for (String i : inputs.keySet()) {
            Pins_framework.InputPin p = inputs.get(i);
            if (!p.isUsed()) {
                System.out.println("input " + p.name + " of gate " + p.nameOfGate() + " is unused");
                OK = false;
            }
        }
        return OK;
    }

    public boolean allOutputsUsed() {
        boolean OK = true;
        for (String i : outputs.keySet()) {
            Pins_framework.OutputPin p = outputs.get(i);
            if (!p.isUsed()) {
                System.out.println("output " + p.name + " of gate " + p.nameOfGate() + " is unused");
                OK = false;
            }
        }
        return OK;
    }

    public static <G extends logicGates_framework.Gate> boolean verify(String label, LinkedList<G> table) {
        HashSet<String> hs = new HashSet<String>();
        boolean OK = true;
        for (G a : table) {

            if (hs.contains(a.name)) {
                System.out.println("multiple " + label + " with the same name: " + a.name);
                OK = false;
            } else {
                hs.add(a.name);
            }

            OK = a.allInputsUsed() && OK;
            OK = a.allOutputsUsed() && OK;
            OK = a.extra() && OK;
        }
        return OK;
    }

    public static boolean verify() {
        boolean OK = true;
         OK = logicGates_framework.And.verify("And gate", logicGates_framework.And.getTable()) && OK;
         OK = logicGates_framework.Not.verify("Not gate", logicGates_framework.Not.getTable()) && OK;
         OK = logicGates_framework.Or.verify("Or gate", logicGates_framework.Or.getTable()) && OK;
         OK = logicGates_framework.InputPort.verify("InputPort", logicGates_framework.InputPort.getTable()) && OK;
        OK = logicGates_framework.Wire.verify() && OK;
        return OK;
    }

    //@Feature(Feature.eval)    
    public abstract int getValue();  // evaluate gate(inputs)
}