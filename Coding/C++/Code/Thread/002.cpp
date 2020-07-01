#include <iostream>
#include <unistd.h>
#include <thread>
#include <unistd.h>

using namespace std;

class A {
public:
    void f(string str) {
        cout << "A::f() " << str << endl;
        sleep(3);
    }

    void fn() {
        cout << "A::fn()" << endl;
        sleep(3);
    }
};

void f(string str) {
    cout << "f(string str)" << str << endl;
    sleep(3);
}

void fn() {
    cout << "fn()" << endl;
    sleep(3);
}

int main() {
    A a;
    std::thread nth(f, "Hello");
    sleep(1);
    std::thread nfth(fn);
    sleep(1);
    std::thread nfthc(&A::f, &a, "hello");

    sleep(5);
    nth.join();

    cout << "All DOEN" << endl;
    while (true) {}
    return 0;
}
