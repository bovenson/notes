#include <iostream>

using namespace std;

// ax + by = c
void pointToLine(const pair<int, int> &pa, const pair<int, int> &pb, int &a, int &b, int &c) {
    a = pa.second - pb.second;
    b = pa.first - pb.first;
    c = a * (pa.first) + b * (pb.second);
}

int main() {
    return 0;
}
