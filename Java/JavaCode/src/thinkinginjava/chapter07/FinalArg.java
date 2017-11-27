//: FinalArg.java

public class FinalArg {
    void f(final int i, final A a) {
        //! i = 10; // 不能分配最终参数i
        //! a = new A();    // 不能分配最终参数a
        a.a = 10;
    }

    void fun() {
        System.out.println("fun in FinalArg");
    }

    public static void main(String args[]) {
        new FinalArg().f(1, new A());
    }
}

class A {
    int a;

    private void fun() {
        System.out.println("fun in A");
    }
}