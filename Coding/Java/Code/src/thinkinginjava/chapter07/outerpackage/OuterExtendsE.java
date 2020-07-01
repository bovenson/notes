//: OuterExtendsE.java
package thinkinginjava.chapter07.outerpackage;

public class OuterExtendsE extends OuterPackage {
    public static void main(String args[]) {
        OuterExtendsE oee = new OuterExtendsE();
        oee.funPublic();
        oee.funPackage();
        oee.funProtected();
        //！ oee.funPrivate();   // 找不到符号
    }
} /** Output
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter07/outerpackage/OuterExtendsE.java 
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter07.outerpackage.OuterExtendsE 
funPublic
funPackage
funPrivate
*/