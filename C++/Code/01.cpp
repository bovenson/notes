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

    while (1) {
        // A *a = (A *)malloc(sizeof(A));
        A *a = new A();
        // delete a;
        free(a);
    } 

    return 0;
}

