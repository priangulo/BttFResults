/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Applications_plugin;

/**
 *
 * @author don
 */
public class Orig {

    public static void main(String... args) {
        One_framework.A a1 = new One_framework.A("4");
        a1.inc();
        a1.add2();
        System.out.println(a1);
        One_plugin.B b1 = new One_plugin.B();
        b1.inc();
        b1.add2();
        System.out.println(b1);
        System.out.println("---------------------");
        Two_framework.A a2 = new Two_framework.A("4");
        a2.inc();
        a2.add2();
        System.out.println(a2);
        Two_plugin.B b2 = new Two_plugin.B();
        b2.inc();
        b2.add2();
        System.out.println(b2);
    }

}