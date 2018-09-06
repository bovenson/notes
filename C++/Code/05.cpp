#include <iostream>
#include <set>

using namespace std; 

int main() {
    set<int> s;
    s.insert(1);
    cout << s.size() << endl;
    s.erase(1);
    cout << s.size() << endl;
    return 0;
}
