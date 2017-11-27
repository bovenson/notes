
//: ExtendsE.java
package thinkinginjava.chapter07;

import thinkinginjava.chapter07.outerpackage.OuterPackage;

public class ExtendsE extends OuterPackage {
    public static void main(String args[]) {
        ExtendsE ee = new ExtendsE();
        ee.funPublic();
        ee.funProtected();
        //! ee.funPackage();    // 找不到符号
        //! ee.funPrivate();    // 找不到符号
    }
} /** Output
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter07/ExtendsE.java 
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter07.ExtendsE
funPublic
funProtected
*/