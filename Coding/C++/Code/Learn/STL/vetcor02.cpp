#include <iostream>
#include <vector>

using namespace std;

void displayVector(vector<int> v) {
    for (auto &item : v) {
        cout << item << " ";
    }
    cout << endl;
}

int main() {
    vector<int> v{1, 2, 3, 4, 5, 6};
    displayVector(v);
    swap(v[0], v.back());
    displayVector(v);
    return 0;
}
