#include <iostream>

using namespace std;

int getCross(const int &lax, const int &lay, const int &lbx, const int &lby, const int &x, const int &y) {
    return (x - lax) * (lby - lay) - (y - lay) * (lbx - lax);
}

int main() {
    int n, d, m, x, y;
    cin >> n >> d >> m;
    int grasshoppers[m][2];
    for (int i = 0; i < m; ++i) {
        cin >> x >> y;
        if (getCross(d, 0, n, n-d, x, y) * getCross(0, d, n-d, n, x, y) <= 0 && 
            getCross(0, d, d, 0, x, y) * getCross(n-d, n, n, n-d, x, y) <= 0) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl; 
        }
    }
    return 0;
}
