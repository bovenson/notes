---
title: C++指针
tags:
	- 指针
categories:
	- C++
---

# 示例

```c++
#include <iostream>

using namespace std;

int main () {
    int i = 6;
    int *p_i = &i;
    cout << "  i : " << i << endl;
    cout << "  &i: " << &i << endl;
    cout << "p_i : " << p_i << endl;
    cout << "*p_i: " << *p_i << endl;
    return 0;
}

/** Output
  i : 6
  &i: 0x7ffeee386644
p_i : 0x7ffeee386644
*p_i: 6
*/
```

```c++
#include <iostream>

using namespace std;

int main () {
    char* a, b;		// a: char *; b: char
    char *c, d;		// c: char *; d: char
    cout << "sizeof(a):" << sizeof(a) << endl;
    cout << "sizeof(b):" << sizeof(b) << endl;
    cout << "sizeof(c):" << sizeof(c) << endl;
    cout << "sizeof(d):" << sizeof(d) << endl;
    return 0;
}

/** Output
sizeof(a):8
sizeof(b):1
sizeof(c):8
sizeof(d):1
*/
```

# 指针和引用

**对比**

- 指针是一个实体，需要分配内存空间。引用只是变量的别名，不需要分配内存空间。
- 引用在定义的时候必须进行初始化，并且不能够改变。指针在定义的时候不一定要初始化，并且指向的空间可变。
- 有多级指针，但是没有多级引用，只能有一级引用。
- 指针支持多种操作，比如++。
- sizeof 引用得到的是所指向的变量（对象）的大小，而sizeof 指针得到的是指针本身的大小。
- 引用访问一个变量是直接访问，而指针访问一个变量是间接访问。

# 智能指针

智能指针可以很好地解决：

- 内存泄漏
  - 用动态存储分配函数（如malloc）动态开辟的空间，在使用完毕后未释放，结果导致一直占据该内存单元，直到程序结束
  - 内存泄漏是指堆内存泄漏
- 悬挂指针
  - 未初始化的指针
    - 指针变量刚被创建时不会自动成为NULL指针，它的缺省值是随机的
  - 未清零的指针
    - 指针p被free或者delete之后，没有置为NULL，让人误以为p是个合法的指针

## 四种智能指针

智能指针的原理都是RAII(Resource Acquisition Is Initialization)，即在构造的时候获取资源，在析构的时候释放资源。 

为了使用智能指针，需要引入头文件 `#include　<memory>`。

### auto_ptr

c++98

#### 解决内存泄漏问题

```c++
#include <iostream>
#include <memory>

using namespace std;

class A {
public:
    A () {}
    ~A() {
        cout << "Calling A's destructor" << endl;
    }
};

class B {
public:
    B () {}
    ~B() {
        cout << "Calling B's destructor" << endl;
    }
};

int main() {
    auto_ptr<A> a(new A());
    B *b = new B();
    return 0;
}
/**output
Calling A's destructor
*/
```

#### 解决浅拷贝导致的指针悬挂问题

```c++
#include <iostream>
#include <memory>
using namespace std;

class HasPtr
{
public:
    HasPtr(char *s);
    ~HasPtr();

private:
    char *ptr;
};

HasPtr::HasPtr(char *s)
{
    if (s == nullptr)
    {
        ptr = new char[1];
        *ptr = '\0';
    }
    else
    {
        ptr = new char[strlen(s) +1];
        strcpy(ptr, s);
    }
}

HasPtr::~HasPtr()
{
    cout << "destructor ---> " << ptr << endl;
    delete ptr;
}

int main()
{
    auto_ptr<HasPtr>p1(new HasPtr("Book"));
    auto_ptr<HasPtr>p2(p1); // 所有权转移到p2,p1变为empty

    return 0;
}
/** Output
destructor ---> Book
*/
```

#### 需要注意的问题

- 所有权转移
  - **auto_ptr transfers the ownership when it is assigned to another auto_ptr.** This is really an issue while passing the auto_ptr between the functions. Say, I have an auto_ptr in Foo( ) and this pointer is passed another function say Fun( ) from Foo. Now once Fun( ) completes its execution, the ownership is not returned back to Foo. 
- 多个auto_ptr不能同时拥有同一个对象
- 不能用auto_ptr管理数组指针
- auto_ptr不可做为容器(vector, list, map)元素

### unique_ptr

- C++ 11 引入

#### 基本操作

- get()
  - 获得原生指针
- reset()
  - 重置，显式释放资源
- reset(new X)
  - 重置，重新指定对象
- release()
  - 释放所有权到某一原生指针上

可以通过std::move将所有权由一个unique_ptr对象转移到另一个unique_ptr对象上。

```c++
#include <iostream>
#include <memory>
using namespace std;

int main()
{
    //1. unique_ptr的创建
    //1.1)创建空的，然后利用reset指定对象
    unique_ptr<int> up1; 
    up1.reset(new int(3)); 
    //1.2）通过构造函数在创建时指定动态对象
    unique_ptr<int> up2(new int(4));

    //2. 获得原生指针(Getting raw pointer )
    int* p = up1.get();

    //3.所有权的变化
    //3.1)释放所有权,执行后变为empty
    int *p1 = up1.release();
    //3.2)转移所有权,执行后变为empty
    unique_ptr<int> up3 = std::move(up2);

    //4.显式释放资源
    up3.reset();

    return 0;
}
```

#### 禁止赋值和复制

unique_ptr禁止赋值和复制，“唯一”地拥有其所指对象，同一时刻只能有一个unique_ptr实例指向给定对象。也就是说模板类unique_ptr的copy构造函数以及等号（“=”）操作符是无法使用的。

```c++
#include <iostream>
#include <memory>
using namespace std;

void Fun1( unique_ptr<int> up )
{ }

int main() {
    unique_ptr<int> up1 = unique_ptr<int>(new int(10));

    //不允许复制(Copy construction is not allowed)，所以以下三个均错误
    unique_ptr<int> up2 = up1;  // error
    unique_ptr<int> up3(up1);   // error
    Fun1(up1);                  // error

    //不允许赋值('=')，所以下面错误
    unique_ptr<int> up4;
    up4 = up1;                  // error

    return 0;
}
```

#### 管理数组指针

```c++
unique_ptr< Test[ ] > uptr1(new Test[3]);
//注意 unique_ptr<Test> uptr3(new Test[3]);是不对的
unique_ptr<int[]> uptr2(new int[5]);
```

#### 做容器元素

```c++
vector<unique_ptr<int> > vec;
unique_ptr<int> ptr1(new int(3));
vec.push_back(std::move(ptr1));
//vec.push_back(ptr1); //由于禁止复制这样不行
```

### shared_ptr

​	shared_ptr has the notion called shared ownership. The goal of shared_ptr is very simple: Multiple shared pointers can refer to a single object and when the last shared pointer goes out of scope, memory is released automatically. 
　　从上面这段英文可以看出，shared_ptr是共享所有权的，其内部有一个计数机制。

#### 使用

- get()：获取原生指针
- reset()：重置，显式释放资源
- reest(new X)：重新指定对象
- unique()：检测对象管理者是否只有一个shared_ptr实例 

在shared_ptr的RAII实现机制中，默认使用delete实现资源释放，也可以定义自己的函数来释放，比如当其管理数组指针时，需要delete[]，这时就需要自定义释放函数。自定义释放的方法有两种：lambda表达式和括号操作符的重载。 

另外可以通过dynamic_pointer_cast实现继承中的转换，具体见下例。

```c++
#include <memory>
#include <iostream>
using namespace std;

class Dealloc
{
public:
    Dealloc()
    {}

    //括号()操作符的重载
    void operator() (int* p )
    {
        if (p != nullptr)
        {
            //Do the custom deallocation job
            cout << "Dealloc called to release the resource " << p 
                 << " whose value is " << *p<<endl;
            delete p;
            p = nullptr;
        }
    }
};

class Base
{
public:
    Base() {}
    // 虚函数保证Base的多态性，以便在dynamic_pointer_cast中使用
    virtual void Foo() {}
};

class Derived : public Base
{
public:
    Derived() {}
};

int main()
{
    //1. 创建
    shared_ptr<int> sp1 = shared_ptr<int>(new int(100));
    shared_ptr<int> sp2 = make_shared<int>(int(10));
    auto sp3 = shared_ptr<int>(nullptr);
    if( sp3 == nullptr )
    {
        cout<<"Null pointer" << endl;
    }

    //2. 自定义资源释放函数
    {
        // lamdba表达式
        auto sp4 = shared_ptr<int>(new int[5], [ ](int* p){
            cout<<"In lambda releasing array of objects..."<<endl;
            delete[ ] p;});
    }
    {
        // 括号()操作符的重载
        auto sp5 = shared_ptr<int>(new int(1000), Dealloc() );
    }

    //3. 复制
    auto sp6(sp1);
    auto sp7 = sp1;

    //4. Getting raw pointer
    int* pRaw = sp2.get( );

    //5. Get how many shared pointers sharing the resource
    long nCount1 = sp1.use_count();
    long nCount2 = sp2.use_count();

    //6. Is this only shared pointer sharing the resource
    bool b1 = sp1.unique();
    bool b2 = sp2.unique();

    //7. swap
    sp1.swap(sp2);

    //8. reset
    sp1.reset();
    sp1.reset(new int(20));

    //9.Using dynamic_cast_pointer on shared pointer
    auto sp10 = shared_ptr<Derived>(new Derived( ));
    shared_ptr<Base> sp11 = dynamic_pointer_cast<Base>(sp10);
    if (sp11.get( ) != nullptr )
    {
        cout << "Dynamic casting from sp10 to sp11 succeeds...." << endl;
    }

    auto sp12 = shared_ptr<Base>(new Base());
    shared_ptr<Derived> sp13 = dynamic_pointer_cast<Derived>(sp12);
    if (sp13 != nullptr)
    {
        cout << "Dynamic casting from 12 to 13 succeeds...." << endl;
    }
    else
    {
        cout << "Dynamic casting from sp12 to sp13 failed ...." << endl;
    }

    return 0;
}
```

- shared_ptr对象的引用是强引用(stong_ref)