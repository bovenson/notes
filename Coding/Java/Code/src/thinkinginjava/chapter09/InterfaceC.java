//: InterfaceC.java

public class InterfaceC {
    public class BImp implements A.B {
        public void f() {}
    }

    class CImp implements A.C {
        public void f() {}
    }

    //! Cannot implements a private interface except within that interface's defining classes;
    //! class DImp implements A.D {
    //! }

    class EImp implements E {
        public void g() {}
    }

    class EGImp implements E.G {
        public void f() { }
    }

    class EImp2 implements E {
        public void g() {}
        class EG implements E.G {
            public void f() { }
        }
    }

    public static void main(String args[]) {
        A a = new A();
        // Can't access A.D;
        //! A.D ad = a.getD();
        // Doesn't return anything but A.D;
        //! A.DImp2 di2 = a.getD(); // 不兼容的类型: A.D无法转换为A.DImp2
        //! a.getD().f();   // A.D中的f()是在不可访问的类或接口中定义的
        A a2 = new A();
        a2.receiveD(a.getD());  // !!!
    }
}

class A {
    interface B {
        void f();
    }

    public class BImp implements B {
        public void f() { }
    }

    private class BImp2 implements B {
        public void f() {}
    }

    public interface C {
        void f();
    }

    class CImp implements C {
        public void f() { }
    }

    private class CImp2 implements C {
        public void f() { }
    }

    private interface D {
        void f();
    }

    private class DImp implements D {
        public void f() { }
    }

    public class DImp2 implements D {
        public void f() { }
    }

    public D getD() { 
        return new DImp();
    }

    private D dRef;

    public void receiveD(D d) {
        dRef = d;
        dRef.f();
    }
}

interface E {
    interface G {
        void f();
    }

    // 多余的public修饰符
    public interface H {
        void f();
    }

    void g();
    // Cannot be private whthin an interface
    //! private interface I { }
}