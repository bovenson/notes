#include <iostream>
#include <unordered_map>

using namespace std;

int main() {
    std::unordered_map<int, int> m;
    auto r = m.insert({1, 2});  // 返回由指向被插入元素（或阻止插入的元素）的迭代器和指代插入是否发生的 bool 组成的 pair 。
    cout << typeid(r).name() << endl;
    cout << r.first->first << " --- " << r.first->second << " --- " << r.second << endl;
    auto d = m.insert({1, 4});
    // cout << d.first << " - " << d.second << endl;
    return 0;
}
