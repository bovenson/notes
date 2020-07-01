#include <iostream>
#include <initializer_list>

using namespace std;

template <typename T>
void f(std::initializer_list<T> list) {
    for (auto elem : list) {
        cout << elem << endl;
    }
}

template <typename T>
void printArg(T t) {
    cout << t << endl;
}

template <typename... T>
void fc(T... args) {
    // (cout << ... << args) << endl;   // c++17
    cout << sizeof ...(args) << endl;
    // cout << sizeof(...(args)) << endl;   // sizeof(type)
                                            // sizeof expression
    cout << "===" << endl;
    { (printArg(args), 0)... };
}


int main() {
    f({1, 2, 3});
    cout << "-----" << endl;
    fc(1, ":", "one", 1.1, 'c');
    cout << "-----" << endl;
    return 0;
}
