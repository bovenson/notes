#include <iostream>

using namespace std;

class Plm {
    public:
    struct  Arg {
        size_t net;
        size_t mem;
        size_t query;
    };
};

int main() {
    Plm::Arg arg {
        .net = 1,
        .mem = 3,
        .query = 2
    };
    cout << arg.net << " " << arg.mem << " " << arg.query << endl;
    return 0;
}
