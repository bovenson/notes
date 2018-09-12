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

# 特殊语法

## 临时对象的产生和运用

```shell
#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template <class T>
void print(const T& v) {
    cout << v << " ";
}

template <class T>
class Print {
public:
    void operator()(const T& v) {
        cout << v << " ";
    }
};

int main() {
    int val[] = {0, 1, 2, 3, 4, 5, 6};
    vector<int> vec(val, val+7);
    for_each(vec.begin(), vec.end(), print<int>);
    cout << endl;
    for_each(vec.begin(), vec.end(), Print<int>());	// Print<int>() 是一个临时对象
    cout << endl;
    return 0;
}

/** Output
0 1 2 3 4 5 6 
0 1 2 3 4 5 6 
*/
```

## 静态常量整数成员可在class内部直接初始化

```c++
#include <iostream>

using namespace std;

class A {
public:
    static const int a = 1;
    // static int f = 1;    // ISO C++ forbids in-class initialization of non-const static member 'A::f'; 非const static int, 必须类外定义
    // static const double b = 1.1; // constexpr' needed for in-class initialization of static data member 'const double A::b' of non-integral type
    static const double c;
    // static const string d = "d"; // in-class initialization of static data member 'const string A::d' of non-literal type
    static const string e;
};

const double A::c = 1.2;
// const static double A::c = 1.2;  // may not be used when defining (as opposed to declaring) a static data member
const string A::e = "e";

int main () {
    return 0;
}
```

**不仅只是int，整数类型都可以**

- char
- long
- ...

## 操作符重载

```c++
#include <iostream>

using namespace std;

class INT {
private:
    int m_i;
    friend ostream& operator<<(ostream& os, const INT& i);
public:
    INT(int i) : m_i(i) {}

    INT& operator++ () {
        ++(this->m_i);
        return *this;
    }

    const INT operator++(int) {
        INT temp = *this;
        ++(*this);
        return temp;
    }

    INT& operator--() {
        --(this->m_i);
        return *this;
    }

    const INT operator--(int) {
        INT temp = *this;
        --(*this);
        return temp;
    }

    int& operator*() const {    // ? 将const int 转为non-const lvalue
        return (int&)m_i;
    }

    void operator()() const {   // 如果不加const, 对于 const INT 无法使用该操作符
        cout << m_i << endl;
    }

};

ostream& operator<<(ostream& os, const INT& i) {
    os << '[' << i.m_i << ']';
    return os;
}

int main() {
    INT I(5);
    cout << I << endl;
    I();
    (I++)();
    (++I)();
    cout << I << endl;
    (I--)();
    (--I)();
    cout << *I << endl;
    return 0;
}

/** Output
[5]
5
5
7
[7]
7
5
5
*/
```

