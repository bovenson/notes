---
title: C++ Lambda
tags:
	- Lambda
categories:
	- C++
---

# 定义

`[capture list] (params list) mutable exception-> return type { function body }`

**含义**

- `capture list`：捕获外部变量列表（中括号必需）
- `params list`：形参列表（可选）
- `mutable`：指示符，用来说用是否可以修改捕获的变量（可选）
- `exception`：异常设定（可选）
- `return type`：返回类型（可选）
- `function body`：函数体（大括号必需）

# 示例

```c++
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

bool cmp(int a, int b)
{
    return  a < b;
}

int main()
{
    vector<int> myvec{ 3, 2, 5, 7, 3, 2 };
    vector<int> lbvec(myvec);

    sort(myvec.begin(), myvec.end(), cmp); // 旧式做法
    cout << "predicate function:" << endl;
    for (int it : myvec)
        cout << it << ' ';
    cout << endl;

    sort(lbvec.begin(), lbvec.end(), [] (int a, int b) -> bool { return a < b; });   // Lambda表达式
    cout << "lambda expression:" << endl;
    for (int it : lbvec)
        cout << it << ' ';
}
```

# 参考

- [C++ 11 Lambda表达式](https://www.cnblogs.com/DswCnblog/p/5629165.html)