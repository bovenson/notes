#include <iostream>

using namespace std;

class A {
public:
    static const int a = 1;
    // static int f = 1;    // ISO C++ forbids in-class initialization of non-const static member 'A::f'; 非const static int, 必须类外定义
    // static const double b = 1.1; // constexpr' needed for in-class initialization of static data member 'const double A::b' of non-integral type
    static const double c;
    // static const string d = "d"; // in-class initialization of static data member 'const string A::d' of non-literal type
    static const string e;
};

const double A::c = 1.2;
// const static double A::c = 1.2;  // may not be used when defining (as opposed to declaring) a static data member
const string A::e = "e";

int main () {
    return 0;
}
