#include <iostream>
#include <stdlib.h>
#include <unordered_map>
#include <stdio.h>
#include <string.h>
#include <unordered_set>
#include <vector>
#include <algorithm>

using namespace std;

bool solve(int A[], int B[], int C[], int dist[], int a, int b, int c) {
    if (a < 0 && c == 0) {
        bool flag = true;
        for (int i = 0; i < 4; ++i) {
            if (B[i] != dist[i]) flag = false;
        }
        return flag;
    }
    if (a >= 0) {   // a out, C in
        C[c] = A[a];
        if (solve(A, B, C, dist, a - 1, b, c+1)) return true;
    }
    if (c > 0) {    // c out, b in
        B[b] = C[c-1];
        if (solve(A, B, C, dist, a, b + 1, c - 1)) return true;
    }
    return false;
}

int main() {
    int dist[4], A[4] = {1, 2, 3, 4}, B[4], C[4];
    for (int i = 0; i < 4; ++i) {
        cin >> dist[i];
    }
    if (solve(A, B, C, dist, 3, 0, 0)) {
        cout << "Yes" << endl;
    } else {
        cout << "No" << endl;
    }
    return 0;
}
