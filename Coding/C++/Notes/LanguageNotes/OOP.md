---
title: C++面向对象编程
tags:
	- 面向对象编程
categories:
	- C++
---

# 面向对象

**是什么**

- 基于对象的软件开发思想

**特性**

- 封装
- 继承
- 多态

# 类

## 定义&使用

```c++
#include <iostream>
using namespace std;

// 定义类
class A {
public:
    int a;
};

int main() {
    A a;	// 定义类对象
    cout << a.a << endl;
    a.a = 1;
    cout << a.a << endl;
}
```

## 成员

- 数据成员
- 成员函数
- 构造函数
- 析构函数
- 拷贝构造函数
- 友元函数
- 内联函数
- this指针
- 静态成员

```c++

```



## 修饰符

默认情况下，类的所有成员都是私有的。

- public
  - 类的外部是可访问的
- protected
  - 在派生类（即子类）中是可访问
- private
  - 在类的外部是不可访问
  - 只有类和友元函数可以访问私有成员