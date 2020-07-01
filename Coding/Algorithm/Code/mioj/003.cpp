#include <iostream>

using namespace std;

int main() {
    std::string inp, a, b;
    size_t pos;
    bool carry;
    while (cin >> inp) {
        pos = inp.find("-");
        a = inp.substr(0, pos);
        b = inp.substr(pos+1);
        carry = false;
        auto ita = a.rbegin();
        for (auto itb = b.rbegin(); ita != a.rend() && itb != b.rend(); ++ita, ++itb) {
            if (carry && *ita - *itb - 1 >= 0) {
                *ita = '0' + *ita - *itb - 1;
                carry = false;
            } else if (carry) {
                *ita = '0' + *ita - *itb + 9;
            } else if (*ita >= *itb) {
                *ita = '0' + *ita - *itb;
            } else {
                *ita = '0' + *ita - *itb + 10;
                carry = true;
            }
        }
        while (carry && ita != a.rend()) {
            if (*ita > '0') {
                *ita -= 1;
                break;
            } else {
                *ita = *ita - 1 + 10;
                ++ita;
            }
        }
        bool bg = true;
        for (auto it = a.begin(); it != a.end(); ++it) {
            if (bg && *it != '0') {
                bg = false;
            }
            if (!bg) {
                std::cout << *it;
            }
        }
        std::cout << std::endl;
    }
    return 0;
}
