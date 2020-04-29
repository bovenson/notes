#include <iostream>
#include <algorithm>
#include <utility>
#include <vector>

using namespace std;

template <typename T>
void printVector(vector<T> v) {
    for (auto i : v) {
        cout << i << " ";
    }
    cout << endl;
}
