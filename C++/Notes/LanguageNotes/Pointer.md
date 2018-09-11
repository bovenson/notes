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

