#include <iostream>
#include <vector>
#include <algorithm>
#include <string>
#include <map>
#include <set>
#include <string.h>
#include <fstream>
#include <iterator>
#include <sstream>

using namespace std;

template <typename T>
void printVector(vector<T> v) {
    for (auto it=v.begin(); it != v.end(); ++it) {
        std::cout << *it << " ";
    }
    std::cout << std::endl;
}

template <typename T>
void pV(std::vector<T> v) {
    printVector(v);
}

template <typename T>
void printVector(vector<vector<T>> v) {
    for (auto &vt: v) 
        printVector(vt);
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

std::vector<std::vector<int>> read2DNum(std::string &f) {
    std::vector<std::vector<int>> v;
    ifstream in;
    in.open(f);
    std::string s;
    while (getline(in, s)) {

        std::cout << s << std::endl;
    }
    return v;
}

template <typename T>
std::vector<T> readVec(const char *f) {
    std::vector<T> res;
    ifstream in(f);
    T number;
    while(in >> number) {
        res.push_back(number);
    }
    return res;
}

template <typename T>
T r() {
    T t;
    cin >> t;
    return t;
}

template <typename T>
std::vector<T> rV() {
    std::vector<T> res;
    T t;
    while (cin >> t) {
        res.push_back(t);
    }
    return res;
}
