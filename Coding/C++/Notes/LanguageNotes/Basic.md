---
title: C++基础
tags:
	- C++
categories:
	- C++
---

C++继承了C语言的高效、简洁、快速和可以执行，同时融合了三种不同的编程方式：

- C语言代表的**过程性语言**
- 在C语言基础上添加的类代表的**面向对象**语言
- C++模板支持的**泛型编程**

# C & C++

- C是面向过程，C++是面向对象
  - C++添加了类
- C++有引用的概念，C没有
- C变量只能在函数的开头处声明和定义，而C++随时定义随时使用

# int & new int()

```c++
#include <iostream>

using namespace std;

int main() {
    int *a = new int(1);
    cout<<a<<endl<<*a<<endl;
    while (true) {
        new int(1);
    }
}

// 上面这段代码有内存泄露的问题
// 下面的不会

#include <iostream>

using namespace std;

int main() {
    int *a = new int(1);
    cout<<a<<endl<<*a<<endl;
    while (true) {
        // new int(1);
        int b = 1;
    }
}
```

# new/delete & malloc/free

**使用**

```c++
//// malloc
// 原型
void * malloc(size_t size);
// 使用
int *p = (int *) malloc(sizeof(int) * length);

//// new
int *array = new int[5];

//// free
free(p);	// 不用指定长度

//// delete
delete[] array
delete p
```



|   项目   |                     new/delete                     |         malloc/free          |
| :------: | :------------------------------------------------: | :--------------------------: |
|   属性   |        **C++关键字/操作符**，需要编译器支持        |  **库函数**，需要头文件支持  |
|   参数   | 无须指定内存块的大小，编译器会根据类型信息自行计算 | 需要显式地指出所需内存的尺寸 |
| 返回类型 |            返回对象类型的指针，无需转换            |   返回void*指针，需要强转    |
| 分配失败 |                 抛出bas_alloc异常                  |           返回NULL           |

**其他**

- new运算符内部数据类型不进行初始化，会自动调用非内部数据类型的默认构造函数
- 由于 malloc/free 是库函数而不是运算符，不在编译器控制权限之内，不能够把执行构造函数和析构函数的任务强加malloc/free

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

# include <> & ""

- `#include <...>` : 首先搜索默认路径，如果不存在，再去搜索源文件所在目录
- `#include "..."` : 首先搜索源文件所在目录，如果不存在，再去搜索默认路径
- PS
  - 默认路径 : 系统及`LD_LIBRARY_PATH`定义路径

# 共享/动态库和静态库

- 共享库/动态库(Shared libraries / Dynamic libraries)
  - `.so` or `.dll` in windows or `.dylib` in OS X
  - 在程序运行时使用它
  - 使用共享库的程序仅引用使用到的代码
  - 优点
    - 编译的程序较小
    - 可以在不重新编译程序的前提下，更新动态库
  - 缺点
    - 增加运行时开销
- 静态库(Static libraries)
  - `.a` or `.lib` in windows
  - 在编译时直接链接到程序中
  - 使用静态库的程序从静态库中获取它使用的代码的副本，并使其成为程序的一部分

- 打包动态库/静态库
  - Ref
    - [Ref 1](https://www.geeksforgeeks.org/static-vs-dynamic-libraries/)

# 限定词

## static

### 作用

- 修饰全局

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

# C++没有finally语句怎么释放资源

使用C/C++的人，都该有这么一个共识：对资源谁申请谁释放，否则就必须使用委托。当资源仅在某一个函数里使用时，那就必须在该函数里释放。

在C++中通常使用RAII，即Resource Aquisition Is Initialization。就是将资源封装成一个类，将资源的初始化封装在构造函数里，释放封装在析构函数里。要在局部使用资源的时候，就实例化一个local object。在抛出异常的时候，由于local object脱离了作用域，自动调用析构函数，会保证资源被释放。