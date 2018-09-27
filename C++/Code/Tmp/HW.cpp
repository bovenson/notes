#include <iostream>
using namespace std;

int add(int a, int b) {
  return a + b;
}

int main() {
  cout<<"Hello World"<<endl;
  cout<<add(1, 2)<<endl;

  // 定义数组
  int li[5] = {1, 2, 3, 4, 5};
  for (int i=0; i < 5; ++i) {
    cout<<li[i]<<endl;
  } // 1 \n 2 \n 3 \n 4 \n 5

  // string
  string s = "hello world";
  cout<<s.substr(0, 5)<<endl; // hello

  // 指针
  char c[20];
  int cs;
  cout<<"cs的地址: "<<&cs<<endl;  // cs的地址: 0x7ffee7e140a8
  cout<<"c的地址: "<<&c<<endl;  // c的地址: 0x7ffee7e141c0

  
  return 0;
}