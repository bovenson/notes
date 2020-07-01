#include <iostream>
#include <algorithm>
#include <vector>

int main() {
    std::vector<int> v;
    int inp_;
    while (std::cin>>inp_) {
        v.emplace_back(inp_);
    }
    std::sort(v.begin(), v.end());
    if (v.size() == 1) {
        std::cout << v[0];
        return 0;
    } else {
        for (int i=1; i < v.size(); i+=2) {
            if (v[i] != v[i-1]) {
                std::cout << v[i-1];
                return 0;
            }
        }
    }
    std::cout << v.back();
    return 0;
}
