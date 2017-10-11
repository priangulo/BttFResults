/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package BI_framework;

//import java.math.*;
import java.math.BigInteger;

/**
 *
 * @author dsb
 */
public class Calc{
    final static public BigInteger zero = new  BigInteger("0"); public
    BigInteger value = zero; public

    void sub(String n) {  value = value.subtract(new BigInteger(n)); } public
    void mul(String n) {  value = value.multiply(new BigInteger(n)); } public
    void add(String n) {  value = value.add(new BigInteger(n)); } public
    void div(String n) {  value = value.divide(new BigInteger(n)); } public
    String get() { return value.toString(); } public
    void set(String n) { value = new BigInteger(n); } public
    void clear() { value = zero; }
}