#include <iostream>

int main() {
    for (int i = 0; i < 10; ++i) {
        static int j = 1;
        std::cout << j << std::endl;
        ++j;
    }
    return 0;
}
