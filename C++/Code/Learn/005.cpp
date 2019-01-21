#include <iostream>

using namespace std;

void fa(int a, int b) {
    cout << a << " " << b << endl;
}

template<typename... Args>
void f(Args... args) {
    fa((args)...);
    fa(args...);
    fa(std::forward<Args>(args)...);
    // fa(std::forward<Args>((args)...)); // ERROR
}


int main() {
    f<int, int>(1, 2);
    f(1, 2);
    return 0;
}
