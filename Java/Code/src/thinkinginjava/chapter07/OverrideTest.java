//: OverrideTest.java

public class OverrideTest extends OverrideClass {
    //! final int b; 
    //! b = 20; // 语法错误
    void f(int i) {
        final int a;
        a = 10;
        //! final static int c;  // static不能用来修饰局部变量
        // c = 10;
        System.out.println("OverrideTest f(int) " + i);
    }

    static void f(long l) {
        int c;
    }

    void f(double d) {
        System.out.println("OverrideTest f(double) " + d);
    }

    void f(String s) {
        System.out.println("OverrideTest f(String) " + s);
    }

    public static void main(String args[]) {
        OverrideTest ot = new OverrideTest();
        ot.f(1);
        ot.f('c');
        ot.f(1f);
        ot.f(1.0);
        ot.f("string");
    }
} /**Output
OverrideTest f(int) 1
OverrideClass f(char) c
OverrideClass f(String) 1.0
OverrideTest f(double) 1.0
OverrideTest f(String) string
*/

class OverrideClass {
    void f(int i) {
        System.out.println("OverrideClass f(int) " + i);
    }

    void f(char c) {
        System.out.println("OverrideClass f(char) " + c);
    }

    void f(float f) {
        System.out.println("OverrideClass f(String) " + f);
    }
}

