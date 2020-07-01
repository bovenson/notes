---
title: C++ 异常
tags:
	- C++异常
categories:
	- C++
---

# 处理异常

```c++
try {
   // 保护代码
} catch (ExceptionName e1) {
   // catch 块
}
```

# 抛出异常

```c++
// 简单异常
throw "Division by zero condition!";

// 自定义异常
#include <iostream>
#include <exception>
using namespace std;
 
struct MyException : public exception {
  const char * what () const throw () {
    return "C++ Exception";
  }
};
 
int main() {
  try {
    throw MyException();
  } catch(MyException& e) {
    std::cout << "MyException caught" << std::endl;
    std::cout << e.what() << std::endl;
  } catch(std::exception& e) {
    //其他的错误
  }
}
```

