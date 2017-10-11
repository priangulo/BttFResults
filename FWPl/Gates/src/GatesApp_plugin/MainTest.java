/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GatesApp_plugin;

import logicGates_framework.And;
import logicGates_framework.Gate;
import logicGates_plugin.InputPort;
import logicGates_framework.Not;
import logicGates_framework.Or;
import logicGates_framework.OutputPort;
import logicGates_framework.Wire;
import org.junit.Test;
import RegTest_plugin.Utility;
//import static org.junit.Assert.*;

/**
 *
 * @author dsb
 */
public class MainTest {

    public MainTest() {
    }

    @Test
    public void h2Example() {
        InputPort a, b, c, d;
        logicGates_framework.OutputPort r, t;
        logicGates_framework.Or or1, or2, or3;
        logicGates_framework.And and1;
        
        Utility.init();
        Utility.redirectStdOut("output.txt");
        
        //if (Feature.tables) {
            logicGates_framework.Gate.resetDB();
        //}

        a = new InputPort("a");
        b = new InputPort("b");
        c = new InputPort("c");
        d = new InputPort("d");
        r = new logicGates_framework.OutputPort("r");
        t = new logicGates_framework.OutputPort("t");

        or1 = new logicGates_framework.Or("or1");
        or2 = new logicGates_framework.Or("or2");
        or3 = new logicGates_framework.Or("or3");

        and1 = new logicGates_framework.And("and1");

        new logicGates_framework.Wire(a, or1, "i1");
        new logicGates_framework.Wire(b, or1, "i2");
        new logicGates_framework.Wire(c, or2, "i1");
        new logicGates_framework.Wire(d, or2, "i2");
        new logicGates_framework.Wire(or1, and1, "i1");
        new logicGates_framework.Wire(or2, and1, "i2");
        new logicGates_framework.Wire(or1, or3, "i1");
        new logicGates_framework.Wire(or2, or3, "i2");
        new logicGates_framework.Wire(and1, r);
        new logicGates_framework.Wire(or3, t);

        //if (Feature.tables) {
            logicGates_framework.Gate.printDB();
        //}

        //if (Feature.constraints) {
            System.out.println("Model is correct: " + logicGates_framework.Gate.verify());
        //}

        //if (Feature.eval) {
            boolean noErrors = true;

            a.setValue(GatesApp_framework.Value.TRUE);
            b.setValue(GatesApp_framework.Value.FALSE);
            c.setValue(GatesApp_framework.Value.FALSE);
            d.setValue(GatesApp_framework.Value.FALSE);
            int rvalue = r.getValue();
            int tvalue = t.getValue();
            if (rvalue != GatesApp_framework.Value.FALSE) {
                System.out.println("r value is wrong");
                noErrors = false;
            }
            if (tvalue != GatesApp_framework.Value.TRUE) {
                System.out.println("t value is wrong");
                noErrors = false;
            }
            if (noErrors) {
                System.out.println("\nEvaluation of circuit is Correct!");
            }
            
              Utility.validate("output.txt", "Correct/h2Example.txt", false);
        //}

    }
    
    @Test
    public void aEQb() {
        // is a == b?
        InputPort a, b;
        logicGates_framework.OutputPort r;
        logicGates_framework.And a1,a2;
        logicGates_framework.Or o1;
        logicGates_framework.Not n1,n2;
        
        Utility.init();
        Utility.redirectStdOut("output.txt");
        
        //if (Feature.tables) {
            logicGates_framework.Gate.resetDB();
        //}
        
        a = new InputPort("a");
        b = new InputPort("b");
        r = new logicGates_framework.OutputPort("r");
        a1 = new logicGates_framework.And("a1");
        a2 = new logicGates_framework.And("a2");
        o1 = new logicGates_framework.Or("o1");
        n1 = new logicGates_framework.Not("n1");
        n2 = new logicGates_framework.Not("n2");
        
        new logicGates_framework.Wire(a,a1,"i1");
        new logicGates_framework.Wire(b,a1,"i2");
        new logicGates_framework.Wire(a,n1,"i1");
        new logicGates_framework.Wire(b,n2,"i1");
        new logicGates_framework.Wire(n1,a2,"i1");
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
            if (rvalue != GatesApp_framework.Value.FALSE) {
                System.out.println("r value is wrong");
            } else {
                System.out.println("\nEvaluation of circuit is Correct!");
            }
        //}
          Utility.validate("output.txt", "Correct/aEQb.txt", false);
    }

    @Test
    public void aNEb() {
        // is a != b?
        
        Utility.init();
        Utility.redirectStdOut("output.txt");
        
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
        
          Utility.validate("output.txt", "Correct/aNEb.txt", false);
    }
}