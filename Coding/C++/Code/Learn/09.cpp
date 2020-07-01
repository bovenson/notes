#include <iostream>
#include <vector>
#include <utility>

using namespace std;

int main() {
    int a = 1, b = 2;
    cout << a << " - " << b << endl;
    swap(a, b);
    cout << a << " - " << b << endl;

    vector<int> v{1, 2, 3};
    iter_swap(v.begin(), v.begin() + 1);
    for (auto i : v) {
        cout << i << " ";
    }
    cout << endl;
    return 0;
}
