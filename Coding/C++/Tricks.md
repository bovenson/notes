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

# parameter pack

- https://en.cppreference.com/w/cpp/language/parameter_pack
- https://blog.csdn.net/zt_xcyk/article/details/73912606

```c++
template<typename... Arguments>
void SampleFunction(Arguments... parameters);

// equals to

template<typename T, typename U>
void SampleFunction(T param1, U param2);
```

```shell
bovenson@Dell:~/Git/notes/C++/Code/Learn$ cat 005.cpp 
#include <iostream>

using namespace std;

void fa(int a, int b) {
    cout << a << " " << b << endl;
}

template<typename... Args>
void f(Args... args) {
    fa((args)...);
    fa(args...);
    fa(std::forward<Args>(args)...);
    // fa(std::forward<Args>((args)...)); // ERROR
}


int main() {
    f<int, int>(1, 2);	 // OK
    f(1, 2);			// OK
    return 0;
}
bovenson@Dell:~/Git/notes/C++/Code/Learn$ g++ 005.cpp  && ./a.out 
1 2
1 2
1 2
1 2
1 2
1 2
```

```shell
MBP:Learn sunzhenkai$ cat 16.cpp
#include <iostream>
#include <vector>

using namespace std;

template <typename... T>
void f(T... args) {
    std::vector<int> v = { args... };
    for (auto item : v) {
        std::cout << item << std::endl;
    }
}

int main() {
    f(1, 2, 3, 4, 5);
    return 0;
}
MBP:Learn sunzhenkai$ g++ 16.cpp -std=c++11 && ./a.out
1
2
3
4
5
```

# 完美转发

无论是T&&、左值引用、右值引用，std::forward都会依照原来的类型完美转发。

- https://blog.csdn.net/ink_cherry/article/details/74573225

# 省略号语法

- http://www.cplusplus.com/articles/EhvU7k9E/

# Amazing

```c++
MBP:Learn sunzhenkai$ cat 15.cpp
#include <iostream>

using namespace std;

class A { };

class B {
    public:
    B(A a) {}
};

void fun(B b) {
    cout << "OK? OK!" << endl;
}

int main() {
    A a;
    fun(a);
    return 0;
}
MBP:Learn sunzhenkai$ g++ 15.cpp && ./a.out
OK? OK!
```

