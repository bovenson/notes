#include <iostream>

using namespace std;

int maxProductAfterCutting(int length) {
    if (length < 2) {
        return 0;
    } else if (length == 2) {
        return 1;
    } else if (length == 3) {
        return 2;
    }
    int *product = new int[length+1];
    int max = 0;
    for (int i = 4; i < length; ++i) {
        max = 0;
        for (int j=1; j <= i / 2; ++j) {
            int product = products[j] * products[i - j];
            if (max < product) {
                max = product;
            }
            products[i] = max;
        }
    }
    max = products[length];
    delete[] products;
    return max;
}

int main() {
    return 0;
}
