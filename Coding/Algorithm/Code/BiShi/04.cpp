#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

int res = 0;

void solve(string &str, int cur, int* flag) {
    if (cur == str.length() - 1) {
        int value = 0, t = 0, lastFlag = -2;
        for (int i = 0; i < str.length(); ++i) {
            t = t * 10 + str[cur] - '0';
            if (flag[i] != 0) {
                if (lastFlag == 1) {
                    value += t;
                } else if (lastFlag == -1) {
                    value -= t;   
                } 
                lastFlag = flag[i];
                t = 0;
            }
        }
        cout << "[value] " << value << endl;
        if (value == 0) ++res;
        return;
    } 
    flag[cur] = 1;
    solve(str, cur+1, flag);
    flag[cur] = -1;
    solve(str, cur+1, flag);
    flag[cur] = 0;
    solve(str, cur+1, flag);
}

int main() {
    int n;
    string str;
    cin >> n;
    while (n > 0) {
        --n;
        cin >> str;
        int flag[str.length()];
        memset(flag, 0, sizeof(int) * str.length());
        res = 0;
        solve(str, 0, flag);
        cout << res << endl;
    }
    return 0;
}
