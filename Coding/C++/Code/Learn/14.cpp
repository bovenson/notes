#include <iostream>
#include <functional>

using namespace std;
using CallBack = std::function<int (int &, int &)>;

int add(int &a, int &b) {
    return a + b;
}

int sub(int &a, int &b) {
    return a - b;
}

int relay(CallBack cb) {
    // cout << cb(2, 1) << endl;
    return cb(2, 1);
}

int main() {
    relay(add);
    relay(sub);
    return 0;
}
