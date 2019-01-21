#include <iostream>

using namespace std;

class A {};

int main() {
    std::function<A*()> aFunc;
    aFunc = [&]() { cout << "run aFunc;" << endl; return new A(); };
    aFunc();
    return 0;
}
