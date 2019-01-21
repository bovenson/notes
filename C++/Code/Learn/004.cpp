#include <iostream>

using namespace std;

void fb(const int a) {
}

template<typename T>
void fa(const T &a) {
    return fb(a);
}

int main() {
    fa(1);
    return 0;
}
