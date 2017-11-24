---
title: 初始化与清理
tags: Java, 初始化与清理
author: bovenson
email: szhkai@qq.com
---

# 初始化与清理

Java采用了构造器，并提供了“垃圾回收器”。

## 用构造器确保初始化

构造器采用与类完全相同的名称。调用构造器是编译器的责任。

不接受任何参数的构造器叫做默认构造器，或无参构造器。

在Java中，初始化和创建捆绑在一起，两者不能分离。

构造器是一种特殊类型的方法，因为它没有返回值。这与返回值为空（void）不同。构造器不返回任何东西。

## 方法重载

为了让方法名相同而形式参数不同的方法同时存在，需要用到**方法重载**。

### 区分重载方法

对于方法相同的方法，Java确定调用哪一个的是依据：每个重载的方法都必须有一个独一无二的参数类型列表。

### 涉及基本类型的重载

基本类型能从一个较小的类型自动提升至一个较大的类型，此过程一旦牵扯到重载，可能会造成一些混淆。

```java
public class PrimitiveOverloading {
    static void f1(char x) { System.out.print("f1(char) "); }
    static void f1(byte x) { System.out.print("f1(byte) "); }
    static void f1(short x) { System.out.print("f1(short) "); }
    static void f1(int x) { System.out.print("f1(int) "); }
    static void f1(long x) { System.out.print("f1(long) "); }
    static void f1(float x) { System.out.print("f1(float) "); }
    static void f1(double x) { System.out.print("f1(double) "); }

    static void f2(byte x) { System.out.print("f2(byte) "); }
    static void f2(short x) { System.out.print("f2(short) "); }
    static void f2(int x) { System.out.print("f2(int) "); }
    static void f2(long x) { System.out.print("f2(long) "); }
    static void f2(float x) { System.out.print("f2(float) "); }
    static void f2(double x) { System.out.print("f2(double) "); }

    static void f3(short x) { System.out.print("f3(short) "); }
    static void f3(int x) { System.out.print("f3(int) "); }
    static void f3(long x) { System.out.print("f3(long) "); }
    static void f3(float x) { System.out.print("f3(float) "); }
    static void f3(double x) { System.out.print("f3(double) "); }

    static void f4(int x) { System.out.print("f4(int) "); }
    static void f4(long x) { System.out.print("f4(long) "); }
    static void f4(float x) { System.out.print("f4(float) "); }
    static void f4(double x) { System.out.print("f4(double) "); }

    static void f5(long x) { System.out.print("f5(long) "); }
    static void f5(float x) { System.out.print("f5(float) "); }
    static void f5(double x) { System.out.print("f5(double) "); }

    static void f6(float x) { System.out.print("f6(float) "); }
    static void f6(double x) { System.out.print("f6(double) "); }

    static void f7(double x) { System.out.print("f7(double) "); }

    public static void main(String[] args) {
        System.out.print("     5: ");
        f1(5); f2(5); f3(5); f4(5); f5(5); f6(5); f7(5); System.out.println();
        
        char x1 = 'x';
        System.out.print("  char: ");
        f1(x1); f2(x1); f3(x1); f4(x1); f5(x1); f6(x1); f7(x1); System.out.println();

        byte x2 = 0;
        System.out.print("  byte: ");
        f1(x2); f2(x2); f3(x2); f4(x2); f5(x2); f6(x2); f7(x2); System.out.println();

        short x3 = 0;
        System.out.print(" short: ");
        f1(x3); f2(x3); f3(x3); f4(x3); f5(x3); f6(x3); f7(x3); System.out.println();

        int x4 = 0;
        System.out.print("   int: ");
        f1(x4); f2(x4); f3(x4); f4(x4); f5(x4); f6(x4); f7(x4); System.out.println();

        long x5 = 0;
        System.out.print("  long: ");
        f1(x5); f2(x5); f3(x5); f4(x5); f5(x5); f6(x5); f7(x5); System.out.println();

        long x6 = 0;
        System.out.print(" float: ");
        f1(x6); f2(x6); f3(x6); f4(x6); f5(x6); f6(x6); f7(x6); System.out.println();

        double x7 = 0;
        System.out.print("double: ");
        f1(x7); f2(x7); f3(x7); f4(x7); f5(x7); f6(x7); f7(x7); System.out.println();
    }
} /** Output:
     5: f1(int) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  char: f1(char) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  byte: f1(byte) f2(byte) f3(short) f4(int) f5(long) f6(float) f7(double)
 short: f1(short) f2(short) f3(short) f4(int) f5(long) f6(float) f7(double)
   int: f1(int) f2(int) f3(int) f4(int) f5(long) f6(float) f7(double)
  long: f1(long) f2(long) f3(long) f4(long) f5(long) f6(float) f7(double)
 float: f1(long) f2(long) f3(long) f4(long) f5(long) f6(float) f7(double)
double: f1(double) f2(double) f3(double) f4(double) f5(double) f6(double) f7(double)
*/
```

常数5被当做int处理。如果传入的数据类型小于方法中声明的形式参数类型，实际数据类型就会被提升。char类型略有不同，如果无法找到恰好接受char参数的方法，就会把char直接提升至int型。

如果传入的实际参数大于重载方法声明的形式参数：

```java
public class Demotion {
    static void f1(char x) { System.out.print("f1(char) "); }
    static void f1(byte x) { System.out.print("f1(byte) "); }
    static void f1(short x) { System.out.print("f1(short) "); }
    static void f1(int x) { System.out.print("f1(int) "); }
    static void f1(long x) { System.out.print("f1(long) "); }
    static void f1(float x) { System.out.print("f1(float) "); }
    static void f1(double x) { System.out.print("f1(double) "); }

    static void f2(char x) { System.out.print("f2(char) "); }
    static void f2(byte x) { System.out.print("f2(byte) "); }
    static void f2(short x) { System.out.print("f2(short) "); }
    static void f2(int x) { System.out.print("f2(int) "); }
    static void f2(long x) { System.out.print("f2(long) "); }
    static void f2(float x) { System.out.print("f2(float) "); }

    static void f3(char x) { System.out.print("f3(char) "); }
    static void f3(byte x) { System.out.print("f3(byte) "); }
    static void f3(short x) { System.out.print("f3(short) "); }
    static void f3(int x) { System.out.print("f3(int) "); }
    static void f3(long x) { System.out.print("f3(long) "); }

    static void f4(char x) { System.out.print("f4(char) "); }
    static void f4(byte x) { System.out.print("f4(byte) "); }
    static void f4(short x) { System.out.print("f4(short) "); }
    static void f4(int x) { System.out.print("f4(int) "); }

    static void f5(char x) { System.out.print("f5(char) "); }
    static void f5(byte x) { System.out.print("f5(byte) "); }
    static void f5(short x) { System.out.print("f5(short) "); }

    static void f6(char x) { System.out.print("f6(char) "); }
    static void f6(byte x) { System.out.print("f6(byte) "); }

    static void f7(char x) { System.out.print("f7(char) "); }

    public static void main(String args[]) {
        System.out.print("Double: ");
        double x = 0;
        // f1(x); f2(x); f3(x); f4(x); f5(x); f6(x); f7(x); error: no suitable method found for \
        // f2(double) f3(double) f4(double) f5double) f6(double)
        f1(x); f2((float)x); f3((long)x); f4((int)x); f5((short)x); f6((byte)x); f7((char)x);
    }
} /** Output 
Double: f1(double) f2(float) f3(long) f4(int) f5(short) f6(byte) f7(char)
*/
```

如果传入的参数较大，就需要显式进行强制转换（窄化转换）。

**Java不允许方法只有返回值类型不同（即，不能通过返回值区分方法）。**

## 默认构造器

如果没有定义构造器，编译器会默认创建一个默认构造器（无参构造器）；如果提供了一个构造器，那么编辑器不再自动创建默认构造器。

## this关键字

如果希望在方法内部获得对当前对象的引用，可以使用this关键字。this关键字只能在方法内部使用，表示对调用方法的那个对象的引用。

如果在方法内部调用同一个类的另一个方法，就不必使用this，直接调用即可。

### 在构造器中调用构造器

```java
public class ST {
    int a = 0;
    String s = "initial value";
    public ST(int a) {
        this.a = a;
        System.out.println("Constructor w/ int arg only, a = " + a);
    }

    public ST(String s) {
        this.s = s;
        System.out.println("Constructor w/ String arg only, s = " + s);
    }

    public ST(int a, String s) {
        this(a);
        //! this(s); // Can't call two
        System.out.println("String & int arg");
    }

    public ST() {
        this(10, "hi");
        System.out.println("Constructor without args");
        //! this(10, "hi"); // 构造器必须置于最起始处
    }

    public void printST() {
        System.out.println("a=" + a + " s=" + s);
    }

    public static void main(String args[]) {
        ST x = new ST();
        x.printST();
    }
} /** Output
Constructor w/ int arg only, a = 10
String & int arg
Constructor without args
a=10 s=initial value
*/
```

**注意：**

- 在一个构造器中，最多只能调用一次别的构造器
- 在一个构造器中，调用另一个构造器，必须置于最起始处

### static

static方法就是没有this的方法。在static方法的内部不能调用非静态方法；在非静态方法中，可以调用静态方法。

## 清理：终结处理和垃圾回收

如果对象（并未使用new）获得一块“特殊”的内存区域，由于垃圾回收器只知道释放那些经由new分配内存，所以它不知道如何释放该对象的这块特殊内存。为了应对这种情况，Java允许在类中定义一个名为finalize()的方法。它的工作原理假定是这样的：一旦垃圾回收器准备好释放对象占用的存储空间，将首先调用其finalize()方法，并且在下一次垃圾回收动作发生时，才会真正回收对象占用的内存。

但是finalize()和C++中的析构函数是不一样的。在C++中，正常情况下对象一定会被销毁，而Java中的对象却并非总是被回收。

- 对象可能不被垃圾回收
- 垃圾回收并不等于析构

有时，只要程序没有濒临存储空间用完的那一刻，对象占用的空间就总也得不到释放。如果程序执行结束，并且垃圾回收器一直都没释放创建的对象的存储空间，则随着程序的退出，那些资源也会全部交还给操作系统。

### finalize()的用途

垃圾回收只与内存有关。

finalize()的需要限制到一种特殊情况，即通过某种创建对象方式以外的方式为对象分配了存储空间。这种情况主要发生在使用**本地方法**的情况下。本地方法是一种在Java中调用非Java代码的方式。

### 实施清理

无论是垃圾回收还是终结，都不保证一定会发生（垃圾回收或终结）。如果Java虚拟机并未面临内存耗尽的情形，它不会浪费时间去执行垃圾回收以恢复内存。

### 垃圾回收器如何工作

自适应的、分代的、停止-复制、标记-清扫式垃圾回收器。

- 停止-复制
- 标记-清扫

## 成员初始化

Java尽力保证：所有变量在使用前都能得到恰当的初始化。对于方法的局部变量，Java以编译时错误的形式来保证变量初始化。

```java
public void f() {
  	int i;
	//! i++; // error: variable i might not have been initialized
}
```

类的每个基本类型数据成员保证都会有一个初始值（可以不用显式初始化），**这种初始化要早于调用构造器函数**。

在类中定义一个对象引用时，如果不将其初始化，此引用就会获得一个特殊值null。

### 初始化顺序

在类的内部，变量定义的先后顺序决定了初始化的顺序。即使变量定义散布于方法定义间，它们仍旧会在任何方法（包括构造器）被调用前得到初始化。

### 静态数据的初始化

```java
class Bowl {
    Bowl(int marker) {
        System.out.println("Bowl(" + marker + ")");	// A1/1/1; A1/2/1; A2/1/1; A2/2/1; A2/3/1
    }

    void f1(int marker) {
        System.out.println("f1(" + marker + ")");	// A1/4/1;	A2/5/1; A4/3/1
    }
}

class Table {
    static Bowl bowl1 = new Bowl(1);	// A1/1
    Table() {
        System.out.println("Table()");	// A1/3
        bowl2.f1(1);	// A1/4
    }
    void f2(int marker) {
        System.out.println("f2(" + marker + ")");	// A7/1
    }
    static Bowl bowl2 = new Bowl(2);	// A1/2
}

class Cupboard {
    Bowl bowl3 = new Bowl(3);	// A2/3; A4/1; A6/1
    static Bowl bowl4 = new Bowl(4);	// A2/1
    Cupboard() {
        System.out.println("Cupboard()");	// A2/4; A4/2; A6/2
        bowl4.f1(2);	// A2/5; A4/3; A6/3
    }

    void f3(int marker) {
        System.out.println("f3(" + marker + ")");	// A8/1
    }

    static Bowl bowl5 = new Bowl(5);	// A2/2
}

public class StaticInitialization {
    public static void main(String args[]) {
        System.out.println("Creating new Cupboard() in main");	// A3
        new Cupboard();	// A4
        System.out.println("Creating new Cupboard() in main");	// A5
        new Cupboard();	// A6
        table.f2(1);	// A7
        cupboard.f3(1);	// A8
    }
    static Table table = new Table();	// A1
    static Cupboard cupboard = new Cupboard();	// A2
} /** Output
Bowl(1)
Bowl(2)
Table()
f1(1)
Bowl(4)
Bowl(5)
Bowl(3)
Cupboard()
f1(2)
Creating new Cupboard() in main
Bowl(3)
Cupboard()
f1(2)
Creating new Cupboard() in main
Bowl(3)
Cupboard()
f1(2)
f2(1)
f3(1)
*/
```

无论创建多少个对象，静态数据都只占用一份存储区域。

static关键字不能应用于局部变量，因此它只能作用于域。

```java
void f1(int marker) {
    //! static int i = 0; // error: illegal start of expression
  	System.out.println("f1(" + marker + ")");
}
```

如果一个域是静态的基本类型域，且也没有对它进行初始化，那么它就会获得基本类型的标准初值；如果它是一个对象引用，那么它的默认初始化值就是null。

### 显式的静态初始化

```java
class Cup {
    Cup(int marker) {
        System.out.println("Cup(" + marker + ")");
    }

    void f(int marker) {
        System.out.println("f(" + marker + ")");
    }
}

class Cups {
    static Cup cup1;
    static Cup cup2;
    static {
        cup1 = new Cup(1);
        cup2 = new Cup(2);
    }
    static {
        Cup cup3 = new Cup(3);
    }
    Cups() {
        System.out.println("Cups()");
    }
}
```

```java
public class ExplicitStatic {
    public static void main(String args[]) {
        System.out.println("Inside main()");
        // Cups.cup1.f(99);
    }
    // static Cups cups1 = new Cups();
    // static Cups cups2 = new Cups();
    // static Cups cups3;
} /** Output
Inside main()
*/
```

```java
public class ExplicitStatic {
    public static void main(String args[]) {
        System.out.println("Inside main()");
        Cups.cup1.f(99);
    }
    static Cups cups1 = new Cups();
    static Cups cups2 = new Cups();
    static Cups cups3;
} /** Output
Cup(1)
Cup(2)
Cup(3)
Cups()
Cups()
Inside main()
f(99)
*/
```

```java
public class ExplicitStatic {
    public static void main(String args[]) {
        System.out.println("Inside main()");
        Cups.cup1.f(99);
    }
    // static Cups cups1 = new Cups();
    // static Cups cups2 = new Cups();
    // static Cups cups3;
} /** Output
Inside main()
Cup(1)
Cup(2)
Cup(3)
f(99)
*/
```

### 非静态实例初始化

实例初始化是在两个构造器之前执行的。

## 数组初始化

数组只是相同类型的，用一个标识符名称封装在一起的一个对象序列或基本类型序列。

两种定义方式（只是定义，并未创建对象）：

```java
int[] a1;
int a2[];
```

在定义时，不允许指定数组大小。

所有数组都有一个不可更改的成员：length，通过它获取数组内元素个数。

为数组变量分配空间：

```java
a1 = new int[10];
Random rand = new Random();
a2 = new int[rand.nextInt(20)];
```

**数组元素中的基本数据类型（不包括基本类型的包装类）会自动初始化为空值。**

如果创建了一个非基本类型的数组，那么就创建了一个数组引用；如果忘记了创建对象，并试图使用数组中的引用，就会在运行时产生异常。

可以用花括号包起来的列表来初始化对象数组：

```java
Integer[] a = {
  	new Integer(1),
    new Integer(2),
    3,	// 自动包装，最后的逗号可选
};
```

### 可变参数列表

`Object[] args`

```java
class A { }

public class VarArgs {
    static void printArray(Object[] args) {
        for (Object obj: args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String args[]) {
        printArray(new Object[] {
            new Integer(47), new Float(3.14), new Double(11.11)
        });
        printArray(new Object[] {"one", "two", "three"});
        printArray(new Object[] {new A(), new A(), new A()});
    }
} /** Output
47 3.14 11.11
one two three
A@4aa298b7 A@7d4991ad A@28d93b30
*/
```

Java SE5之后，添加了新特性`Object... args` ：

```java
class A {}

public class NewVarArgs {
    static void printArray(Object... args) {
        for (Object obj: args) {
            System.out.print(obj + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        printArray(new Integer(47), new Float(3.14), new Double(11.11));
        printArray(47, 3.14F, 11.11D);
        printArray("one", "two", "three", "four");
        printArray(new A(), new A(), new A(), new A());

        printArray((Object[])new Integer[]{ 1, 2, 3, 4 });
        printArray();
    }
} /** Output
Args
47 3.14 11.11
47 3.14 11.11
one two three four
A@4aa298b7 A@7d4991ad A@28d93b30 A@1b6d3586
1 2 3 4

*/
```

可以使用具有Object之外类型的可变参数列表：

```java
void f(int required, String... trailing) {
  	...
}
```

在参数列表中可以使用任何类型的参数，包括基本类型。

但是这种是不可以的：

```java
//! static void f(int... ints, String... strs) {
//!    ...
//! }
```

可变参数列表与自动包装机制可以和谐共处。

## 枚举类型

```java
//: Spiciness.java
public enum Spiciness {	// 定义
  	NOT, MILD, MEDIUM, HOT, FLAMING
}

//: SimpleEnumUse.java
public class SimpleEnumUse {	// 使用
  	public static void main(String args[]) {
      	Spiciness howHot = Spiciness.MEDIUM;
      	System.out.println(howHot);
  	}
} /**
MEDIUM
*/
```

这里创建一个名为Spiciness的枚举类型，它具有5个具名值。由于每个枚举类型的实例是常量，因此按照命名惯例它们都用大写字母表示（如果一个名字中有多个单词，用下划线将它们隔开）。

`ordinal()和values`

```java
enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}

public class EnumSample {
    public static void main(String args[]) {
        for (Spiciness s: Spiciness.values()) {
            System.out.println(s + ", ordinal " + s.ordinal());
        }
    }
} /**
NOT, ordinal 0
MILD, ordinal 1
MEDIUM, ordinal 2
HOT, ordinal 3
FLAMING, ordinal 4
*/
```

enum类型对象可以结合switch使用。

```java
enum Spiciness {
    NOT, MILD, MEDIUM, HOT, FLAMING
}

public class EnumSample {
    public static void main(String args[]) {
        Spiciness s = Spiciness.HOT;
        switch(s) {
            case NOT:
                System.out.println("NOT");
                break;
            case MILD:
                System.out.println("MILD");
                break;
            case MEDIUM:
                System.out.println("MEDIUM");
                break;
            case HOT:
                System.out.println("HOT");
                break;
            case FLAMING:
                System.out.println("FLAMING");
                break;
            default:
                System.out.println("UNKONWN");
        }
    }
} /**
HOT
*/
```

