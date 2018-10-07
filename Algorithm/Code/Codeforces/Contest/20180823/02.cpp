#include <iostream>

using namespace std;

int pointOfLineSide(const int &lax, const int &lay, const int &lbx, const int &lby, const int &x, const int &y) {

}

int main() {
    int n, d, m, x, y;
    cin >> n >> d >> m;
    int grasshoppers[m][2];
    for (int i = 0; i < m; ++i) {
        cin >> x >> y;
        if (pointOfLineSide(d, 0, n, n-d, x, y) >= 0 &&
            pointOfLineSide(n, n-d, n-d, n, x, y) <= 0 &&
            pointOfLineSide(n-d, n, 0, d, x, y) <= 0 &&
            pointOfLineSide(0, d, d, 0, x, y) >= 0) {
            cout << "YES" << endl;
        } else {
            cout << "NO" << endl; 
        }
    }
    return 0;
}
