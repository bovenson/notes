//: DotNew.java
//public class DotNew {
//    public class Inner {
//        public void f() {
//            System.out.println("DotNew.Inner.f()");
//        }
//    }
//    public static class StaticInner {
//        public void f() {
//            System.out.println("DotNew.StaticInner.f()");
//        }
//    }
//    public static void main(String args[]) {
//        DotNew dn = new DotNew();
//        DotNew.Inner dni = dn.new Inner();
//        dni.f();
//        //! Inner in = new Inner(); // Error: 无法从静态上下文中引用非静态 变量 this
//        //! Inner in = new DotNew.Inner(); // Error: 无法从静态上下文中引用非静态 变量 this
//        Inner in = dn.new Inner();
//        in.f();
//        // Inner in2 = new dn.Inner(); // Error: 程序包dn不存在
//        StaticInner si = new StaticInner();
//        si.f();
//    }
//} /** Output
DotNew.Inner.f()
DotNew.StaticInner.f()
 */