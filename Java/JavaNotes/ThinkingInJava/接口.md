---
title: 接口
tags: Java, 接口
---

# 接口

抽象类，是普通的类与接口之间的一种中庸之道。

## 抽象类和抽象方法

Java提供一个叫做抽象方法的机制，这种方法是不完整的；仅有声明而没有方法体。

```java
abstract void f();
```

包含抽象方法的类叫做抽象类。如果一个类包含一个或多个抽象方法，该类必须被限定为抽象的。

如果一个抽象类继承，并想创建该新类的对象，那么就必须为基类中的所有抽象方法提供方法定义。如果不这样做，那么导出类也是抽象类，并且必须用abstract关键字来限定这个类。

可以声明一个没有抽象方法的类为抽象类，这样可以阻止产生这个类的任何对象。

抽象类和抽象方法，可以使类的抽象性明确起来。抽象类还有很有用的重构工具，因为它们使得我们可以很容易地将公共方法沿着继承层次结构向上移动。

## 接口

interface这个关键字产生一个完全抽象的类，它根本就没有提供任何具体实现。它允许创建者确定方法名、参数列表和返回类型，但是没有任何方法体。接口只提供了形式，而未提供任何具体实现。

interface不仅是一个极度抽象的类，因为它允许人们通过创建一个能够被向上转型为多种基类的类型，来实现某种类似多重继承变种的特性。

要想创建一个接口，需要用interface关键字来替代class关键字。就像类一样，关键字前添加public关键字。如果不添加public，默认为包访问权限。

接口也可以包含域，但是这些域隐式地是static和final的。

要让一个类遵循某个特定接口或者是一组接口，需要使用implements关键字。

**接口中的方法必须是public的。**可以选择在接口中显式地将方法生命为public的，但即使你不这么做，它们也是public的。

## 完全解耦

只要一个方法操作的是类而非接口，那么你就只能使用这个类及其子类。接口可以在很大程度上放宽这种限制，它使得我们可以编写可复用性更好的代码。

将接口从具体实现中解耦使得接口可以应用于多种不同的具体实现，因此代码也就更具可复用性。

## Java中的多重继承

Java中只能从一个类继承，其余的元素都必须是接口。需要将所有的接口名都置于implements关键字之后，用逗号将它们一一隔开。可以继承任意多个接口，并可以向上转型为每个接口，因为每一个接口都是一个独立类型。

使用接口的核心原因：**为了能够向上转型为多个基类型（以此带来的灵活性）** 。使用接口的第二个原因于使用抽象类相同：防止客户端程序员创建该类的对象，并确保这仅仅是建立一个接口。

应该使用接口还是抽象类？如果要创建不带任何方法定义和成员变量的基类，那么就应该选择接口而不是抽象类。

## 通过继承来扩展接口

通过继承可以很容易地在接口中添加新的方法声明，还可以通过继承在新接口中组合数个接口。这两种情况都可以获取新的接口。

```java
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
```

一般情况下，只可以将extends用于单一类，但是可以引用多个基类接口。

### 组合接口时的名字冲突

```java
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
```

## 适配接口

Java允许同一个接口具有多个不同的具体实现，体现形式通常是一个接受接口类型的方法，而该接口的实现和向该方法传递的对象则取决于方法的使用者。

可以在任何现有类之上添加新的接口，所以这意味着让方法接受接口类型，是一种任何类都可以对该方法进行适配的方式。

## 接口中的域

接口中的域默认是public、static、final的，所以接口就成为一种很便捷的方式来创建常量组。这与Java SE5及之后的enum类型作用相似。

### 初始化接口中的域

在接口中定义的域不能是“空final”，但是可以被非常量表达式初始化。

```java
public interface RandVals {
  	Random RAND = new Random();
  	int RANDOM_INT = RAND.nextInt();
}
```

域是static的，它们就可以在类第一次被加载时初始化，这发生在任何域被首次访问之前。

这些域不是借口的一部分，它们的值被存储在该接口的静态存储区域内。

## 嵌套接口

接口可以嵌套在类或其他接口中。

```java
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
```

嵌套在另一个接口中的接口自动就是public。

当实现一个接口时，并不需要实现嵌套在其内部的接口。

## 接口与工厂

```java
//: InterfaceFactories.java
interface Service {
    void method1();
    void method2();
}

interface ServiceFactory {
    Service getService();
}

class Imp1 implements Service {
    Imp1() { }
    public void method1() { System.out.println("Imp1 method1"); }
    public void method2() { System.out.println("Imp1 method2");  }
}

class Imp1Factory implements ServiceFactory {
    public Service getService() {
        return new Imp1();
    }
}

class Imp2 implements Service {
    Imp2() { }
    public void method1() { System.out.println("Imp2 method1"); }
    public void method2() { System.out.println("Imp2 method2");  }
}

class Imp2Factory implements ServiceFactory {
    public Service getService() {
        return new Imp2();
    }
}

public class InterfaceFactories {
    public static void serviceConsumer(ServiceFactory fact) {
        Service s = fact.getService();
        s.method1();
        s.method2();
    }

    public static void main(String args[]) {
        serviceConsumer(new Imp1Factory());
        serviceConsumer(new Imp2Factory());
    }
} /** Output
Imp1 method1
Imp1 method2
Imp2 method1
Imp2 method2
*/
```

