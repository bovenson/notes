#include <iostream>
#include <typeinfo>

using namespace std;

template<typename T>
class C {
    T t_;
    public:
    void pt() {
        cout << "----" << endl;
        cout << typeid(t_).name() << endl;
        cout << "----" << endl;
    };
};

int main() {
    C<int> c;
    c.pt();
    int a = 1;

    cout << typeid(a).name() << endl;
    return 0;
}
