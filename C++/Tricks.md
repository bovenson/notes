---
title: C++ Tricks
tags:
	- C++
	- Tricks
categories:
	- C++
---

# Shuffle

```c++
static void shuffle(vector<int>* v) {
  srand(9);
  for (int n = v->size(); n >= 2; n--) {
    swap((*v)[n - 1], (*v)[static_cast<unsigned>(rand()) % n]);
  }
}
```

# class 成员初始化

```shell
$ cat 010.cpp 
#include <iostream>

using namespace std;

class Plm {
    public:
    struct  Arg {
        size_t net;
        size_t mem;
        size_t query;
    };
};

int main() {
    Plm::Arg arg {
        .net = 1,
        .mem = 3,
        .query = 2
    };
    cout << arg.net << " " << arg.mem << " " << arg.query << endl;
    return 0;
}
$ g++ 010.cpp && ./a.out 
1 3 2

```

