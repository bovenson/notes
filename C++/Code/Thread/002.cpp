#include <iostream>
#include <pthread.h>
#include <unistd.h>

using namespace std;

class A {
public:
    void f(string str) {
        cout << str << endl;
        sleep(3);
    }
};

int main() {
    A a;
    std::thread nth(&A::f, &a);
    return 0;
}
