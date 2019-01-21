#include <iostream>
#include <vector>

using namespace std;

template <typename... T>
void f(T... args) {
    std::vector<int> v = { args... };
    for (auto item : v) {
        std::cout << item << std::endl;
    }
}

int main() {
    f(1, 2, 3, 4, 5);
    return 0;
}
