---
title: C++基础
tags:
	- C++
categories:
	- C++
---

# 限定词

## static

- 程序运行分配内存，生命周期和程序一样

```c++
class A {
public:
    // static int a = 1;            // ERROR: ISO C++ 禁止在类内初始化非 const static 成员变量
    const int b = 1;            
    const static int c = 1;         // OK
    // const static double d = 1.1; // ERROR: 禁止在类内初始化非整形 const static 成员变量
    const int e;                    // WARNING: 必须初始化; 这里 OR 构造函数初始化列表
    const double f = 1.1;
    A () : e(1) {
        // e = 3;                   // 常量不可赋值 必须在定义时初始化
    }
    
    void fc (int v) {
        const int fc = v;
        const static int fcs = v;
        cout << fc << endl;
        cout << fcs << endl;
    }
};

int main() {
    A a;
    a.fc(1);
    A b;
    b.fc(2);
    return 0;
} /** Output
1
1
2
1
*/
```

### const vs static

- const : 不可更改
- static : 静态常量
- const static : 不可变的静态变量

# Struct & Class

## struct in c VS struct in c++

