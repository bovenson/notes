#include <iostream>
#include <cstring>

using namespace std;

class A {
public:
    int* a;
    unsigned int b = 2;
    int c: 3;
    string s;
    A () {
        a = new int;
    }
    ~A() {
        delete a;
    }
};

int main () {
    // sizeof: 返回一个对象或类型所占的内存字节数

//    while (1) {
        // A *a = (A *)malloc(sizeof(A));
//        A *a = new A();
        // delete a;
//        free(a);
//    } 

    int a = 1;
    int &b = a;
    int &c = b;

    a = 2;
    cout << b << endl;
    cout << c << endl;
    b = 3;
    cout << a << endl;
    cout << c << endl;

    return 0;
}

