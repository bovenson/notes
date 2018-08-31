#include <iostream>

using namespace std;

struct A {  
    int a;                  // C++11 extension; C++11 之前 & c 中不可以指定默认值
};

struct B {  
    int a;                  // C++11 extension; C++11 之前 & c 中不可以指定默认值
    int b;
};

struct C {  
    int a;                  // C++11 extension; C++11 之前 & c 中不可以指定默认值
    int b;
    unsigned int c;
};

struct D {
    int *a;
    unsigned int b:2;       // C++11 extension; C++11 之前 & c 中不可以指定默认值
    int c:1;                // 指定长度(字节)
};

struct E {
    int *a;
    unsigned int b=2;       // C++11 extension; C++11 之前 & c 中不可以指定默认值
    int c:2;                // 指定长度(字节)
};

int main() {
  cout << sizeof(bool) << endl;   // 1
  cout << sizeof(char) << endl;   // 1
  cout << sizeof(int) << endl;    // 4

  cout << sizeof(A) << endl;  // 4
  cout << sizeof(B) << endl;  // 8
  cout << sizeof(C) << endl;  // 12
  cout << sizeof(D) << endl;  // 16
  cout << sizeof(E) << endl;  // 16

  A a;
  cout << a.a << endl;        // 1

  return 0;
}