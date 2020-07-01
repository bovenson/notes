/**
@autor	sunzhenkai
@file	004.cpp
@date	2019-02-28 18:38:36
@desc	MiOJ 004 最长连续数列
*/

#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
    int v, sep;
    vector<int> vc;
    while (true) {
        vc.clear();
        while (cin >> v) {
            vc.push_back(v);
            sep = getchar();
            if (sep == '\n') break;
            else if (sep == EOF) return 0;
        }
        std::sort(vc.begin(), vc.end(), std::greater<int>());
        for (auto &i : vc) {
            cout << i << endl;
        }
    }
    return 0;
}