//: ProtectedControlTest.java
package thinkinginjava.chapter06.c;
import thinkinginjava.chapter06.a.A;

public class ProtectedControlTest extends A {
    public static void main(String args[]) {
        ProtectedControlTest pct = new ProtectedControlTest();
        System.out.println(pct.pt); // 不同包，protected权限测试
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/c/ProtectedControlTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.c.ProtectedControlTest
thinkinginjava.chapter06.a
*/