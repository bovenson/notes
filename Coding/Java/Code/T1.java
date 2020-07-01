class A {
    public void f() {
        System.out.println("A.f()");
    }

    public void e() {
        f();
    }
}

class B extends A {
    // @Override
    public void f() {
        System.out.println("B.f()");
    }
}

public class T1 {
    public static void main(String[] args) {
        B b = new B();
        b.e();
        b.f();
    }
}

