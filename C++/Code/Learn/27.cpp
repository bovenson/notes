#include <iostream>

using namespace std;

int main() {
    std::cout << (2 == 2==1?2:1) << std::endl;
    std::cout << (2 == (2==1?2:1)) << std::endl;
    std::cout << (-1 == 3==0?1:2) << std::endl;
    std::cout << (3 == 3==0?1:2) << std::endl;
    std::cout << (-1 == 3==1?1:2) << std::endl;
    std::cout << (3 == 3==1?1:2) << std::endl;
    return 0;
}
