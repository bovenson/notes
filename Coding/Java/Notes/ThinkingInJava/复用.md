---
title: 复用类
tags: Java, 复用类
author: bovenson
email: szhkai@qq.com
---

# 复用类

复用类的方法：

- 组合：在另一个类中创建该类的对象
- 继承

## 组合语法

组合，只需将对象引用置于新类中即可。

初始化引用的位置：

- 在定义对象的地方
- 在类的构造器中
- 在正要使用这些对象之前，这种方式称为：**惰性初始化**
- 使用实例初始化

## 继承语法

如果使用命令`Java A`， 即便A不是public类，`A.main()`仍可被调用。

```java
//: ExtendsExample.java
public class ExtendsExample extends A { // 继承
    public static void main(String args[]) {
        System.out.println("ExtendsExample");
    }
}

class A {
    public static void main(String args[]) {
        System.out.println("A");
    }
} /**
Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src/thinkinginjava/chapter07$ javac ExtendsExample.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src/thinkinginjava/chapter07$ java A
A
*/
```

待继承类中，待继承的方法或成员必须是public或protected。如果没有加任何权限修饰词，那么成员默认的访问权限是包访问权限，仅允许包内的成员访问。

```java

//: ExtendsE.java
package thinkinginjava.chapter07;

import thinkinginjava.chapter07.outerpackage.OuterPackage;

public class ExtendsE extends OuterPackage {
    public static void main(String args[]) {
        ExtendsE ee = new ExtendsE();
        ee.funPublic();
        ee.funProtected();
        //! ee.funPackage();    // 找不到符号；不在用一个包内，无访问权限
        //! ee.funPrivate();    // 找不到符号
    }
} /** Output
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter07/ExtendsE.java 
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter07.ExtendsE
funPublic
funProtected
*/

//: OuterExtendsE.java
package thinkinginjava.chapter07.outerpackage;

public class OuterExtendsE extends OuterPackage {
    public static void main(String args[]) {
        OuterExtendsE oee = new OuterExtendsE();
        oee.funPublic();
        oee.funPackage();
        oee.funProtected();
        //！ oee.funPrivate();   // 找不到符号
    }
} /** Output
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter07/outerpackage/OuterExtendsE.java 
bovenson@ThinkCentre:~/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter07.outerpackage.OuterExtendsE 
funPublic
funPackage
funPrivate
*/

//: OuterPackage.java
package thinkinginjava.chapter07.outerpackage;

public class OuterPackage {

    public void funPublic() {
        System.out.println("funPublic");
    }

    void funPackage() {
        System.out.println("funPackage");
    }

    protected void funProtected() {
        System.out.println("funProtected");
    }

    private void funPrivate() {
        System.out.println("funPrivate");
    }

    public static void main(String args[]) {
        System.out.println("OuterPackage");
    }
}
```

Java中super关键词表示超类。

### 初始化基类

现在涉及基类和导出类。

当创建一个导出类的对象时，该对象包含一个基类的子对象。这个子对象和用基类直接创建的对象是一样的。二者的区别在于，后者来自于外部，而基类的子对象被包装在导出类对象内部。

导出类只能在构造器中调用基类的构造器来初始化基类。**Java会自动在导出类的构造器中插入对基类构造器的调用。即便导出类不适用编译器自动生成的构造器。**

```java
//: ExtendsE2.java

public class ExtendsE2 extends Extends2 {

    public ExtendsE2() {
        System.out.println("class ExtendsE2 initial");
    }

    public ExtendsE2(int marker) {
        System.out.println("class ExtendsE2 initial with marker " + marker);
    }

    public static void main(String args[]) {
        ExtendsE2 ee = new ExtendsE2();
        ExtendsE2 ee2 = new ExtendsE2(1);
    }
} /**Output
class A initial
class ExtendsE2 initial
class A initial
class ExtendsE2 initial with marker 1
*/

class Extends2 {
    Extends2() {
        System.out.println("class A initial");
    }
}
```

#### 带参数的构造器

如果基类没有默认的构造器，或者想调用一个带参数的基类构造器，就必须使用super显式地编写调用基类构造器的语句，并且给出适当的参数。

```java
//: ExtendsA.java

public class ExtendsA extends SuperA {
    ExtendsA(int marker) {
        super(marker);
        System.out.println("Initialize ExtendsA with marker " + marker);
    }

    public static void main(String args[]) {
        ExtendsA ea = new ExtendsA(10);
    }
} /**Output
Initialize SuperA with marker 10
Initialize ExtendsA with marker 10
*/

class SuperA {
    SuperA(int marker) {
        System.out.println("Initialize SuperA with marker " + marker);
    }
}
```

## 代理

将一个成员对象置于所要构造的类中（就像组合），但与此同时在新类中暴露了该成员对象的所有方法（就像继承）。

例如，太空船需要一个控制模块：

```java
//: SpaceShipControls.java
// 太空船的控制模块

public class SpaceShipControls {
    void up(int velocity) {}
    void down(int velocity) {}
    void left(int velocity) {}
    void right(int velocity) {}
    void forward(int velocity) {}
    void back(int velocity) {}
    void turboBoost() {}
}
```

如果使用继承：

```java
//: SpaceShip.java

public class SpaceShip extends SpaceShipControls {
    private String name;
    public SpaceShip(String name) {
        this.name = name;
    }
    public String toString() { return name; }
    public static void main(String args[]) {
        SpaceShip ss = new SpaceShip("NSEA Protector");
        ss.forward(100);
    }
}
```

如果使用代理：

```java
//: SpaceShipDelegated.java

public class SpaceShipDelegated {
    private String name;
    private SpaceShipControls controls = new SpaceShipControls();
    public SpaceShipDelegated(String name) {
        this.name = name;
    }
    // Delegated methods
    public void back(int velocity) {
        controls.back(velocity);
    }

    public void down(int velocity) {
        controls.down(velocity);
    }

    public void left(int velocity) {
        controls.left(velocity);
    }

    public void right(int velocity) {
        controls.right(velocity);
    }

    public void forward(int velocity) {
        controls.forward(velocity);
    }

    public void turboBoost() {
        controls.turboBoost();
    }

    public static void main(String args[]) {
        SpaceShipDelegated ssd = new SpaceShipDelegated("NSEA Protector");
        ssd.forward(100);
    }
}
```

## 结合使用组合和继承

### 确保正确清理

Java中没有C++中析构函数的概念。但是，有时，类可能要在其生命周期内执行一些必需的清理活动。

一旦涉及垃圾回收，能够信赖的事就不会很多了。垃圾回收器可能永远也无法被调用，即使被调用，它也可能以任何它想要的顺序来回收对象。最好的办法是除了内存以外，不能依赖垃圾回收器去做任何事。如果需要进行清理（不一定是释放内存），最好是编写自己的清理方法，但不要使用finalize()。

### 名称覆盖

如果Java的基类拥有某个已被多次重载的方法名称，那么在导出类中重新定义该方法名称并不会屏蔽其在基类中的任何版本。

```java
//: OverrideTest.java

public class OverrideTest extends OverrideClass {
    void f(int i) {
        System.out.println("OverrideTest f(int) " + i);
    }

    void f(double d) {
        System.out.println("OverrideTest f(double) " + d);
    }

    void f(String s) {
        System.out.println("OverrideTest f(String) " + s);
    }

    public static void main(String args[]) {
        OverrideTest ot = new OverrideTest();
        ot.f(1);
        ot.f('c');
        ot.f(1f);
        ot.f(1.0);
        ot.f("string");
    }
} /**Output
OverrideTest f(int) 1
OverrideClass f(char) c
OverrideClass f(String) 1.0
OverrideTest f(double) 1.0
OverrideTest f(String) string
*/

class OverrideClass {
    void f(int i) {
        System.out.println("OverrideClass f(int) " + i);
    }

    void f(char c) {
        System.out.println("OverrideClass f(char) " + c);
    }

    void f(float f) {
        System.out.println("OverrideClass f(String) " + f);
    }
}
```

`@Override`注解：当想要覆写某个方法时，可以选择添加这个注解，可以防止意外重载了该方法。

## 在组合和继承之间选择

组合和继承都允许在新的类中放置子对象，组合是显式地这样做，而继承则是隐式地做。

## protected关键字

protected指明，就类用户而言，这是private的，但对于任何继承于此类的导出类或其他任何位于同一个包内的类来说，它却是可以访问的。（**protected也提供了包内访问权限**）。

## 向上转型

将导出类引用转换为基类引用的动作，我们称之为向上转型。

由导出类转型为基类，在继承图上是向上移动的。

由于向上转型是从一个较专用类型向较通用类型转换，所以总是安全的。

向上转型过程中，类接口中唯一可能发生的事情是丢失方法，而不是获取它们。

#### 再论组合与继承

到底是该用组合还是继承，一个最清晰的判断方法就是，是否需要从新类向基类进行向上转型。如果必须向上转型，则继承是必要的；如果不需要，则应好好考虑是否需要继承。

## final关键字

一个既是static又是final的域只占据一段不能改变的存储空间。

```java
public class A {
    //! final int b; 
    //! b = 20; // 语法错误；可以在构造器中初始化
    void f(int i) {
        final int a;
        a = 10;
        //! final static int c;  // static不能用来修饰局部变量
        // c = 10;
        System.out.println(i);
    }
}
```

对于基本类型，final使数值恒定不变；而用于对象引用时，final使引用恒定不变。一旦引用被初始化指向一个对象，就无法再把它更改为指向另一个对象。然而，对象其自身确是可以被修改的，Java并未提供使任何对象恒定不变的途经。

定义为static，强调只有一份；定义为final，则说明它是一个常量。同时使用`static final`定义的变量，必须在定义时对其进行初始化。

### 空白final

空白final指被声明为final但又未给定初值的域。无论什么情况，编译器都确保空白final在使用前被初始化：**必须在域的定义处或者每个构造器中表达式对final进行赋值**。为此，一个类中的final域可以做到根据对象而有所不同，却又保持恒定不变的特性。

### final参数

当基本类型参数被指明为final时，所出现的结果：可以读参数，但却无法修改参数。这一特性主要向匿名内部类传递数据。

当引用参数被指明为final时，不能修改参数指向别的对象引用，但可以修改参数引用指向的对象。

```java
//: FinalArg.java

public class FinalArg {
    void f(final int i, final A a) {
        //! i = 10; // 不能分配最终参数i
        //! a = new A();    // 不能分配最终参数a
        a.a = 10;
    }

    public static void main(String args[]) {
        new FinalArg().f(1, new A());
    }
}

class A {
    int a;
}
```

###  final方法

使用final方法的原因有两个：

- 把方法锁定，以防止任何继承类修改它的含义。这是出于这样的设计考虑：想要确保在继承中使方法保持不变，并且不会被覆盖。
- 效率。但是在Java SE5/6，应该让编译器和JVM去处理效率问题，只有在想要明确禁止覆盖时，才将方法设置为final的。

### final和private关键字

类中所有private方法都隐式地指定为final。由于无法取用private方法，也就无法覆盖它。为private方法添加final修饰词，是没有必要的。

**覆盖只是在某方法是基类接口的一部分时才会出现。即，必须能将一个对象向上转型为它的基本类型并调用相同的方法。如果某个方法为private，它就不是基类的接口的一部分，而且，导出类可以定义相同名称的方法。由于private方法无法触及而且能有效隐藏，所以除了把它看成是因为它所归属的类的组织结构的原因而存在外，其他任何食物都不需要考虑到它。**

### final类

当将某个类的整体定义为final时，就表明了不打算继承该类，而且也不允许别人这样做。

final类的域可以根据个人意愿选择是或不是final。然而，由于final类禁止继承，所以final类中的所有的方法都隐式指定为final的，因为无法覆盖它们。

