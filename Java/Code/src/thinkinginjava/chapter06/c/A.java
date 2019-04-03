//: A.java
package thinkinginjava.chapter06.c;

public class A {
    String outer = "A.outer";   // 包访问权限

    void printB() {
        B b = new B();
        System.out.println(b.inner);    // b.inner: 同一个文件中，包访问权限测试
    }
}

class B {
    String inner = "B.inner";   // 包访问权限
    String outer = "B.inner";   // 包访问权限
}