/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Two_plugin;

/**
 *
 * @author don
 */
public class B extends Two_framework.B {
    Two_framework.A[] b = { new Two_framework.A("1"), new Two_framework.A("2"), new Two_framework.A("3") };
    public void inc() {
        for (int i=0; i<3; i++) 
            b[i].inc();
    }
    public void add2() {
        for (int i=0; i<3; i++) 
            b[i].add2();
    }
    
    public String toString() {
        String result = "";
        for (int i=0; i<3; i++)
            result = result + b[i].toString() + " ";
        return result;
    }
}