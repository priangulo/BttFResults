/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package D_framework;

/**
 *
 * @author Don
 */
public class Calc{ public
    
    Double value = 0.0; public

    void add(String n) { value = value+Double.parseDouble(n); } public

    void clear() { value =0.0; } public

    void div(String n) { value = value/Double.parseDouble(n); } public

    String get() { return value.toString(); } public

    void mul(String n) { value = value*Double.parseDouble(n); } public

    void set(String n) { value = Double.parseDouble(n); } public

    void sub(String n) { value = value - Double.parseDouble(n); }
}