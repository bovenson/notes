#include <iostream>

int main() {
    std::string a, b, inp;
    while (std::cin >> inp) {
        static int pos = inp.find("-");
        a = inp.substr(0, pos);
        b = inp.substr(pos+1);
        auto ita = a.rbegin(), itb = b.rbegin();
        short carry = 0;
        for (; ita != a.rend() && itb != b.rend(); ++ita, ++itb) {
            if (*itb + carry > *ita) {
                *ita = '0' + *ita + 10 - carry - *itb;
                carry = 1;
            } else {
                *ita = '0' + *ita - *itb - carry;
                carry = 0;
            }
        }
        while (carry && ita != a.rend()) {
            if (*ita > '0') {
                *ita -= carry;
                break;
            } else { *ita = *ita - carry + '0' + 10; ++ita; }
        }
        bool bg = true;
        for (auto it = a.begin(); it != a.end(); ++it) {
            if (bg && *it > '0') { bg = false; }
            if (!bg) { std::cout << *it; }
        }
        if (bg) { std:: cout << "0"; }
        std::cout << std::endl;
    }
    return 0;
}
