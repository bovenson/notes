#include <iostream>
#include <stack>

using namespace std;

template <class T>
class OverLoadingTest {
private:
    std::stack<T> *_stack;
public:
    OverLoadingTest () {
        _stack = new std::stack<T>;
    }

    ~OverLoadingTest () {
        delete _stack;
    }

    std::stack<T>* operator->() {
        return &_stack;
    }

    T top() {
        return _stack->top();
    }
};

int main () {
    OverLoadingTest<int> olt;
    // olt->push_back(1);
    cout << olt-> << endl;
    return 0;
}
