#include <iostream>

using namespace std;

void f(void (*cbf)()) {
    cbf();
}

void cb() {
    cout << "cb()" << endl;
}

int main() {
    f(cb);
    return 0;
}
