package GatesApp_plugin;

import logicGates_framework.And;
import logicGates_framework.Gate;
import logicGates_plugin.InputPort;
import logicGates_framework.Not;
import logicGates_framework.Or;
import logicGates_framework.OutputPort;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import logicGates_framework.Wire;
//import logicGates.*;

/**
 *
 * @author don
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        aCircuit();
        System.out.println("Done!");
    }
    
    public static void aCircuit() {
        // is a == b?
        //if (Feature.tables) {
            logicGates_framework.Gate.resetDB();
        //}
        
        InputPort a = new InputPort("a");
        InputPort b = new InputPort("b");
        logicGates_framework.OutputPort r = new logicGates_framework.OutputPort("r");
        
        logicGates_framework.Not n1 = new logicGates_framework.Not("n1");
        logicGates_framework.Not n2 = new logicGates_framework.Not("n2");
        
        logicGates_framework.And a1 = new logicGates_framework.And("a1");
        logicGates_framework.And a2 = new logicGates_framework.And("a2");
        
        logicGates_framework.Or o1 = new logicGates_framework.Or("o1");
        
        new logicGates_framework.Wire(a,n1,"i1");
        new logicGates_framework.Wire(n1,a1,"i1");
        new logicGates_framework.Wire(b,a1,"i2");
        
        new logicGates_framework.Wire(a,a2,"i1");
        new logicGates_framework.Wire(b,n2,"i1");
        new logicGates_framework.Wire(n2,a2,"i2");
        
        new logicGates_framework.Wire(a1,o1,"i1");
        new logicGates_framework.Wire(a2,o1,"i2");
        new logicGates_framework.Wire(o1,r);
                
        //if (Feature.tables) {
            logicGates_framework.Gate.printDB();
        //}
        
        //if (Feature.constraints) {
            boolean result = logicGates_framework.Gate.verify();
            System.out.println("Model is correct: " + result);
            if (!result)
                return;
        //}
        
        //if (Feature.eval) {
            
            a.setValue(GatesApp_framework.Value.TRUE);
            b.setValue(GatesApp_framework.Value.FALSE);


            int rvalue = r.getValue();
            if (rvalue != GatesApp_framework.Value.TRUE) {
                System.out.println("r value is wrong");
            } else {
                System.out.println("\nEvaluation of circuit is Correct!");
            }
        //}
    }
}