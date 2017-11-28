//: InterfaceB.java

interface I1 {
    void f();
}

interface I2 {
    int f(int i);
}

interface I3 {
    int f();
}

class C {
    public int f() {
        return 1;
    }
}

class C2 implements I1, I2 {
    public void f() {}
    public int f(int i) {return 1;}
}

class C3 extends C implements I2 {
    public int f(int i) {
        return 1;
    }
}

class C4 extends C implements I3 {
    public int f() {return 1;}
}

//! class C5 extends C implements I1 {} // ERROR: C5不是抽象的, 并且未覆盖I1中的抽象方法f()
//! interface I4 extends I1, I3 {}  // ERROR: 类型I3和I1不兼容; 两者都定义了f(), 但却带有不相关的返回类型