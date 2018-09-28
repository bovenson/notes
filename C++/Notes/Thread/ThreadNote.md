# 创建线程

```c++
std::thread nth(&ctr::Class::func, this);		// 创建运行对象成员函数的线程
std::thread nth(func, "HELLO");
```

**Example**

```c++
#include <iostream>
#include <thread>
#include <unistd.h>

using namespace std;

class A {
public:
    void f(string str) {
        cout << str << endl;
        sleep(3);
    }

    void fn() {
        cout << "A::fn()" << endl;
        sleep(3);
    }
};

void f(string str) {
    cout << str << endl;
    sleep(3);
}

void fn() {
    cout << "fn()" << endl;
    sleep(3);
}

int main() {
    A a;
    // std::thread nth(&A::f, &a);
    std::thread nfth(fn);

    while (true) {}
    return 0;
}

```



# 参考

- [线程管理](http://www.cplusplus.com/forum/beginner/196573/)

