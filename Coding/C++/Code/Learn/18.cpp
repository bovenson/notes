#include <iostream>

using namespace std;

int main() {
    cout << std::hash<std::string>()("HELLO") << std::endl;
    return 0;
}
