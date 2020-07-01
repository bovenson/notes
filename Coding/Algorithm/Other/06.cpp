#include <iostream>
#include <string.h>

using namespace std;

int main() {
    string S, P;
    getline(cin, S);
    getline(cin, P);
    bool v[S.length()];
    memset(v, 0, sizeof(bool) * S.length());
    for (int i = 0, j = 0; i < S.length(); ++i) {
        while (j < P.length() && S[i+j] == P[j]) {j++;};
        if (j == P.length()) {
            j--;
            while (j >= 0) {v[i+j] = true; j--;}
            j++;
        } else {
            j = 0;
        }
    }
    int res = 0, add = 0;
    for (int i = 0; i < S.length(); ++i) {
        if (v[i]) ++add;
        else {
            res += add * add;
            add = 0;
        }
    }
    res += add * add;
    cout << res;
    return 0;
}
