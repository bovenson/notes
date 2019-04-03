//: PackageControlTest.java
package thinkinginjava.chapter06.c;

public class PackageControlTest {
    public static void main(String args[]) {
        A a = new A();
        a.printB();
        System.out.println(a.outer);    // 同一个包中，不同文件的不同类数据成员包访问权限测试
        B b = new B();
        System.out.println(b.outer);    // 访问同一个包中其他文件的非public类
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/c/PackageControlTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.c.PackageControlTest
B.inner
A.outer
B.inner
*/