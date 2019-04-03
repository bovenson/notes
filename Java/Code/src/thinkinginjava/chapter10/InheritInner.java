//: InheritInner.java

class WithInner {
    class Inner {}
}

public class InheritInner extends WithInner.Inner {
    //! InheritInner() { } // Error: 需要包含WithInner.Inner的封闭实例
    InheritInner(WithInner wi) {
        wi.super();
    }
    public static void main(String args[]) {
        WithInner wi = new WithInner();
        InheritInner ii = new InheritInner(wi);
    }
}