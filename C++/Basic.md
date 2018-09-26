---
title: C++
tags:
	- C++
categories:
	- C++
---

C++继承了C语言的高效、简洁、快速和可以执行，同时融合了三种不同的编程方式：

- C语言代表的**过程性语言**
- 在C语言基础上添加的类代表的**面向对象**语言
- C++模板支持的**泛型编程**

# new/delete VS malloc/free

- new/delete 是关键词；malloc/free是库函数
- new/delete会调用对象构造/析构函数

# delete VS delete[]

- 对于内建简单数据类型，delete和delete[] 相同
- 对于复杂数据类型，delete[]删除一个数组

# 构造函数与析构函数调用

- 构造对象时，先调用父类构造函数，再调用派生类构造函数
- 析构对象时，先调用派生类析构函数，再调用基类构造函数

# const用途

- 定义只读变量，即常量
- 修饰函数的参数和函数的返回值
- 修饰函数的定义体，这里的函数为类的成员函数，被const修饰的成员函数代表不修改成员变量的值

# 指针和引用

- 引用是变量的一个别名，内部实现是只读指针，仍然占用栈空间，但是地址和被引用的变量一致，这说明编译器隐藏了引用变量的地址

- 有多级指针，但没有多级引用

- 引用只能在初始化时被赋值，其他时候值不能被改变，非const指针的值可以在任何时候被改变

- 引用不能直接赋值为NULL，指针可以

  ```c++
  int *p = nullptr;
  int &r = nullptr; <--- compiling error
  int &r = *p;  <--- likely no compiling error, especially if the nullptr is hidden behind a function call, yet it refers to a non-existent int at address 0
  ```

- 引用变量内存单元保存的是被引用变量的地址

- "sizeof 引用" = 指向变量的大小 ， "sizeof 指针"= 指针本身的大小

- 指针可以指向栈和堆空间，但是指引用地址只能是栈

- 指针可以使用++或+n遍历数组

- 指针拥有一个内存地址，引用的内存地址和被引用变量一致

- const引用可以绑定临时变量，但是指针不可以

  ```c++
  const int &x = int(12); //legal C++
  int *y = &int(12); //illegal to dereference a temporary.
  ```

# 多态、虚函数、纯虚函数