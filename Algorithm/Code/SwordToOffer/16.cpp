#include <iostream>

using namespace std;

double Power(double base, int exponent) {
    if (equal(base, 0.0) && exponent < 0) {
        return 0.0;
    }

    unsigned int absExponent = (unsigned int)epxponent;
    if (exponent < 0) {
        absExponent = (unsigned int)(-exponent);
    }

    double res = PowerWithUnsignedExponent(base, absExponent);
    if (exponent < 0) {
        res = 1.0 / res;
    }
    return res;
}

double PowerWithUnsignedExponent(double base, unsigned int exponent) {
    double res = 1.0;
    for (int i=0; i <= exponent; ++i) {
        result *= base;
    }
    return res;
}

int main() {
    return 0;
}
