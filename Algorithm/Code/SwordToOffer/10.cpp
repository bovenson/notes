#include <iostream>

using namespace std;

class Solution {
public:
    int Fibonacci(int n) {
        if (n == 0) {
            return 0;
        }
        int l = 1, ll = 1, res = 1;
        for (int i=3; i <= n; ++i) {
            res = l + ll;
            l = ll;
            ll = res;
        }
        return res;
    }
};

int main() {
    cout<<(new Solution)->Fibonacci(10)<<endl;
    return 0;
}
