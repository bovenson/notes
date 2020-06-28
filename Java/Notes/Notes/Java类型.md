---
title: Java类型
tags:
	- Java
categories:
	- Java
---

# 基本数据类型

- 整数数据类型
  - `byte`
  - `short`
  - `int`
  - `long`
- 字符型
  - `char`
- 浮点类型
  - `float`
  - `double`
- 布尔型
  - `boolean`

## 自动装箱和拆箱

自动装箱和拆箱就是基本类型和引用类型之间的转换。

**为什么自动装箱？**

- 可以`new`对象
- 如果集合中想存放基本类型，泛型的限定类型只能是对应的包装类型
- 可以调用`Object`内置函数