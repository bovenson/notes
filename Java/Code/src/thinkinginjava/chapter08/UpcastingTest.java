//: UpcastingTest.java

public class UpcastingTest {
    public static void main(String args[]) {
        B b = new B();
        b.f();
        A a = b;
        a.f();
    }
} /**Output
B.f()
B.f()
*/

class A {
    void f() {
        System.out.println("A.f()");
    }
}

class B extends A {
    void f() {
        System.out.println("B.f()");
    }
}