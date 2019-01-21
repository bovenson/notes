---
title: C++ Function
tags: 
	-C++
categories:
	-c++
---

# 函数指针

```c++
$ cat 002.cpp 
#include <iostream>

using namespace std; 

int add(int a, int b) {
    return a + b;
}

int funcBroker(int (*fp)(int, int), int a, int b) {
    cout << fp(a, b) << endl;
    cout << (*fp)(a, b) << endl;
    cout << fp << endl;
    return fp(a, b);
}

int main() {
    funcBroker(add, 1, 2);
    cout << "-----" << endl;
    funcBroker(add, 2, 2);
    return 0;
}
$ g++ 002.cpp  && ./a.out 
3
3
1
-----
4
4
1
```

# 可变参数的列表和模板

