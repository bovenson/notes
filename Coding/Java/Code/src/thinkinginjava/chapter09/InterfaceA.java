//: InterfaceA.java

interface IA {
    void a();
}

interface IB extends IA {
    void b();
}

interface IAB extends IB {
    void iA();
}

interface IC {
    void c();
}

interface IABC extends IB, IC {
    void iC();
}

class IM implements IABC {
    public void a() {}
    public void b() {}
    public void c() {}
    public void iA() {}
    public void iC() {}
}