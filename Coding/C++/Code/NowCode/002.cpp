#include <iostream>
#include <algorithm>
#include <vector>
#include <functional>

using namespace std;

int main() {
    vector<int> arr = {1, 3, 2, 0, 91, -1, 2, 4, 1};
    sort(arr.begin(), arr.end());
    for (auto v : arr) {
        cout << v << " ";
    }
    cout << endl;
    sort(arr.begin(), arr.end(), greater<int>());
    for (auto v : arr) {
        cout << v << " ";
    }
    cout << endl;
    return 0;
}
