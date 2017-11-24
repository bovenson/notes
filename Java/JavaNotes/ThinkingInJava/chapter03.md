---
title: 操作符
tags: Java, 操作符
---

# 操作符

## 使用Java操作符

操作符接受一个或多个参数，并生成一个新值。参数的形式于普通的方法调用不同，但效果是相同的。

操作符作用于操作数，生成一个新值。另外，有些操作符可能会改变操作数自身值，这被称为**副作用**。

几乎所有操作符都只能操作基本类型。例外的操作符是“=”，“==”，“！=” 能操作所有的对象。除此之外，String类支持“+”和“+=”。

## 关系操作符

`==`操作符比较的是对象的引用，而不是对象的实际值。

注意基本类型和其包装类实例使用`==`号时的比较结果。

如果想比较两个对象的实际内容是否相同，必须使用所有对象都适用的特殊方法`equals()` 。

```java
public class Operator {
    public static void main(String args[]) {
        Integer i1 = new Integer(11);
        Integer i2 = new Integer(11);
        int i3 = 11;
        int i4 = 11;

        System.out.println(i1 == i2);
        System.out.println(i1.equals(i2));
        System.out.println(i3 == i4);
        System.out.println(i1.equals(i3));
        System.out.println(i1 == i3);	// true
        System.out.println(i2 == i3);	// true
        // System.out.println(i3.equals(i4)); 非法：i3是基本类型，没有equals方法
    }
} /* Output:
false
true
true
true
true
true
*/
```

## 逻辑操作符

**短路**现象是指，一但能够明确无误地确定整个表达式的值，就不再计算表达式余下部分了。

## 直接常量

```java
public class Literals {
    public static void main(String args[]) {
        int i1 = 0x2f;  // 16进制 (小写)
        System.out.println("i1: " + Integer.toBinaryString(i1));    // i1: 101111

        int i2 = 0X2F;  // 16进制 (大写)
        System.out.println("i2: " + Integer.toBinaryString(i2));    // i2: 101111

        int i3 = 0177;  // 8进制 (0开头)
        System.out.println("i3: " + Integer.toBinaryString(i3));    // i3: 1111111

        int c = 0xffff; // char类型16进制最大值
        System.out.println("c: " + Integer.toBinaryString(c));      // c: 1111111111111111

        int b = 0x7f; // byte 类型16进制表示的最大值
        System.out.println("b: " + Integer.toBinaryString(b));      // b: 1111111

        short s = 0x7fff;   // max short hex value
        System.out.println("s: " + Integer.toBinaryString(s));  // s: 111111111111111

        long n1 = 200L; // long suffix
        long n2 = 200l; // long suffix (but can be confusing)
        long n3 = 200;  
        float f1 = 1;
        float f2 = 1F;  // float suffix
        float f3 = 2f;  // float suffix
        double d1 = 1d; // double suffix
        double d2 = 1D; // double suffix
        // Hex and Octal also work with long
    }
} /* Output
i1: 101111
i2: 101111
i3: 1111111
c: 1111111111111111
b: 1111111
s: 111111111111111
*/
```

## 指数计数法

```java
float expFloat = 1.29e-43f;	// UPpercase and lowercase 'e' are the same
double expDouble = 47e47d;	// 'd' is optional
double expDouble2 = 47e47;	// Automatically double
```

## 移位操作符

- `<<`：左移位操作符，低位补零。
- `>>`：右移位操作符，“有符号“右移位操作符使用”符号扩展“：若符号为正，则在高位插入0；符号为负，则在高位插入1。
- `>>>`：无符号右移位操作符，使用”零扩展“：无论正负，都在高位插入0。

对char、byte、short类型的数值进行移位处理之前，它们会被转换为int类型，并且得到的结果也是一个int类型的值。

数字的二进制表示形式称为”有符号的二进制补码“。

## 字符串+

```java
public class T {
    public static void main(String args[]) {
        System.out.println(1 + " is " + 1);
        System.out.println(1 + 1 + " is " + 2);
    }
} /** Output
1 is 1
2 is 2
*/
```

## 其他

```java
int a = 3;
int b, c;
System.out.println(b = a);		// 3
System.out.println(c = b = a);	// 3
Integer i1 = new Integer(1);
Integer i2 = new Integer(2);
System.out.println(i1 + i2);	// 3
```

