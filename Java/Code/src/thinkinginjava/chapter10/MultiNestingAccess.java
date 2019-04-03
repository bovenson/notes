//: MultiNestingAccess.java
class MNA {
    private void e() {
        System.out.println("MNA.e()");
    }
    private void f() {
        System.out.println("MNA.f()");
    }
    class A {
        private void g() {
            System.out.println("MNA.A.g()");            
        }
        private void f() {
            System.out.println("MNA.A.f()");            
        }
        public class B {
            void h() {
                System.out.println("MNA.A.B.h()");
                e();   
                MNA.this.f();      // 访问最外围类成员         
                f();
                g();
            }
        }
    }
}

public class MultiNestingAccess {
    public static void main(String args[]) {
        MNA mna = new MNA();
        MNA.A mnaa = mna.new A();
        MNA.A.B mnaab = mnaa.new B();
        mnaab.h();
    }
} /** Output
MNA.A.B.h()
MNA.e()
MNA.f()
MNA.A.f()
MNA.A.g()
 */