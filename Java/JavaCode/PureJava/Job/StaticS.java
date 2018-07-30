class A {
    public A (String text) {
        System.out.println(text);
    }
}

class B {
    static A a1 = new A("a1");
    A a2 = new A("a2");
    
    public B () {
        a2 = new A("a4");
    }
}

public class StaticS {
    public static void main(String args[]) {
        B b = new B();
    }
}
