#include <iostream>
#include <vector>

using namespace std;

int main() {
    vector<int> v{1, 2, 3, 4};
    for (auto i : v) {
        cout << i << endl;
    }

    cout << "----" << endl;

    for (auto iter=v.begin(); iter != v.end(); ++iter) {
        cout << *iter << endl;
    }
    
    cout << "----" << endl;

    // reverse traversal
    for (auto iter=v.rbegin(); iter != v.rend(); ++iter) {
        cout << *iter << endl;
    }
    return 0;
}
