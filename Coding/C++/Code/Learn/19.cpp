#include <iostream>
#include <vector>

using namespace std;

class A { };

int main() {
    std::vector<A> as;
    for (int i=0; i < 10; ++i) {
        as.emplace_back();
    }
    std::cout << as.size() << std::endl;
    return 0;
}
