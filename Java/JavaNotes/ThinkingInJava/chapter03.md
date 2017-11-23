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

