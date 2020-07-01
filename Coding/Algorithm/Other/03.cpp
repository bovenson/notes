#include <iostream>
#include <cstring>

using namespace std;

int main() {
    int n, m, p, q;
    cin >> n >> m;
    bool g[n][n];
    memset(g, 0, sizeof(bool) * n * n);
    for (int i = 0; i < m; ++i) {
        cin >> p >> q;
        g[p][q] = g[q][p] = true;
    }
    return 0;
}
