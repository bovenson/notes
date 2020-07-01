#include <iostream>
#include <vector>

using namespace std;

class A {
    public:
    A () {}
};

int main() {
    std::vector<std::vector<A>> av;
    av.emplace_back(100);
    std::cout << av[0].size() << std::endl;
    return 0;
}
