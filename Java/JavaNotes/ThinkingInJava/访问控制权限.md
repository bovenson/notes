---
title: 访问权限控制
tags: Java, 访问权限控制
author: bovenson
email: szhkai@qq.com
---

# 访问权限控制

访问权限控制的等级，从最大权限到最小权限依次是：public、protected、包访问权限（没有关键词）和private。

## 包：库单元

包内包含有一组类，它们在单一的名字空间之下被组织在一起。

**如果同一个包中，有重复的类（类只有包权限和public权限），编译器会报错。**

类库实际上是一组类文件。其中每个文件都有一个public类，以及任意数量的非public类。

package与import

```java
// package
package access.mypackage;
public class MyClass {
  	... 
}

// import 
import access.mypackage.*;
```

按照惯例，package名称的第一部分是类的创建者的反顺序的Internet域名。

Java解析器查找`.class`文件的过程：遍历环境变量CLASSPATH中的目录，把其中的每一个目录作为根目录，再从根目录开始，解析器获取包的名称，换做路径，继续查找。

如果将两个含有相同名称的类库以“*”形式同时导入，则会存在潜在的冲突，但是只要不使用那些导致冲突的类，就不会有问题，否则就需要明确指定使用的类来自哪一个类库。

```java
//: ImportTest.java
package thinkinginjava.chapter06;
//! import thinkinginjava.chapter06.a.A;
//! import thinkinginjava.chapter06.b.A;   // thinkinginjava.chapter06.a.A的 single-type-import 已定义具有相同简名的类型
import thinkinginjava.chapter06.a.*;
import thinkinginjava.chapter06.b.*;


public class ImportTest {
    public static void main(String args[]) {
        //! A a = new A();  // 对A的引用不明确
                        //  thinkinginjava.chapter06.b 中的类 thinkinginjava.chapter06.b.A 和 
                        // thinkinginjava.chapter06.a 中的类 thinkinginjava.chapter06.a.A 都匹配
        thinkinginjava.chapter06.a.A aa = new thinkinginjava.chapter06.a.A();
        thinkinginjava.chapter06.b.A ba = new thinkinginjava.chapter06.b.A();
        System.out.println(aa);
        System.out.println(ba);
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/ImportTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.ImportTest
thinkinginjava.chapter06.a
thinkinginjava.chapter06.b
*/

//: A.java
package thinkinginjava.chapter06.a;

public class A {
    private String pk = "thinkinginjava.chapter06.a";

    public String toString() {
        return pk;
    }
}

//: A.java
package thinkinginjava.chapter06.b;

public class A {
    private String pk = "thinkinginjava.chapter06.b";

    public String toString() {
        return pk;
    }
}
```

在创建包时，都已经在给定包的名称时隐含地指定了目录结构。这个包必须位于其名称所指定的目录之中，而该目录必须是在以CLASSPATH开始的目录中可以查询到。

## Java访问权限修饰词

对于域（数据成员）或方法，如果不提供任何访问权限修饰词，则意味着它是“包访问权限”。

### 包访问权限

如果某个成员是包访问权限，当前包中所有其他类对那个成员都有访问权限，但对于这个包外的所有类，这个成员确实private。由于一个编译单元（即一个文件）只能属于一个包，所以经由包访问权限，处于同一个编译单元中的所有类彼此之间都是自动可访问的。

```java
//: PackageControlTest.java
package thinkinginjava.chapter06.c;

public class PackageControlTest {
    public static void main(String args[]) {
        A a = new A();
        a.printB();
        System.out.println(a.outer);    // 同一个包中，不同文件的不同类数据成员包访问权限测试
        B b = new B();
        System.out.println(b.outer);    // 访问同一个包中其他文件的非public类
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/c/PackageControlTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.c.PackageControlTest
B.inner
A.outer
B.inner
*/

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
```

对包访问权限：

- 可以访问同一个包中其他文件的非public类

通过不加访问权限修饰词并将其他类放置于同一个包内的方式给成员赋予包访问权限。于是包内的其他类也就可以访问该成员了。

### public：接口访问权限

使用关键词public，意味着public之后紧跟着的成员声明自己对每个人都是可用的。

#### 默认包

不同的Java文件，它们处于**相同的目录**并且**没有给自己设定任何包名称**，Java将这样的文件自动看作是隶属于该目录的默认包之中，于是它们为该目中所有其他的文件提供了包访问权限。

### private：你无法访问

关键词private的意思是，除了包含该成员的类之外，其他任何类都无法访问这个成员。因此，private就允许你随意改变该成员，而不比考虑这样做是否会影响到包内的其他类。

默认的包访问权限通常已经提供了充足的隐藏措施。使用类的客户端程序员是无法访问包访问权限成员的。

考虑哪些类需要明确公开给客户端程序员使用了，从而将它们声明为public。

### protected：继承访问权限

有时，基类的创建者会希望有某个特定成员，把对它的访问权限赋予派生类而不是所有类，这就需要protected权限。

```java
//: ProtectedControlTest.java
package thinkinginjava.chapter06.c;
import thinkinginjava.chapter06.a.A;

public class ProtectedControlTest extends A {
    public static void main(String args[]) {
        ProtectedControlTest pct = new ProtectedControlTest();
        System.out.println(pct.pt); // 不同包，protected权限测试
    }
} /**Output
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ javac thinkinginjava/chapter06/c/ProtectedControlTest.java
bovenson@ThinkCentre:/home/public/Git/notes/Java/JavaCode/src$ java thinkinginjava.chapter06.c.ProtectedControlTest
thinkinginjava.chapter06.a
*/

//: A.java
package thinkinginjava.chapter06.a;

public class A {
    protected String pt = "thinkinginjava.chapter06.a";
    private String pk = "thinkinginjava.chapter06.a";

    public String toString() {
        return pk;
    }
}
```

## 接口和实现

访问权限的控制常被称为具体**实现的隐藏**。把数据和方法包装进类中，以及具体实现的隐藏，常共同被称作是**封装**。其结果是一个同时带有特征和行为的数据类型。

处于两个重要原因，访问权限控制将权限的边界划在了数据类型的内部：

- 设定客户端程序员可以是使用和不可以使用的界限
- 将接口和具体实现进行分离

## 类的访问权限

类既不可以是private（这样会使得除该类之外，其他任何类都不可以访问它），也不可以是protected的。所以对于类的访问权限仅有两个选择：包访问权限和public。

如果没能为类访问权限指定一个访问修饰符，默认得到包访问权限。这就意味着该类的对象可以由包内任何其他类来创建，但在包外则不可以。

如果不希望其他任何人对该类拥有访问权限，可以把所有构造器都指定为private，从而阻止任何人创建该类的对象。

一些限制：

- 每个编译单元（一个文件）都只能有一个public类
- public类的名字必须完全与含有该编译单元的文件名相匹配，包括大小写
- 编译单元内完全不带public也是可能的。这种情况下可以随便对文件命名