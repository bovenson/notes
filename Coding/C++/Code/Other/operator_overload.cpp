#include <iostream>

using namespace std;

class INT {
private:
    int m_i;
    friend ostream& operator<<(ostream& os, const INT& i);
public:
    INT(int i) : m_i(i) {}

    INT& operator++ () {
        ++(this->m_i);
        return *this;
    }

    const INT operator++(int) {
        INT temp = *this;
        ++(*this);
        return temp;
    }

    INT& operator--() {
        --(this->m_i);
        return *this;
    }

    const INT operator--(int) {
        INT temp = *this;
        --(*this);
        return temp;
    }

    int& operator*() const {    // ? 将const int 转为non-const lvalue
        return (int&)m_i;
    }

    void operator()() const {   // 如果不加const, 对于 const INT 无法使用该操作符
        cout << m_i << endl;
    }

};

ostream& operator<<(ostream& os, const INT& i) {
    os << '[' << i.m_i << ']';
    return os;
}

int main() {
    INT I(5);
    cout << I << endl;
    I();
    (I++)();
    (++I)();
    cout << I << endl;
    (I--)();
    (--I)();
    cout << *I << endl;
    return 0;
}

/** Output
[5]
5
5
7
[7]
7
5
5
*/
