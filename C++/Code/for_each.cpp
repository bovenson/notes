#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

template <class T>
void print(const T& v) {
    cout << v << " ";
}

template <class T>
class Print {
public:
    void operator()(const T& v) {
        cout << v << " ";
    }
};

int main() {
    int val[] = {0, 1, 2, 3, 4, 5, 6};
    vector<int> vec(val, val+7);
    for_each(vec.begin(), vec.end(), print<int>);
    cout << endl;
    for_each(vec.begin(), vec.end(), Print<int>());
    cout << endl;
    return 0;
}

/** Output
0 1 2 3 4 5 6 
0 1 2 3 4 5 6 
*/
