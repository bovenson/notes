---
title: C++笔记
tags:
	- 笔记
categories:
	- C++
---

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

# 堆 & 栈

# new & malloc