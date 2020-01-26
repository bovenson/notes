#include <iostream>
#include <vector>

using namespace std;

template <typename T>
void printVector(vector<T> v) {
    for (auto it=v.begin(); it != v.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

template <typename T>
void swapT(T& a, T& b) {
    a ^= b;
    b ^= a;
    a ^= b;
}

template <typename T>
void reverse(vector<T> &v, int a, int b) {
    for (;a < b; ++a)
        swapT(v[a], v[b]);
}

