#include <iostream>
#include <vector>

using namespace std;

int main() {
    int i, j, k, n, t, sum, avg, cnt;
    vector<int> v;
    cin >> k;
    for (i = 0; i < k; ++i) {
        cin >> n;
        sum = 0; cnt = 0;
        v.clear();
        for (j = 0; j < n; ++j) {
            cin >> t;
            v.push_back(t);
        }
        if (n == 0) {
            cout << "YES" << endl;
        } else {
            if (sum % v.size() != 0) {
                cout << "NO" << endl;
            } else {
                int x;
                bool flag = false;
                avg = sum / v.size();
                for (int l = 0; l < v.size(); ++l) {
                    if (l == 0) {
                        x = v[l] - avg;
                    } else {
                        if (v[l] - avg != x && v[l] - avg != -x && v[l] != avg) {
                            flag = true;
                        }
                    }
                }
                if (flag) {
                    cout << "NO" << endl;
                } else {
                    cout << "YES" << endl;
                }
            }
        }
    }
    return 0;
}