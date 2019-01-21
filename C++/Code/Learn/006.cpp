#include <iostream>

using namespace std;

template <typename T>
class A {
    T t_;
    public:
    template <typename Args>
    A(Args... args) : t_(std::forward<Args>(args)...) { }
}

template <typename... Targs>
std::vector<A*> Create(Targs... args) {
    return std::vector<A*>({
        new A<>(std::forward<Targs>(args))...
    });
}


int main() {
    cout << A::type << endl;
    return 0;
}
